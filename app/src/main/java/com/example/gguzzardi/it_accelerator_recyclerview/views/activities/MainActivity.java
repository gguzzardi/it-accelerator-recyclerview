package com.example.gguzzardi.it_accelerator_recyclerview.views.activities;

import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
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

    private static final String STATE_QUERY = "search_query";
    private static final int LANDSCAPE_COLUMNS_AMOUNT = 3;
    private static final int PORTRAIT_COLUMNS_AMOUNT = 2;

    private ProgressBar mProgressBar;
    private RecyclerView mRecyclerView;
    private SearchView mSearchView;

    private MarketplacePresenter mMarketplacePresenter;

    private String mQuery;

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

        doInitialSearch(savedInstanceState);
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putString(STATE_QUERY, mQuery);
        super.onSaveInstanceState(savedInstanceState);
    }

    private void doInitialSearch(Bundle savedInstanceState) {
        Intent intent = getIntent();
        String action = intent.getAction();
        if (action != null && action.equals(Intent.ACTION_VIEW)) {
            Uri data = intent.getData();
            mQuery = data.getLastPathSegment();
        } else if (savedInstanceState != null &&
                   savedInstanceState.getString(STATE_QUERY, "").isEmpty() == false) {
            mQuery = savedInstanceState.getString(STATE_QUERY);
        } else {
            mQuery = MeliPreferences.getInstance(this).
                    getString(MeliPreferences.Key.LAST_QUERY, "Celulares");
        }
        mSearchView.setQuery(mQuery, false);
        loadItems(mQuery);
    }

    private void setupRecyclerView() {
        RecyclerView itemsRecyclerView = findViewById(R.id.rv_marketplace);

        StaggeredGridLayoutManager gridLayoutManager;
        int orientation = getResources().getConfiguration().orientation;
        if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            gridLayoutManager = new StaggeredGridLayoutManager(LANDSCAPE_COLUMNS_AMOUNT, StaggeredGridLayoutManager.VERTICAL);
        } else {
            gridLayoutManager = new StaggeredGridLayoutManager(PORTRAIT_COLUMNS_AMOUNT, StaggeredGridLayoutManager.VERTICAL);
        }

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
                mQuery = query;
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
