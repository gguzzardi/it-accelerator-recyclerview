package com.example.gguzzardi.it_accelerator_recyclerview.views.activities;

import android.content.Intent;
import android.graphics.Paint;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gguzzardi.it_accelerator_recyclerview.R;
import com.example.gguzzardi.it_accelerator_recyclerview.model.ItemDetails;
import com.example.gguzzardi.it_accelerator_recyclerview.model.PictureData;
import com.example.gguzzardi.it_accelerator_recyclerview.presenters.ItemDetailsPresenter;
import com.example.gguzzardi.it_accelerator_recyclerview.views.interfaces.ItemDetailsView;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

public class ItemDetailsActivity extends AppCompatActivity implements ItemDetailsView {

    public static final String EXTRA_ITEM_ID = "extra_item_id";

    private ProgressBar mProgressBar;
    private ViewGroup mContentLayout;

    private ItemDetailsPresenter mItemDetailsPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_details);

        mProgressBar = findViewById(R.id.pb_item_detail);
        mContentLayout = findViewById(R.id.layout_item_detail);


        mItemDetailsPresenter = new ItemDetailsPresenter(this);

        loadItemDetails();
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
        updateItemTitle(itemDetails.getTitle());
        updateItemBasePrice(itemDetails.getBasePrice(), itemDetails.getPrice());
        updateItemPrice(itemDetails.getPrice());
        setupBuyButton(itemDetails.getLinkToMeli());
    }

    @Override
    public void onLoadItemDetailsError() {
        hideProgressBar();
        Toast.makeText(this,
                getResources().getString(R.string.error_loading_item), Toast.LENGTH_SHORT).show();
    }

    private void updateItemImages(List<PictureData> imagesData) {
        if (imagesData.isEmpty()) return;

        final SimpleDraweeView image = findViewById(R.id.image_item);
        Uri uri = Uri.parse(imagesData.get(0).getUrl());
        image.setImageURI(uri);
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
        itemBasePrice.setText(String.format("%.2f", basePrice));

        int discountAmount = (int) (100 - (actualPrice * 100 / basePrice));
        discountView.setText(String.format("%d%% OFF", discountAmount));
    }

    private void updateItemPrice(Double price) {
        final TextView itemActualPrice = findViewById(R.id.tv_actual_price);
        itemActualPrice.setText(String.format("%.2f", price));
    }

    private void setupBuyButton(String linkToMeli) {
        final Button button = findViewById(R.id.btn_buy_item);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(linkToMeli));
                startActivity(intent);
            }
        });
    }
}
