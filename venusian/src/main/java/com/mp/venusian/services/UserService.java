package com.mp.venusian.services;

import com.google.cloud.firestore.*;
import com.mp.venusian.models.UserModel;
import com.mp.venusian.util.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.api.core.ApiFuture;
import com.google.firebase.cloud.FirestoreClient;

import jakarta.transaction.Transactional;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ExecutionException;

@Service
public class UserService {
    private final CollectionReference userCollection;
    @Autowired
    public UserService() {
        userCollection = FirestoreClient.getFirestore().collection("User");
    }

    @Transactional
    public UserModel save(UserModel user) throws InterruptedException, ExecutionException {
        Map map = ObjectMapper.mapObjectForDatabase(user);
        ApiFuture<DocumentReference> future = userCollection.add(map);
        DocumentReference newDocRef = future.get();
        ApiFuture<DocumentSnapshot> snapshot = newDocRef.get();
        DocumentSnapshot docSnapshot = snapshot.get();
        UserModel recordedModel = docSnapshot.toObject(UserModel.class);
        recordedModel.setId(snapshot.get().getId());
        return recordedModel;
    }

    public boolean existsBy(String field, String value) {
        Query query = userCollection.whereEqualTo(field, value);
        ApiFuture<QuerySnapshot> querySnapshotFuture = query.get();
        try {
            QuerySnapshot querySnapshot = querySnapshotFuture.get();
            return querySnapshot.isEmpty() ? false : true;
        } catch(InterruptedException | ExecutionException e) {
            return true;
        }
    }

    public Optional<UserModel> findById(String id) {
        DocumentReference docRef = userCollection.document(id);
        try{
            ApiFuture<DocumentSnapshot> future = docRef.get();
            DocumentSnapshot document = future.get();
            if (document.exists()) {
                UserModel model = document.toObject(UserModel.class);
                model.setId(id);
                return Optional.of(model);
            }
        } catch (InterruptedException | ExecutionException e) {
            return Optional.empty();
        }
        return Optional.empty();
    }

//    @Transactional
//    public void delete(UserModel userModel) {
//        userRepository.delete(userModel);
//    }
}