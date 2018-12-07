package com.example.gguzzardi.it_accelerator_recyclerview.model;

import com.google.gson.annotations.SerializedName;

public class Item {

    @SerializedName("id")
    private Long mId;

    @SerializedName("imagePath")
    private String mImagePath;

    @SerializedName("title")
    private String mTitle;

    @SerializedName("price")
    private Double mPrice;

    @SerializedName("discount")
    private Integer mDiscount;

    @SerializedName("description")
    private String mDescription;

    public long getId() {
        return mId;
    }

    public void setId(long id) {
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
