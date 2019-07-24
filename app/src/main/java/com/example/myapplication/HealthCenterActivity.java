package com.example.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import java.util.List;

public class HealthCenterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health_center);

        final RecyclerView recyclerView  = findViewById(R.id.health_recycler_view);
        new HealthDatabaseHelper().readHealthData(new HealthDatabaseHelper.DataStatus() {
            @Override
            public void DataIsLoaded(List<HealthCentersModel> healthLists, List<String> keys) {
                new HealthRecyclerViewConfiguration().setConfig(recyclerView , HealthCenterActivity.this , healthLists , keys);
            }

            @Override
            public void DataInInserted() {

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
