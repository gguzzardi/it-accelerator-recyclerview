package com.example.gguzzardi.it_accelerator_recyclerview.apis;

import com.example.gguzzardi.it_accelerator_recyclerview.model.ItemDetails;
import com.example.gguzzardi.it_accelerator_recyclerview.model.ItemList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MercadolibreService {

    @GET("/sites/MLA/search")
    Call<ItemList> getItemsByQuery(@Query("q") String query);

    @GET("items/{id}")
    Call<ItemDetails> getItemDetailsById(@Path("id") String id);

}
