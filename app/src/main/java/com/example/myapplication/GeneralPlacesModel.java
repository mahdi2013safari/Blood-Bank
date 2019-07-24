package com.example.myapplication;

public class GeneralPlacesModel {
    String place_name;
    String place_phone;
    String place_address;
    int thumbnail;

    public GeneralPlacesModel() {

    }

    public GeneralPlacesModel(String place_name, String place_phone, String place_address, int thumbnail) {
        this.place_name = place_name;
        this.place_phone = place_phone;
        this.place_address = place_address;
        this.thumbnail = thumbnail;
    }

    public String getPlace_name() {
        return place_name;
    }

    public void setPlace_name(String place_name) {
        this.place_name = place_name;
    }

    public String getPlace_phone() {
        return place_phone;
    }

    public void setPlace_phone(String place_phone) {
        this.place_phone = place_phone;
    }

    public String getPlace_address() {
        return place_address;
    }

    public void setPlace_address(String place_address) {
        this.place_address = place_address;
    }

    public int getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(int thumbnail) {
        this.thumbnail = thumbnail;
    }
}
