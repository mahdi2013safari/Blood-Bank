package com.example.bloodbank;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ImageView image_donate_blood = findViewById(R.id.imageViewDonator);
        ImageView image_search_blood = findViewById(R.id.imageView_search_blood);
        image_search_blood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HomeActivity.this,SearchActivity.class);
                startActivity(i);
            }
        });

        image_donate_blood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HomeActivity.this,DonatorRegisterActivity.class);
                startActivity(i);
            }
        });
    }
}
