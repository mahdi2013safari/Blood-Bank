package com.example.bloodbank;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ActionBar;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bloodbank.Classes.Donator;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

public class SearchActivity extends AppCompatActivity {

    DatabaseReference mDatabase;
    RecyclerView recyclerView;
    FirebaseRecyclerAdapter<Donator,DonatorViewHolder>adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        recyclerView = findViewById(R.id.search_recycleView);
        final ProgressBar progressBar = findViewById(R.id.progressBarSearch);
        progressBar.setVisibility(View.VISIBLE);
        mDatabase = FirebaseDatabase.getInstance().getReference().child("bloodDonator");
        mDatabase.keepSynced(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));




        // Radio Group Button
        final RadioGroup radioGroupButtonBloodGroup  = findViewById(R.id.radioButtonBloodGroup);
        final RadioButton checkedRadioButton  = radioGroupButtonBloodGroup.findViewById(radioGroupButtonBloodGroup.getCheckedRadioButtonId());
        radioGroupButtonBloodGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton checkedRadioButton  = radioGroupButtonBloodGroup.findViewById(checkedId);
                boolean isChecked = checkedRadioButton.isChecked();
                String bloodGroup = "";
                if(isChecked)
                {
                    switch(checkedId)
                    {
                        case R.id.group_blood_a:
                            bloodGroup = "A";
                            break;
                        case R.id.group_blood_b:
                            bloodGroup = "B";
                            break;
                        case R.id.group_blood_ab:
                            bloodGroup = "AB";
                            break;
                        case R.id.group_blood_o:
                            bloodGroup = "O";
                            break;
                    }
                    final Query querySingle = mDatabase.orderByChild("bloodGroup").equalTo("A").limitToFirst(1);
                    querySingle.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            for (DataSnapshot dataSnapshot1: dataSnapshot.getChildren()) {
                                final String address = (String) dataSnapshot1.child("address").getValue();
                                final String bloodGroup = (String) dataSnapshot1.child("bloodGroup").getValue();
                                final String firstname = (String) dataSnapshot1.child("firstname").getValue();
                                String lastname = (String) dataSnapshot1.child("lastname").getValue();
                                String phone = (String) dataSnapshot1.child("phone").getValue();
                                FirebaseRecyclerOptions<Donator> options =
                                        new FirebaseRecyclerOptions.Builder<Donator>()
                                                .setQuery(querySingle, Donator.class)
                                                .build();

                                adapter = new FirebaseRecyclerAdapter<Donator, DonatorViewHolder>(options) {
                                    @Override
                                    protected void onBindViewHolder(@NonNull DonatorViewHolder viewHolder, int i, @NonNull Donator donator) {
                                        viewHolder.setAddress(address);
                                        viewHolder.setBloodGroup(bloodGroup);
                                        viewHolder.setFirstname(firstname);
                                    }

                                    @NonNull
                                    @Override
                                    public DonatorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                                        View view = LayoutInflater.from(parent.getContext())
                                                .inflate(R.layout.search_card_adapter, parent, false);

                                        return new DonatorViewHolder(view);
                                    }

                                    @Override
                                    public void onDataChanged() {
                                        super.onDataChanged();
                                        progressBar.setVisibility(View.INVISIBLE);
                                    }
                                };
                                recyclerView.setAdapter(adapter);
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {
                            Toast.makeText(SearchActivity.this, "Data Not found", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });


        Query query = FirebaseDatabase.getInstance()
                .getReference()
                .child("bloodDonator")
                .limitToLast(50);

        FirebaseRecyclerOptions<Donator> options =
                new FirebaseRecyclerOptions.Builder<Donator>()
                        .setQuery(query, Donator.class)
                        .build();

         adapter = new FirebaseRecyclerAdapter<Donator, DonatorViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull DonatorViewHolder viewHolder, int i, @NonNull Donator donator) {
                viewHolder.setAddress(donator.getAddress());
                viewHolder.setBloodGroup(donator.getBloodGroup());
                viewHolder.setFirstname(donator.getFirstname());
            }

            @NonNull
            @Override
            public DonatorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.search_card_adapter, parent, false);

                return new DonatorViewHolder(view);
            }

             @Override
             public void onDataChanged() {
                 super.onDataChanged();
                 progressBar.setVisibility(View.INVISIBLE);
             }
         };
         recyclerView.setAdapter(adapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    public static class DonatorViewHolder extends RecyclerView.ViewHolder
    {
        View myView;
        private static final int MAX_WIDTH = 200;
        private static final int MAX_HEIGHT = 200;
        Context mContext;
        Donator donator;
        public TextView fname, address, bloodGroup;

        public DonatorViewHolder(@NonNull View itemView) {
            super(itemView);
            myView = itemView;
            fname = myView.findViewById(R.id.txt_name_donator);
            address = myView.findViewById(R.id.txt_donator_address);
            bloodGroup = myView.findViewById(R.id.txt_adapter_blood_group);
        }

        public void setFirstname (String fnameString)
        {
            fname.setText(fnameString);
        }

        public void setAddress(String addressString)
        {
            address.setText(addressString);
        }
        public void setBloodGroup(String bloodGroupString)
        {
            bloodGroup.setText(bloodGroupString);
        }


    }
}
