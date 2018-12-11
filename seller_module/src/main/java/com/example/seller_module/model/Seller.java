package com.example.seller_module.model;

import com.google.gson.annotations.SerializedName;

public class Seller {

    @SerializedName("nickname")
    private String mName;

    @SerializedName("address")
    private Address mAddress;

    @SerializedName("points")
    private Integer mPoints;

    @SerializedName("permalink")
    private String mPermalink;

    @SerializedName("seller_reputation")
    private SellerReputation mSellerReputation;

    public String getName() {
        return mName;
    }

    public Address getAddress() {
        return mAddress;
    }

    public Integer getPoints() {
        return mPoints;
    }

    public String getPermalink() {
        return mPermalink;
    }

    public SellerReputation getSellerReputation() {
        return mSellerReputation;
    }
}
