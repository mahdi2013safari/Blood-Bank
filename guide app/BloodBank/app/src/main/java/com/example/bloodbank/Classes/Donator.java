package com.example.bloodbank.Classes;

public class Donator {

    public String firstname;
    public String lastname;
    public String bloodGroup;
    public String phone;
    public String address;

    public Donator()
    {

    }

    public Donator(String firstname, String lastname, String bloodGroup, String phone, String address){
        this.firstname = firstname;
        this.lastname = lastname;
        this.bloodGroup = bloodGroup;
        this.phone = phone;
        this.address = address;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getBloodGroup() {
        return bloodGroup;
    }

    public void setBloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }






}
