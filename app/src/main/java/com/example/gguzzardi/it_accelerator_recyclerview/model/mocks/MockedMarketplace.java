package com.example.gguzzardi.it_accelerator_recyclerview.model.mocks;

import com.example.gguzzardi.it_accelerator_recyclerview.model.Item;
import com.example.gguzzardi.it_accelerator_recyclerview.model.Marketplace;

import java.util.List;
import java.util.Random;

public class MockedMarketplace extends Marketplace {

    public MockedMarketplace() {
        super();
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
        Random rand = new Random();
        Item item = new Item();
        item.setName(name);
        item.setDescription("Descripcion de item de ejemplo");
        item.setPrice(getRandomPrice());
        item.setImagePath("https://frescolib.org/static/images/docs/01-scaletypes-sample-1.png");

        if (rand.nextInt(2) == 1) {
            item.setDiscount(10);
        } else {
            item.setDiscount(0);
        }

        return item;
    }

    private double getRandomPrice() {
        Random rand = new Random();
        double rangeMin = 5.0;
        double rangeMax = 5000.0;
        return rangeMin + (rangeMax - rangeMin) * rand.nextDouble();
    }
}
