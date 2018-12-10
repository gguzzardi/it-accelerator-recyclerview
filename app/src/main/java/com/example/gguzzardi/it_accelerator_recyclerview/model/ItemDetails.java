package com.example.gguzzardi.it_accelerator_recyclerview.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ItemDetails {

    @SerializedName("id")
    private String mId;

    @SerializedName("pictures")
    private List<PictureData> mItemImages;

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

    public List<PictureData> getItemImages() {
        return mItemImages;
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
