package com.example.seller_module.model;

import com.google.gson.annotations.SerializedName;

public class Address {

    @SerializedName("city")
    private String mCity;

    public String getCity() {
        return mCity;
    }
}
