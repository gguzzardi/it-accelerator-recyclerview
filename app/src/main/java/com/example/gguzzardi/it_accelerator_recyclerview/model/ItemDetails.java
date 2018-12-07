package com.example.gguzzardi.it_accelerator_recyclerview.model;

import com.google.gson.annotations.SerializedName;

public class ItemDetails {

    @SerializedName("id")
    private String mId;

    @SerializedName("title")
    private String mTitle;

    @SerializedName("price")
    private Double mPrice;

    @SerializedName("base_price")
    private Double mBasePrice;

    @SerializedName("available_quantity")
    private Integer mAvailableQuantity;

    public String getId() {
        return mId;
    }

    public String getTitle() {
        return mTitle;
    }

    public Double getPrice() {
        return mPrice;
    }

    public Double getBasePrice() {
        return mBasePrice;
    }

    public Integer getAvailableQuantity() {
        return mAvailableQuantity;
    }
}
