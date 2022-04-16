package com.example.tanant_finder;

import android.net.Uri;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class user {

    private String property_type = "";
    private String address = "";
    private String rent = "";
    private String contact_no = "";
//    private Uri Uri1;
//    private Uri Uri2;
//    private Uri Uri3;
//    private Uri Uri4;


    public user() {

    }

    public user(String property_type, String address, String rent, String contact_no) {
        this.property_type = property_type;
        this.address = address;
        this.rent = rent;
        this.contact_no = contact_no;
//        this.Uri1=Uri1;
//        this.Uri2=Uri2;
//        this.Uri3=Uri3;
//        this.Uri4=Uri4;
    }

    public String getProperty_type() {
        return property_type;
    }

    public String getAddress() {
        return address;
    }

    public String getRent() {
        return rent;
    }

    public String getContact_no() {
        return contact_no;
    }

//    public Uri getUri1() {
//
}