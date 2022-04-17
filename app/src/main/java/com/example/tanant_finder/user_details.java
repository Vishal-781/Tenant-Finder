package com.example.tanant_finder;

public class user_details {
    private  String usernme,emailc,passwordc;
    private String profile_dp;

    public user_details() {
    }

    public String getProfile_dp() {
        return profile_dp;
    }

    public void setProfile_dp(String profile_dp) {
        this.profile_dp = profile_dp;
    }

    public user_details(String username, String email, String password) {
        this.usernme = username;
        this.emailc = email;
        this.passwordc = password;
    }

    public String getUsernme() {
        return usernme;
    }



    public String getEmailc() {
        return emailc;
    }



    public String getPasswordc() {
        return passwordc;
    }

    public void setPasswordc(String passwordc) {
        this.passwordc = passwordc;
    }
}
