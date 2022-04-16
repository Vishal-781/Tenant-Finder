package com.example.tanant_finder;

public class Uploads {
    private String mName;
    private String mUrl;
    public Uploads(){
//        empty contructor needed
    }
    public Uploads(String name ,String url) {
        if (name.trim().equals("")) {
            name = "No name";
        }
        mName=name;
        mUrl=url;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public String getmUrl() {
        return mUrl;
    }

    public void setmUrl(String mUrl) {
        this.mUrl = mUrl;
    }
}
