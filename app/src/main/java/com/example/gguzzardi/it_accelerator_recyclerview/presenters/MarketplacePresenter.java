package com.example.gguzzardi.it_accelerator_recyclerview.presenters;

import com.example.gguzzardi.it_accelerator_recyclerview.apis.MercadoLibreApi;
import com.example.gguzzardi.it_accelerator_recyclerview.model.Item;
import com.example.gguzzardi.it_accelerator_recyclerview.model.ItemList;
import com.example.gguzzardi.it_accelerator_recyclerview.model.Marketplace;
import com.example.gguzzardi.it_accelerator_recyclerview.views.interfaces.MarketplaceItemsView;
import com.example.gguzzardi.it_accelerator_recyclerview.views.recyclerviews.adapters.MarketplaceItemsAdapter;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MarketplacePresenter implements MarketplaceItemsAdapter.MarketplaceItemsListener {

    private MarketplaceItemsView mMarketplaceView;
    private Marketplace mMarketplace;

    public MarketplacePresenter(MarketplaceItemsView view, Marketplace marketplace) {
        mMarketplaceView = view;
        mMarketplace = marketplace;
    }

    public void loadMarketplace(String itemsQuery) {
        Call<ItemList> call = MercadoLibreApi.getApi().getItemsByQuery(itemsQuery);
        call.enqueue(new Callback<ItemList>() {
            @Override
            public void onResponse(Call<ItemList> call, Response<ItemList> response) {
                if (response.isSuccessful()) {
                    mMarketplace.setItems(response.body().getItems());
                    mMarketplaceView.onLoadItemsSuccess();
                } else {
                    mMarketplaceView.onLoadItemsError();
                }
            }

            @Override
            public void onFailure(Call<ItemList> call, Throwable t) {
                mMarketplaceView.onLoadItemsError();
            }
        });
    }

    public List<Item> getItems() {
        return mMarketplace.getItems();
    }

    @Override
    public void onItemClicked(String itemId) {
        mMarketplaceView.onItemClicked(itemId);
    }
}
