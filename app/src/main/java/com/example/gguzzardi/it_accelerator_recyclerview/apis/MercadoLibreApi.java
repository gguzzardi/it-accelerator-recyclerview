package com.example.gguzzardi.it_accelerator_recyclerview.apis;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MercadoLibreApi {
    private static MercadolibreService sMercadoLibreService;
    private static final String BASE_URL = "https://api.mercadolibre.com";

    public static MercadolibreService getApi() {
        if (sMercadoLibreService == null) {
            Retrofit retrofit = new retrofit2.Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            sMercadoLibreService = retrofit.create(MercadolibreService.class);
        }
        return sMercadoLibreService;
    }
}
