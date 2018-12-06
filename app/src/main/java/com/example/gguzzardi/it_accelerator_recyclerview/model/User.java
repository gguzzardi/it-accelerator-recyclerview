package com.example.gguzzardi.it_accelerator_recyclerview.model;

public class User {

    private String mEmail;
    private String mPassword;

    public User(String email, String password) {
        mEmail = email;
        mPassword = password;
    }

    public void setEmail(String email) {
        this.mEmail = email;
    }

    public void setPassword(String password) {
        this.mPassword = password;
    }
}
