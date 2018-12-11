package com.example.seller_module.apis;

import com.example.seller_module.model.Seller;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
public interface MercadolibreService {

    @GET("/users/{id}")
    Call<Seller> getSellerInfo(@Path("id") String id);

}
