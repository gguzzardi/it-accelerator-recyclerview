package com.example.gguzzardi.it_accelerator_recyclerview.views.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.ProgressBar;

import com.example.gguzzardi.it_accelerator_recyclerview.R;
import com.example.gguzzardi.it_accelerator_recyclerview.model.Marketplace;
import com.example.gguzzardi.it_accelerator_recyclerview.presenters.MarketplacePresenter;
import com.example.gguzzardi.it_accelerator_recyclerview.views.interfaces.MarketplaceItemsView;
import com.example.gguzzardi.it_accelerator_recyclerview.views.recyclerviews.adapters.MarketplaceItemsAdapter;

import jp.wasabeef.recyclerview.animators.SlideInUpAnimator;

public class MainActivity extends AppCompatActivity implements MarketplaceItemsView {

    private ProgressBar mProgressBar;
    private RecyclerView mRecyclerView;

    private MarketplacePresenter mMarketplacePresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mProgressBar = findViewById(R.id.pb_items);
        mRecyclerView = findViewById(R.id.rv_marketplace);
        mMarketplacePresenter = new MarketplacePresenter(this, new Marketplace());

        setupRecyclerView();
        loadItems();
    }

    private void setupRecyclerView() {
        RecyclerView itemsRecyclerView = findViewById(R.id.rv_marketplace);

        StaggeredGridLayoutManager gridLayoutManager =
                new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        itemsRecyclerView.setLayoutManager(gridLayoutManager);

        itemsRecyclerView.setItemAnimator(new SlideInUpAnimator());

        itemsRecyclerView.setHasFixedSize(true);
    }

    private void loadItems() {
        showProgressBar();
        mMarketplacePresenter.loadMarketplace("Celulares");
    }

    @Override
    public void onItemClicked(String itemId) {
        Intent openItemDetailsActivityIntent = new Intent(this, ItemDetailsActivity.class);
        openItemDetailsActivityIntent.putExtra(ItemDetailsActivity.EXTRA_ITEM_ID, itemId);
        startActivity(openItemDetailsActivityIntent);
    }

    private void showProgressBar() {
        mProgressBar.setVisibility(View.VISIBLE);
        mRecyclerView.setVisibility(View.GONE);
    }

    private void hideProgressBar() {
        mProgressBar.setVisibility(View.GONE);
        mRecyclerView.setVisibility(View.VISIBLE);
    }

    @Override
    public void onLoadItemsSuccess() {
        RecyclerView itemsRecyclerView = findViewById(R.id.rv_marketplace);
        MarketplaceItemsAdapter adapter = new MarketplaceItemsAdapter(mMarketplacePresenter.getItems(), mMarketplacePresenter);
        itemsRecyclerView.setAdapter(adapter);
        hideProgressBar();
    }

    @Override
    public void onLoadItemsError() {
        hideProgressBar();
    }
}
