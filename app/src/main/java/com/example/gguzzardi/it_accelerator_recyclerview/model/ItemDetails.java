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

    @SerializedName("original_price")
    private Double mBasePrice;

    @SerializedName("sold_quantity")
    private Integer mSoldQuantity;

    @SerializedName("available_quantity")
    private Integer mAvailableQuantity;

    @SerializedName("permalink")
    private String mLinkToMeli;

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

    public Integer getSoldQuantity() {
        return mSoldQuantity;
    }

    public String getLinkToMeli() {
        return mLinkToMeli;
    }

    public Integer getAvailableQuantity() {
        return mAvailableQuantity;
    }
}
