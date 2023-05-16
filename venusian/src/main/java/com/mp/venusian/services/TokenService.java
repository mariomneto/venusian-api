package com.mp.venusian.services;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import com.mp.venusian.models.Token;
import com.mp.venusian.util.ObjectMapper;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ExecutionException;

@Service
public class TokenService {
    private final CollectionReference tokenCollection;
    @Autowired
    public TokenService() {
        tokenCollection = FirestoreClient.getFirestore().collection("Token");
    }

    @Transactional
    public Token save(Token token) throws InterruptedException, ExecutionException {
        Map map = ObjectMapper.mapObjectForDatabase(token);
        ApiFuture<DocumentReference> future = tokenCollection.add(map);
        DocumentReference newDocRef = future.get();
        ApiFuture<DocumentSnapshot> snapshot = newDocRef.get();
        DocumentSnapshot docSnapshot = snapshot.get();
        Token recordedModel = docSnapshot.toObject(Token.class);
        recordedModel.setId(snapshot.get().getId());
        return recordedModel;
    }

    public Optional<Token> findById(String id) {
        DocumentReference docRef = tokenCollection.document(id);
        try{
            ApiFuture<DocumentSnapshot> future = docRef.get();
            DocumentSnapshot document = future.get();
            if (document.exists()) {
                Token model = document.toObject(Token.class);
                model.setId(id);
                return Optional.of(model);
            }
        } catch (InterruptedException | ExecutionException e) {
            return Optional.empty();
        }
        return Optional.empty();
    }

    public List<Token> findAllValidTokenByUser(String userId) {
        List<Token> tokens = new ArrayList<>();
        Query query = tokenCollection.whereEqualTo("userId", userId);
        ApiFuture<QuerySnapshot> querySnapshotFuture = query.get();
        try {
            QuerySnapshot querySnapshot = querySnapshotFuture.get();
            for(QueryDocumentSnapshot document : querySnapshot) {
                Token token = document.toObject(Token.class);
                tokens.add(token);
            }
        } finally {
            return tokens;
        }
    }
}