package com.example.gguzzardi.it_accelerator_recyclerview.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ItemList {
    @SerializedName("results")
    private List<Item> mItems;

    public List<Item> getItems() {
        return mItems;
    }
}
