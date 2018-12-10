package com.example.gguzzardi.it_accelerator_recyclerview.views.activities;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ProgressBar;
import android.widget.SearchView;

import com.example.gguzzardi.it_accelerator_recyclerview.R;
import com.example.gguzzardi.it_accelerator_recyclerview.model.Marketplace;
import com.example.gguzzardi.it_accelerator_recyclerview.preferenes.MeliPreferences;
import com.example.gguzzardi.it_accelerator_recyclerview.presenters.MarketplacePresenter;
import com.example.gguzzardi.it_accelerator_recyclerview.views.interfaces.MarketplaceItemsView;
import com.example.gguzzardi.it_accelerator_recyclerview.views.recyclerviews.adapters.MarketplaceItemsAdapter;

import jp.wasabeef.recyclerview.animators.SlideInUpAnimator;

public class MainActivity extends AppCompatActivity implements MarketplaceItemsView {

    private ProgressBar mProgressBar;
    private RecyclerView mRecyclerView;
    private SearchView mSearchView;

    private MarketplacePresenter mMarketplacePresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mProgressBar = findViewById(R.id.pb_items);
        mRecyclerView = findViewById(R.id.rv_marketplace);
        mSearchView = findViewById(R.id.search_marketplace);
        mMarketplacePresenter = new MarketplacePresenter(this, new Marketplace());

        setupRecyclerView();
        setupSearchView();

        doInitialSearch();
    }

    private void doInitialSearch() {
        String query = "";
        Intent intent = getIntent();
        String action = intent.getAction();
        if (!action.isEmpty() && action.equals(Intent.ACTION_VIEW)) {
            Uri data = intent.getData();
            query = data.getLastPathSegment();
        } else {
            query = MeliPreferences.getInstance(this).
                    getString(MeliPreferences.Key.LAST_QUERY, "Celulares");
        }
        mSearchView.setQuery(query, false);
        loadItems(query);
    }

    private void setupRecyclerView() {
        RecyclerView itemsRecyclerView = findViewById(R.id.rv_marketplace);

        StaggeredGridLayoutManager gridLayoutManager =
                new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        itemsRecyclerView.setLayoutManager(gridLayoutManager);

        itemsRecyclerView.setItemAnimator(new SlideInUpAnimator());

        itemsRecyclerView.setHasFixedSize(true);
    }

    private void setupSearchView() {
        mSearchView.setOnClickListener(v -> mSearchView.setIconified(false));

        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextChange(String newText) {
                return true;
            }

            @Override
            public boolean onQueryTextSubmit(String query) {
                saveQueryToPreferences();
                loadItems(query);
                return true;
            }
        });
    }

    private void loadItems(String searchQuery) {
        showProgressBar();
        mMarketplacePresenter.loadMarketplace(searchQuery);
    }

    private void saveQueryToPreferences() {
        String queryValue = mSearchView.getQuery().toString();
        MeliPreferences.getInstance(this).put(MeliPreferences.Key.LAST_QUERY, queryValue);
    }

    @Override
    public void onItemClicked(String itemId) {
        Intent openItemDetailsActivityIntent = new Intent(this, ItemDetailsActivity.class);
        openItemDetailsActivityIntent.putExtra(ItemDetailsActivity.EXTRA_ITEM_ID, itemId);
        startActivity(openItemDetailsActivityIntent);
    }

    private void showProgressBar() {
        mProgressBar.setVisibility(View.VISIBLE);
        mSearchView.setVisibility(View.GONE);
        mRecyclerView.setVisibility(View.GONE);
    }

    private void hideProgressBar() {
        mProgressBar.setVisibility(View.GONE);
        mSearchView.setVisibility(View.VISIBLE);
        mRecyclerView.setVisibility(View.VISIBLE);
    }

    @Override
    public void onLoadItemsSuccess() {
        RecyclerView itemsRecyclerView = findViewById(R.id.rv_marketplace);
        MarketplaceItemsAdapter adapter = new MarketplaceItemsAdapter(mMarketplacePresenter.getItems(), mMarketplacePresenter);
        itemsRecyclerView.swapAdapter(adapter, false);
        hideProgressBar();
    }

    @Override
    public void onLoadItemsError() {
        hideProgressBar();
    }
}
