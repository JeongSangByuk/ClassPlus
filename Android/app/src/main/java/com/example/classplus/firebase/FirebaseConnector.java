package com.example.classplus.firebase;

import android.app.Activity;
import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;

import com.example.classplus.Constant;
import com.example.classplus.DTO.ChatData;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

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


    public Boolean haveTeamChat(int totalChatUuid){

        final Boolean[] exist = new Boolean[1];

        dbRef.child(Constant.FIREBASE_CHAT_TYPE_NODE_NAME).addListenerForSingleValueEvent(


                new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot snapshot) {
                        Log.d("ddd",String.valueOf(totalChatUuid));
                        if (snapshot.child(String.valueOf(totalChatUuid)).exists()) {

                            exist[0] = true;
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

        if(exist[0]) Log.d("sssssss","!!!!!!!");

        return exist[0];
    }

}
