package com.example.gguzzardi.it_accelerator_recyclerview.apis;

import com.example.gguzzardi.it_accelerator_recyclerview.model.Item;
import com.example.gguzzardi.it_accelerator_recyclerview.model.ItemList;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MercadolibreService {

    @GET("/sites/MLA/search")
    Call<ItemList> getItemsByQuery(@Query("q") String query);

    @GET("items/{id}")
    Call<Item> getItemById(@Path("id") Long id);

}
