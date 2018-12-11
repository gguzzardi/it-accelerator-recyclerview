package com.example.gguzzardi.it_accelerator_recyclerview.views.activities;

import android.content.Intent;
import android.graphics.Paint;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SnapHelper;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gguzzardi.it_accelerator_recyclerview.R;
import com.example.gguzzardi.it_accelerator_recyclerview.model.ItemDetails;
import com.example.gguzzardi.it_accelerator_recyclerview.model.PictureData;
import com.example.gguzzardi.it_accelerator_recyclerview.presenters.ItemDetailsPresenter;
import com.example.gguzzardi.it_accelerator_recyclerview.views.interfaces.ItemDetailsView;
import com.example.gguzzardi.it_accelerator_recyclerview.views.recyclerviews.adapters.PicturesAdapter;
import com.facebook.drawee.view.SimpleDraweeView;
import com.mercadolibre.android.ui.widgets.MeliSpinner;

import java.net.URI;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;

import jp.wasabeef.recyclerview.animators.SlideInUpAnimator;

public class ItemDetailsActivity extends AppCompatActivity implements ItemDetailsView {

    public static final String EXTRA_ITEM_ID = "extra_item_id";
    private static final String URI_OPEN_SELLER_DETAILS_FORMAT = "ml://vervendedor/%s";

    private MeliSpinner mProgressBar;
    private ViewGroup mContentLayout;
    private RecyclerView mRecyclerView;

    private ItemDetailsPresenter mItemDetailsPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_details);

        mProgressBar = findViewById(R.id.pb_item_detail);
        mContentLayout = findViewById(R.id.layout_item_detail);
        mRecyclerView = findViewById(R.id.rv_item_images);


        mItemDetailsPresenter = new ItemDetailsPresenter(this);

        setupImagesRecyclerView();

        loadItemDetails();
    }

    private void setupImagesRecyclerView() {
        mRecyclerView = findViewById(R.id.rv_item_images);
        LinearLayoutManager layoutManager =
                new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setItemAnimator(new SlideInUpAnimator());
        mRecyclerView.setHasFixedSize(true);
        SnapHelper snapHelper = new LinearSnapHelper();
        snapHelper.attachToRecyclerView(mRecyclerView);
    }

    private void loadItemDetails() {
        Bundle extras = getIntent().getExtras();
        if(extras == null || !extras.containsKey(EXTRA_ITEM_ID)) {
            String errorMessage = getResources().getString(R.string.error_loading_item);
            Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show();
            finish();
        } else {
            showProgressBar();
            String itemId = extras.getString(EXTRA_ITEM_ID);
            mItemDetailsPresenter.loadItemDetails(itemId);
        }
    }

    private void showProgressBar() {
        mProgressBar.setVisibility(View.VISIBLE);
        mContentLayout.setVisibility(View.GONE);
    }

    private void hideProgressBar() {
        mProgressBar.setVisibility(View.GONE);
        mContentLayout.setVisibility(View.VISIBLE);
    }

    @Override
    public void onLoadItemDetailsSuccess(ItemDetails itemDetails) {
        hideProgressBar();

        updateItemImages(itemDetails.getItemImages());
        updateSoldQuantity(itemDetails.getSoldQuantity());
        updateItemTitle(itemDetails.getTitle());
        updateItemBasePrice(itemDetails.getBasePrice(), itemDetails.getPrice());
        updateItemPrice(itemDetails.getPrice());
        setupBuyButton(itemDetails.getLinkToMeli());
        setupSellerDetailsButton(itemDetails.getSellerId());
    }

    @Override
    public void onLoadItemDetailsError() {
        hideProgressBar();
        Toast.makeText(this,
                getResources().getString(R.string.error_loading_item), Toast.LENGTH_SHORT).show();
    }

    private void updateItemImages(List<PictureData> imagesData) {
        if (imagesData.isEmpty()) return;

        mRecyclerView.setAdapter(new PicturesAdapter(imagesData));
    }

    private void updateSoldQuantity(Integer soldQuantity) {
        final TextView soldQuantityView = findViewById(R.id.tv_item_sold_quantity);
        if (soldQuantity == null || soldQuantity < 1) {
            soldQuantityView.setVisibility(View.GONE);
        }
        String soldQuantityString = String.format(getResources().getString(R.string.format_vendidos), soldQuantity);
        soldQuantityView.setText(soldQuantityString);
    }

    private void updateItemTitle(String title) {
        final TextView itemTitle = findViewById(R.id.tv_item_title);
        itemTitle.setText(title);
    }

    private void updateItemBasePrice(Double basePrice, Double actualPrice) {
        final TextView discountView = findViewById(R.id.tv_discount);
        final TextView itemBasePrice = findViewById(R.id.tv_base_price);
        if (basePrice == null || actualPrice == basePrice) {
            itemBasePrice.setVisibility(View.GONE);
            discountView.setVisibility(View.GONE);
            return;
        }

        discountView.setVisibility(View.VISIBLE);
        itemBasePrice.setVisibility(View.VISIBLE);
        itemBasePrice.setPaintFlags(itemBasePrice.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        NumberFormat formatter = new DecimalFormat("#,###.00");
        itemBasePrice.setText(String.format("$ %s", formatter.format(basePrice)));

        int discountAmount = (int) (100 - (actualPrice * 100 / basePrice));
        discountView.setText(String.format("%d%% OFF", discountAmount));
    }

    private void updateItemPrice(Double price) {
        NumberFormat formatter = new DecimalFormat("#,###.00");
        final TextView itemActualPrice = findViewById(R.id.tv_actual_price);
        itemActualPrice.setText(String.format("$ %s", formatter.format(price)));
    }

    private void setupBuyButton(String linkToMeli) {
        final Button button = findViewById(R.id.btn_buy_item);
        button.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(linkToMeli));
            startActivity(intent);
        });
    }

    private void setupSellerDetailsButton(String sellerId) {
        final CardView sellerCardview = findViewById(R.id.cv_seller_details);
        sellerCardview.setOnClickListener(v -> {
            String url = String.format(URI_OPEN_SELLER_DETAILS_FORMAT, sellerId);
            Intent openSellerDetailsIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            startActivity(openSellerDetailsIntent);
        });
    }
}
