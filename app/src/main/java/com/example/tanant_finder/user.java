package com.example.tanant_finder;

import android.net.Uri;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class user {

    private String property_type = "";
    private String address = "";
    private String rent = "";
    private String contact_no = "";
    private String imageV="https://www.google.com/url?sa=i&url=https%3A%2F%2Fphotos.com%2F&psig=AOvVaw3Kmky3mCMwOLr5Vacs0kH5&ust=1650361428466000&source=images&cd=vfe&ved=0CAwQjRxqFwoTCNDy3cqpnfcCFQAAAAAdAAAAABAD";






    public user() {

    }

    public user(String property_type, String address, String rent, String contact_no,String imageV) {
        this.property_type = property_type;
        this.address = address;
        this.rent = rent;
        this.contact_no = contact_no;
        this.imageV=imageV;


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
    public String getImageV() {
        return imageV;
    }

}