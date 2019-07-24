package com.example.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class EducationalCenterActivity extends AppCompatActivity {

    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_educational_center);

        recyclerView = findViewById(R.id.educational_center_recycler_view);
        new FirebaseDatabaseHelper().readEducationalCenters(new FirebaseDatabaseHelper.DataStatus() {
            @Override
            public void isDataLoaded(List<EducationalCenters> educationalCenters, List<String> keys) {
                new RecyclerViewConfig().setConfig(recyclerView ,EducationalCenterActivity.this , educationalCenters , keys);
            }

            @Override
            public void DataIsInserted() {

            }

            @Override
            public void DataIsUpdated() {

            }

            @Override
            public void DataIsDeleted() {

            }
        });
    }
}
