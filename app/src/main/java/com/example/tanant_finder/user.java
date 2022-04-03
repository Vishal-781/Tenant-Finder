package com.example.tanant_finder;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class user {

    private String property_type;
    private String address;
    private int rent;
    private int contact_no;

    public user(String dls, String adrs, String charge, String phn_no) {
    }

    public user(String property_type, String address,int rent, int contact_no) {
        this.property_type = property_type;
        this.address = address;
        this.rent = rent;
    }

    public String getProperty_type() {
        return property_type;
    }

    public String getAddress() {
        return address;
    }

    public int  getRent() {
        return rent;
    }

    public int  getContact_no() {
        return contact_no;
    }
}
