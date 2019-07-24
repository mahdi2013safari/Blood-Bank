package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

public class AddressFragment extends Fragment implements View.OnClickListener {

    CardView educational_card_view , health_center_card_view , general_places_card_view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_address , container , false);
        educational_card_view = v.findViewById(R.id.educational_center_click_event);
        health_center_card_view = v.findViewById(R.id.health_center_event_click);
        general_places_card_view = v.findViewById(R.id.general_places_id);
        general_places_card_view.setOnClickListener(this);
        educational_card_view.setOnClickListener(this);
        health_center_card_view.setOnClickListener(this);
        return v;
    }

    @Override
    public void onClick(View v) {
        Intent i;
        switch (v.getId()){
            case R.id.educational_center_click_event:
                i = new Intent(this.getActivity() , EducationalCenterActivity.class);
                startActivity(i);
                break;
            case R.id.health_center_event_click:
                i = new Intent(this.getActivity() , HealthCenterActivity.class );
                startActivity(i);
                break;
            case R.id.general_places_id:
                i = new Intent(this.getActivity() , HealthActivity.class);
                startActivity(i);
                break;
        }
    }
}
