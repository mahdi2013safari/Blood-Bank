package com.example.bloodbank;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bloodbank.Classes.Donator;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DonatorRegisterActivity extends AppCompatActivity {

    private DatabaseReference mDatabase;
    FirebaseDatabase database;
    EditText ed_firstname,ed_lastname,ed_bloodGroup,ed_address,ed_phone;
    String key;
    String bloodGroup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donator_register);
        database = FirebaseDatabase.getInstance();
        key = database.getReference("donator").push().getKey();
        mDatabase = FirebaseDatabase.getInstance().getReference();

        final Spinner Bloodspinner = findViewById(R.id.bloodSpinner);
//        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
//                R.array.BloodGroup, android.R.layout.simple_spinner_item);
//        // Specify the layout to use when the list of choices appears
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        String[] BloodGroup = new String[]{
                "Choose Blood Group",
                "A",
                "B",
                "AB",
                "O",
        };

        List<String> listBloodGroup = new ArrayList<String>(Arrays.asList(BloodGroup));
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,R.layout.spinner_item,listBloodGroup){
            @Override
            public boolean isEnabled(int position) {
                return super.isEnabled(position);
            }

            @Override
            public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                View view =  super.getDropDownView(position, convertView, parent);
                TextView tv = (TextView) view;
                if(position==0) {
                    // Set the disable item text color
                    tv.setTextColor(Color.GRAY);
                }
                else {
                    tv.setTextColor(Color.BLACK);
                }
                return view;
            }

        };
        arrayAdapter.setDropDownViewResource(R.layout.spinner_item);
        Bloodspinner.setAdapter(arrayAdapter);



        Bloodspinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
               bloodGroup  =  Bloodspinner.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        // Apply the adapter to the spinner




        // initalize textField
        ed_firstname = findViewById(R.id.ed_firstname);
        ed_lastname = findViewById(R.id.ed_lastname);
        ed_address = findViewById(R.id.ed_address);
        ed_phone = findViewById(R.id.ed_phone);

        Button btn_submit = findViewById(R.id.btn_register_donator);
        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InsertDonator(ed_firstname.getText().toString(),ed_lastname.getText().toString(),bloodGroup,ed_address.getText().toString(),ed_phone.getText().toString());
                Toast.makeText(DonatorRegisterActivity.this, "Successfully Inserted", Toast.LENGTH_SHORT).show();
                Intent goToHome = new Intent(DonatorRegisterActivity.this,HomeActivity.class);
                startActivity(goToHome);
            }
        });
    }

    private void InsertDonator(String firstname, String lastname, String bloodGroup, String phone, String address){
        Donator donate = new Donator(firstname,lastname,bloodGroup,phone,address);
        mDatabase.child("bloodDonator").child(key).setValue(donate);
    }
}
