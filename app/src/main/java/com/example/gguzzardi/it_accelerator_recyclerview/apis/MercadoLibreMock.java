package com.example.gguzzardi.it_accelerator_recyclerview.apis;

import com.example.gguzzardi.it_accelerator_recyclerview.model.ItemDetails;
import com.example.gguzzardi.it_accelerator_recyclerview.model.ItemList;

import retrofit2.Call;

public class MercadoLibreMock implements MercadolibreService {


    @Override
    public Call<ItemList> getItemsByQuery(String query) {
        return null;
    }

    @Override
    public Call<ItemDetails> getItemDetailsById(String id) {
        return null;
    }
}
