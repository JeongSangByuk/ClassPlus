package com.example.classplus.firebase;

import android.app.Activity;
import android.app.Application;

import com.example.classplus.DTO.ChatData;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class FirebaseConnector {

    private static FirebaseConnector ourInstance = null;

    private static FirebaseDatabase database;
    private static DatabaseReference dbRef;

    public static FirebaseConnector getInstance(Activity activity) {

        ourInstance = new FirebaseConnector(activity);
        return ourInstance;
    }

    public static FirebaseConnector getInstance() {
        return ourInstance;
    }


    private FirebaseConnector(Activity activity) {

        FirebaseApp.initializeApp(activity);
        database = FirebaseDatabase.getInstance();
        dbRef = database.getReference();
    }

    public FirebaseDatabase getFirebaseDatabase(){
        return database;
    }

    public DatabaseReference getDatabaseReference(){ return dbRef; }

}
