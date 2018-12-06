package com.example.gguzzardi.it_accelerator_recyclerview.views.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.widget.Toast;

import com.example.gguzzardi.it_accelerator_recyclerview.R;
import com.example.gguzzardi.it_accelerator_recyclerview.model.Marketplace;
import com.example.gguzzardi.it_accelerator_recyclerview.model.mocks.MockedMarketplace;
import com.example.gguzzardi.it_accelerator_recyclerview.presenters.MarketplacePresenter;
import com.example.gguzzardi.it_accelerator_recyclerview.views.interfaces.MarketplaceItemsView;
import com.example.gguzzardi.it_accelerator_recyclerview.views.recyclerviews.adapters.MarketplaceItemsAdapter;

import jp.wasabeef.recyclerview.animators.SlideInUpAnimator;

public class MainActivity extends AppCompatActivity implements MarketplaceItemsView {

    private MarketplacePresenter mMarketplacePresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mMarketplacePresenter = new MarketplacePresenter(this, new MockedMarketplace());

        setupRecyclerView();
    }

    private void setupRecyclerView() {
        RecyclerView itemsRecyclerView = findViewById(R.id.rv_marketplace);

        MarketplaceItemsAdapter adapter = new MarketplaceItemsAdapter(mMarketplacePresenter.getMarketplaceItems(), mMarketplacePresenter);
        itemsRecyclerView.setAdapter(adapter);
        itemsRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        StaggeredGridLayoutManager gridLayoutManager =
                new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        itemsRecyclerView.setLayoutManager(gridLayoutManager);

        itemsRecyclerView.setItemAnimator(new SlideInUpAnimator());

        itemsRecyclerView.setHasFixedSize(true);
    }

    @Override
    public void onItemClicked() {
        Toast.makeText(this, "Item clicked", Toast.LENGTH_SHORT).show();
    }
}
