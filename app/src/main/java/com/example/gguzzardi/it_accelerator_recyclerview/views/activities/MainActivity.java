package com.example.gguzzardi.it_accelerator_recyclerview.views.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import com.example.gguzzardi.it_accelerator_recyclerview.R;
import com.example.gguzzardi.it_accelerator_recyclerview.model.Marketplace;
import com.example.gguzzardi.it_accelerator_recyclerview.views.activities.recyclerviews.adapters.MarketplaceItemsAdapter;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupRecyclerView();
    }

    private void setupRecyclerView() {
        RecyclerView itemsRecyclerView = findViewById(R.id.rv_marketplace);

        Marketplace marketplace = new Marketplace();

        MarketplaceItemsAdapter adapter = new MarketplaceItemsAdapter(marketplace.getItems());
        itemsRecyclerView.setAdapter(adapter);
        itemsRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        StaggeredGridLayoutManager gridLayoutManager =
                new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        itemsRecyclerView.setLayoutManager(gridLayoutManager);

        itemsRecyclerView.setHasFixedSize(true);
    }
}
