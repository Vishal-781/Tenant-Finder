package com.example.tanant_finder;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class user {

    private String property_type = "";
    private String address = "";
    private String rent = "";
    private String contact_no = "";

    public user() {

    }

    public user(String property_type, String address,String rent, String contact_no) {
        this.property_type = property_type;
        this.address = address;
        this.rent = rent;
        this.contact_no = contact_no;
    }

    public String getProperty_type() {
        return property_type;
    }

    public String getAddress() {
        return address;
    }

    public String  getRent() {
        return rent;
    }

    public String  getContact_no() {
        return contact_no;
    }
}
