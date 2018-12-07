package com.example.gguzzardi.it_accelerator_recyclerview.model;

import com.google.gson.annotations.SerializedName;

public class Item {

    @SerializedName("id")
    private String mId;

    @SerializedName("thumbnail")
    private String mImagePath;

    @SerializedName("title")
    private String mTitle;

    @SerializedName("price")
    private Double mPrice;

    private Integer mDiscount = 0;

    private String mDescription;

    public String getId() {
        return mId;
    }

    public void setId(String id) {
        mId = id;
    }

    public String getImagePath() {
        return mImagePath;
    }

    public void setImagePath(String imagePath) {
        mImagePath = imagePath;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public double getPrice() {
        return mPrice;
    }

    public void setPrice(double price) {
        mPrice = price;
    }

    public int getDiscount() {
        return mDiscount;
    }

    public void setDiscount(int discount) {
        mDiscount = discount;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        mDescription = description;
    }
}
