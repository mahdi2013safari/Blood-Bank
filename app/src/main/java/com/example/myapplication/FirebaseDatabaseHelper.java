package com.example.myapplication;

import android.support.annotation.NonNull;
import com.google.firebase.database.*;

import java.util.ArrayList;
import java.util.List;

public class FirebaseDatabaseHelper {
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private List<EducationalCenters> educationalCenters = new ArrayList<>();


    public interface DataStatus {
        void isDataLoaded(List<EducationalCenters> educationalCenters , List<String> keys);
        void DataIsInserted();
        void DataIsUpdated();
        void DataIsDeleted();
    }

    public FirebaseDatabaseHelper() {
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("educational_centers");
    }

    public void readEducationalCenters(final DataStatus dataStatus) {
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                educationalCenters.clear();
                List<String> keys = new ArrayList<>();
                for(DataSnapshot keyNode : dataSnapshot.getChildren()) {
                    keys.add(keyNode.getKey());
                    EducationalCenters educationalCenter = keyNode.getValue(EducationalCenters.class);
                    educationalCenters.add(educationalCenter);
                }
                dataStatus.isDataLoaded(educationalCenters , keys);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
