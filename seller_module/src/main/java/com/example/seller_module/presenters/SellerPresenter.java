package com.example.seller_module.presenters;

import com.example.seller_module.apis.MercadoLibreApi;
import com.example.seller_module.model.Seller;
import com.example.seller_module.views.interfaces.SellerView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SellerPresenter {

    private SellerView mSellerView;

    public SellerPresenter(SellerView sellerView) {
        mSellerView = sellerView;
    }

    public void loadSellerInfo(String sellerId) {
        Call<Seller> call = MercadoLibreApi.getApi().getSellerInfo(sellerId);
        call.enqueue(new Callback<Seller>() {
            @Override
            public void onResponse(Call<Seller> call, Response<Seller> response) {
                if (response.isSuccessful()) {
                    Seller sellerInfo = response.body();
                    mSellerView.onLoadSellerInfoSuccess(sellerInfo);

                } else {
                    mSellerView.onLoadSellerInfoError();
                }
            }

            @Override
            public void onFailure(Call<Seller> call, Throwable t) {
                mSellerView.onLoadSellerInfoError();
            }
        });
    }
}