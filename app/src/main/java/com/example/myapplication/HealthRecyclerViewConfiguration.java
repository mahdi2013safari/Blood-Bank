package com.example.myapplication;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class HealthRecyclerViewConfiguration {
    Context context;
    private HealthAdapter healthAdapter;

    public void setConfig(RecyclerView recyclerView , Context context , List<HealthCentersModel> healthCentersModels  , List<String> keys ) {
        this.context = context;
        this.healthAdapter = new HealthAdapter(healthCentersModels , keys);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(this.healthAdapter);
    }


    public class HealthItemView extends RecyclerView.ViewHolder {
        private TextView name , phone , address;
        private String key;




        public HealthItemView(ViewGroup viewGroup) {
            super(LayoutInflater.from(context).inflate(R.layout.educational_centers_lists  , viewGroup , false));

            name = itemView.findViewById(R.id.edu_center_name);
            phone = itemView.findViewById(R.id.edu_center_phone);
            address = itemView.findViewById(R.id.edu_center_address);
        }

        public void Bind(HealthCentersModel healthCentersModel , String key) {
            name.setText(healthCentersModel.getName());
            phone.setText(healthCentersModel.getPhone());
            address.setText(healthCentersModel.getAddress());
            this.key = key;
        }
    }

    public class HealthAdapter extends RecyclerView.Adapter<HealthItemView> {
        private List<HealthCentersModel> healthCentersModelList;
        private List<String> keys;

        public HealthAdapter(List<HealthCentersModel> healthCentersModelList , List<String> keys) {
            this.healthCentersModelList = healthCentersModelList;
            this.keys = keys;
        }


        @NonNull
        @Override
        public HealthItemView onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            return new HealthItemView(viewGroup);
        }

        @Override
        public void onBindViewHolder(@NonNull HealthItemView healthItemView, int i) {
                healthItemView.Bind(healthCentersModelList.get(i) , keys.get(i));
        }

        @Override
        public int getItemCount() {
            return healthCentersModelList.size();
        }
    }
}
