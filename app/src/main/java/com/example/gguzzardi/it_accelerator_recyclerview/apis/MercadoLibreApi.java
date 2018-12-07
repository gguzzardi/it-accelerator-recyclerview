package com.example.gguzzardi.it_accelerator_recyclerview.apis;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MercadoLibreApi {
    private static Retrofit sRetrofit;
    private static final String BASE_URL = "https://api.mercadolibre.com/";

    public static Retrofit getApi() {
        if (sRetrofit == null) {
            sRetrofit = new retrofit2.Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return sRetrofit;
    }
}
