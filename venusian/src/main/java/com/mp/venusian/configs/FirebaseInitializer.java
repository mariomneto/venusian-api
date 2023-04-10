package com.mp.venusian.configs;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.InputStream;
import java.util.List;

@Service
public class FirebaseInitializer {

    @PostConstruct
    public void initialize() {
        InputStream inputStream;
        String fileName = "pvt/firebaseKey.json";

        ClassLoader classLoader = getClass().getClassLoader();
        inputStream = classLoader.getResourceAsStream(fileName);

        if(inputStream == null){
            throw new IllegalArgumentException("Firebase private key not found on " + fileName);
        }

        try {
            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(inputStream))
                    .build();

            boolean hasBeenInitialized=false;
            List<FirebaseApp> firebaseApps = FirebaseApp.getApps();
            for(FirebaseApp app : firebaseApps){
                if(app.getName().equals(FirebaseApp.DEFAULT_APP_NAME)){
                    hasBeenInitialized=true;
                }
            }
            if(!hasBeenInitialized){
                FirebaseApp.initializeApp(options);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}