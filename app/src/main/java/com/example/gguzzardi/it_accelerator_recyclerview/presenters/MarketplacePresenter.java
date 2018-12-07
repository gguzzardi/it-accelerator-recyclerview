package com.example.gguzzardi.it_accelerator_recyclerview.presenters;

import com.example.gguzzardi.it_accelerator_recyclerview.model.Item;
import com.example.gguzzardi.it_accelerator_recyclerview.model.Marketplace;
import com.example.gguzzardi.it_accelerator_recyclerview.views.interfaces.MarketplaceItemsView;
import com.example.gguzzardi.it_accelerator_recyclerview.views.recyclerviews.adapters.MarketplaceItemsAdapter;

import java.util.List;

public class MarketplacePresenter implements MarketplaceItemsAdapter.MarketplaceItemsListener {

    private MarketplaceItemsView mMarketplaceView;
    private Marketplace mMarketplace;

    public MarketplacePresenter(MarketplaceItemsView view, Marketplace marketplace) {
        mMarketplaceView = view;
        mMarketplace = marketplace;
    }

    public List<Item> loadMarketplaceItems() {
        return mMarketplace.getItems();
    }

    @Override
    public void onItemClicked() {
        mMarketplaceView.onItemClicked();
    }
}
