package com.example.gguzzardi.it_accelerator_recyclerview.model;

import com.google.gson.annotations.SerializedName;

public class PictureData {

    @SerializedName("secure_url")
    private String mUrl;

    public String getUrl() {
        return mUrl;
    }
}
