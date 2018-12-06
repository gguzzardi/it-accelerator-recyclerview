package com.example.gguzzardi.it_accelerator_recyclerview.model;

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
}
