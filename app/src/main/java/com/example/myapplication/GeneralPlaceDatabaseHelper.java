package com.example.myapplication;

import android.support.annotation.NonNull;
import com.google.firebase.database.*;

import java.util.ArrayList;
import java.util.List;

public class GeneralPlaceDatabaseHelper {
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mDatabaseReference;
    private List<GeneralPlacesModel> generalPlacesList = new ArrayList<>();

    public GeneralPlaceDatabaseHelper() {
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mDatabaseReference = mFirebaseDatabase.getReference("educational_centers");
    }

    public void readGeneralPlaces() {
        mDatabaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        })
    }
}
