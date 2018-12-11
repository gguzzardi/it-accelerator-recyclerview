package com.example.seller_module.model;

import com.google.gson.annotations.SerializedName;

public class RatingsData {

    @SerializedName("negative")
    private Double mNegatives;

    @SerializedName("neutral")
    private Double mNeutrals;

    @SerializedName("positive")
    private Double mPositives;

    public Double getNegatives() {
        return mNegatives;
    }

    public Double getNeutrals() {
        return mNeutrals;
    }

    public Double getPositives() {
        return mPositives;
    }
}
