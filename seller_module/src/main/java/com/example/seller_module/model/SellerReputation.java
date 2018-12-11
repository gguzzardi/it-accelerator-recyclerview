package com.example.seller_module.model;

import com.google.gson.annotations.SerializedName;

public class SellerReputation {

    @SerializedName("power_seller_status")
    private String mSellerStatus;

    @SerializedName("transactions")
    private SellerTransactionsData mTransactions;

    public String getSellerStatus() {
        return mSellerStatus;
    }

    public SellerTransactionsData getTransactions() {
        return mTransactions;
    }
}
