package com.example.myapplication;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class RecyclerViewConfig  {
        private Context context;

        private EducationalCenterAdapter educationalCenterAdapter;

        public void setConfig(RecyclerView recyclerView , Context context , List<EducationalCenters> educationalCenters , List<String>  keys) {
            this.context = context;
            this.educationalCenterAdapter = new EducationalCenterAdapter(educationalCenters , keys);
            recyclerView.setLayoutManager(new LinearLayoutManager(context));
            recyclerView.setAdapter(this.educationalCenterAdapter);
        }

        private class EducationalCenterItemView extends RecyclerView.ViewHolder {
            private TextView edu_name ,edu_phone, edu_address;
            private String key;

            public EducationalCenterItemView(ViewGroup parent) {
                 super(LayoutInflater.from(context).inflate(R.layout.educational_centers_lists , parent, false));

                 edu_name = itemView.findViewById(R.id.edu_center_name);
                 edu_phone = itemView.findViewById(R.id.edu_center_phone);
                 edu_address = itemView.findViewById(R.id.edu_center_address);
            }

            public void bind(EducationalCenters educationalCenters , String key) {
                edu_name.setText(educationalCenters.getName());
                edu_phone.setText(educationalCenters.getPhone());
                edu_address.setText(educationalCenters.getAddress());
                this.key = key;
            }

        }

        class  EducationalCenterAdapter extends RecyclerView.Adapter<EducationalCenterItemView> {

                private List<EducationalCenters> eduList;
                private List<String> eduKeys;

                public EducationalCenterAdapter(List<EducationalCenters> eduList , List<String> eduKeys) {
                    this.eduList = eduList;
                    this.eduKeys = eduKeys;
                }


            @NonNull
            @Override
            public EducationalCenterItemView onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
                return new EducationalCenterItemView(viewGroup);
            }

            @Override
            public void onBindViewHolder(@NonNull EducationalCenterItemView educationalCenterItemView, int i) {
                    educationalCenterItemView.bind(eduList.get(i) , eduKeys.get(i));
            }

            @Override
            public int getItemCount() {
                return eduList.size();
            }
        }
}
