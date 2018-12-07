package com.example.gguzzardi.it_accelerator_recyclerview.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;


public class Marketplace {

    protected List<Item> mItems;

    public Marketplace() {
        mItems = new ArrayList<>();
    }

    public List<Item> getItems() {
        return mItems;
    }

    public void setItems(List<Item> items) {
        mItems = items;
    }
}
