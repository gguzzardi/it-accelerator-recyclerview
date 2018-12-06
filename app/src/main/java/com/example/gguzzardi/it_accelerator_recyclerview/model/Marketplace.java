package com.example.gguzzardi.it_accelerator_recyclerview.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Marketplace {

    private List<Item> mItems;

    public Marketplace() {
        mItems = new ArrayList<>();
        createDummyItems();
    }

    public List<Item> getItems() {
        return mItems;
    }

    private void createDummyItems() {
        for (int i = 0; i < 15; i++) {
            Item item = createRandomItem("Item " + i);
            mItems.add(item);
        }
    }

    private Item createRandomItem(String name) {
        Item item = new Item();
        item.setName(name);
        item.setDescription("Descripcion de item de ejemplo");

        Random rand = new Random();
        return item;
    }
}
