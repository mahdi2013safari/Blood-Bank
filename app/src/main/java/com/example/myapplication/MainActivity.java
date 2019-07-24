package com.example.myapplication;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewFlipper;
import com.google.firebase.database.*;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    TextView tv;
    ViewFlipper vf;
    Fragment address_fragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv = findViewById(R.id.health_id);
        DatabaseReference database;
        database = FirebaseDatabase.getInstance().getReference();
        database = database.child("health_app");
        String abc = database.getKey();

        loadFragment(new StoreFragment());

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);

    }

    private boolean loadFragment(Fragment fragment) {
        //switching fragment
        if (fragment != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.frame_layout, fragment)
                    .commit();
            return true;
        }
        return false;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        Fragment fragment = null;
        switch (menuItem.getItemId()) {
            case R.id.stores:
                fragment = new StoreFragment();
                break;
            case R.id.address:
                fragment = new AddressFragment();
                break;
            case R.id.jobs_and_scholarships:
                fragment = new JobsFragment();
                break;
            case R.id.products:
                fragment = new ProductsFragment();
                break;
            case R.id.informatics:
                fragment = new InformationFragment();
                break;
        }

        return loadFragment(fragment);
    }

}
