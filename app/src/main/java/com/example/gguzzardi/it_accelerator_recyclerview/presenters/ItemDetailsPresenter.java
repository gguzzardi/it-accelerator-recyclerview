package com.example.gguzzardi.it_accelerator_recyclerview.presenters;

import com.example.gguzzardi.it_accelerator_recyclerview.apis.MercadoLibreApi;
import com.example.gguzzardi.it_accelerator_recyclerview.model.ItemDetails;
import com.example.gguzzardi.it_accelerator_recyclerview.views.interfaces.ItemDetailsView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ItemDetailsPresenter {

    private ItemDetailsView mItemDetailsView;

    public ItemDetailsPresenter(ItemDetailsView view) {
        mItemDetailsView = view;
    }

    public void loadItemDetails(String itemId) {
        Call<ItemDetails> call = MercadoLibreApi.getApi().getItemDetailsById(itemId);
        call.enqueue(new Callback<ItemDetails>() {
            @Override
            public void onResponse(Call<ItemDetails> call, Response<ItemDetails> response) {
                if (response.isSuccessful()) {
                    ItemDetails itemDetails = response.body();
                    mItemDetailsView.onLoadItemDetailsSuccess(itemDetails);

                } else {
                    mItemDetailsView.onLoadItemDetailsError();
                }
            }

            @Override
            public void onFailure(Call<ItemDetails> call, Throwable t) {
                mItemDetailsView.onLoadItemDetailsError();
            }
        });
    }
}
