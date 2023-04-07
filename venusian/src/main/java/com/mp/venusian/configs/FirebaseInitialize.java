package com.mp.venusian.configs;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.InputStream;

@Service
public class FirebaseInitialize {

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
                    .setDatabaseUrl("https://venusian-e99d3-default-rtdb.firebaseio.com/")
                    .build();

            FirebaseApp.initializeApp(options);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}