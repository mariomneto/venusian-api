package com.mp.venusian.services;

import com.google.cloud.firestore.*;
import com.mp.venusian.models.User;
import com.mp.venusian.util.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.api.core.ApiFuture;
import com.google.firebase.cloud.FirestoreClient;

import jakarta.transaction.Transactional;

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
    public User save(User user) throws InterruptedException, ExecutionException {
        Map map = ObjectMapper.mapObjectForDatabase(user);
        ApiFuture<DocumentReference> future = userCollection.add(map);
        DocumentReference newDocRef = future.get();
        ApiFuture<DocumentSnapshot> snapshot = newDocRef.get();
        DocumentSnapshot docSnapshot = snapshot.get();
        User recordedModel = docSnapshot.toObject(User.class);
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

    public Optional<User> findById(String id) {
        DocumentReference docRef = userCollection.document(id);
        try{
            ApiFuture<DocumentSnapshot> future = docRef.get();
            DocumentSnapshot document = future.get();
            if (document.exists()) {
                User model = document.toObject(User.class);
                model.setId(id);
                return Optional.of(model);
            }
        } catch (InterruptedException | ExecutionException e) {
            return Optional.empty();
        }
        return Optional.empty();
    }

    public Optional<User> findByEmailOrPhone(String auth) {
        Query emailQuery = userCollection.whereEqualTo("email", auth).limit(1);
        Query phoneQuery = userCollection.whereEqualTo("phone", auth).limit(1);

        ApiFuture<QuerySnapshot> emailQuerySnapshotFuture = emailQuery.get();
        ApiFuture<QuerySnapshot> phoneQuerySnapshotFuture = phoneQuery.get();
        try {
            QuerySnapshot emailQuerySnapshot = emailQuerySnapshotFuture.get();
            QuerySnapshot phoneQuerySnapshot = phoneQuerySnapshotFuture.get();

            DocumentSnapshot document = !emailQuerySnapshot.isEmpty() ? emailQuerySnapshot.getDocuments().get(0) :
                    !phoneQuerySnapshot.isEmpty() ? phoneQuerySnapshot.getDocuments().get(0) : null;

            if(document != null) {
                User user = document.toObject(User.class);
                Optional.of(user);
            }
        } catch(InterruptedException | ExecutionException e) {
            return Optional.empty();
        }
        return Optional.empty();

    }

//    @Transactional
//    public void delete(UserModel userModel) {
//        userRepository.delete(userModel);
//    }
}