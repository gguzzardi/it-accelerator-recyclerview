package com.example.seller_module.model;

import com.google.gson.annotations.SerializedName;

public class SellerTransactionsData {

    @SerializedName("canceled")
    private Integer mCancelled;

    @SerializedName("completed")
    private Integer mCompleted;

    @SerializedName("ratings")
    private RatingsData mRatingsData;

    @SerializedName("total")
    private Integer mTotalAmount;

    public Integer getCancelled() {
        return mCancelled;
    }

    public Integer getCompleted() {
        return mCompleted;
    }

    public RatingsData getRatingsData() {
        return mRatingsData;
    }

    public Integer getTotalAmount() {
        return mTotalAmount;
    }
}
