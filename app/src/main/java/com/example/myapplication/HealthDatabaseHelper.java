package com.example.myapplication;

import android.support.annotation.NonNull;
import com.google.firebase.database.*;

import java.util.ArrayList;
import java.util.List;

public class HealthDatabaseHelper {
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;

    private List<HealthCentersModel> healthCenterLists  = new ArrayList<>();

    public HealthDatabaseHelper() {
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("educational_centers");
    }

    public interface DataStatus {
        void DataIsLoaded(List<HealthCentersModel> healthCenterLists , List<String> keys);
        void DataInInserted();
        void DataIsUpdated();
        void DataIsDeleted();

    }

    public void readHealthData(final DataStatus dataStatus) {
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                healthCenterLists.clear();
                List<String> keys = new ArrayList<>();
                for(DataSnapshot nodeKey : dataSnapshot.getChildren()) {
                    keys.add(nodeKey.getKey());
                    HealthCentersModel healthCentersModel = nodeKey.getValue(HealthCentersModel.class);
                    healthCenterLists.add(healthCentersModel);
                }
                dataStatus.DataIsLoaded(healthCenterLists , keys);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

}
