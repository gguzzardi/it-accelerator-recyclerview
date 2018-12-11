package com.example.seller_module.views;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.seller_module.R;
import com.example.seller_module.model.Address;
import com.example.seller_module.model.RatingsData;
import com.example.seller_module.model.Seller;
import com.example.seller_module.presenters.SellerPresenter;
import com.example.seller_module.views.interfaces.SellerView;
import com.mercadolibre.android.ui.widgets.MeliSnackbar;

public class SellerActivity extends AppCompatActivity implements SellerView {

    private static final String STATE_SELLER_ID = "state_seller_id";

    private View mProgressBar;
    private View mSellerInfo;
    private View mSellerRep;
    private Button mMoreDetailsButton;

    private SellerPresenter mSellerPresenter;
    private String mSellerId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seller);

        mProgressBar = findViewById(R.id.spinner_seller);
        mSellerInfo = findViewById(R.id.cv_seller_info);
        mSellerRep = findViewById(R.id.cv_seller_rep);
        mMoreDetailsButton = findViewById(R.id.btn_open_seller);

        mSellerPresenter = new SellerPresenter(this);

        setTitle(getResources().getString(R.string.activity_seller_name));

        Intent intent = getIntent();
        String action = intent.getAction();
        if (action != null && action.equals(Intent.ACTION_VIEW)) {
            Uri data = intent.getData();
            String lastSegment = data.getLastPathSegment();
            mSellerId = lastSegment == null ? "" : lastSegment;
        } else if (savedInstanceState != null && savedInstanceState.containsKey(STATE_SELLER_ID)) {
            mSellerId = savedInstanceState.getString(STATE_SELLER_ID);
        } else {
            Toast.makeText(this, "Error loading seller info", Toast.LENGTH_SHORT).show();
            finish();
        }
        loadSellerInfo(mSellerId);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putString(STATE_SELLER_ID, mSellerId);
        super.onSaveInstanceState(outState);
    }

    private void loadSellerInfo(String sellerId) {
        showProgressBar();
        mSellerPresenter.loadSellerInfo(sellerId);
    }

    private void showProgressBar() {
        mProgressBar.setVisibility(View.VISIBLE);
        mSellerInfo.setVisibility(View.GONE);
        mSellerRep.setVisibility(View.GONE);
        mMoreDetailsButton.setVisibility(View.GONE);
    }

    private void hideProgressBar() {
        mProgressBar.setVisibility(View.GONE);
        mSellerInfo.setVisibility(View.VISIBLE);
        mSellerRep.setVisibility(View.VISIBLE);
        mMoreDetailsButton.setVisibility(View.VISIBLE);
    }

    @Override
    public void onLoadSellerInfoSuccess(Seller sellerInfo) {
        setSellerName(sellerInfo.getName());
        setSellerAddress(sellerInfo.getAddress());
        setSellerPoints(sellerInfo.getPoints());
        setItemSoldAmount(sellerInfo.getSellerReputation().getTransactions().getTotalAmount());
        setRatings(sellerInfo.getSellerReputation().getTransactions().getRatingsData());
        setUpMoreDetailsButton(sellerInfo.getPermalink());

        hideProgressBar();
    }

    private void setSellerName(String name) {
        final TextView nameTextview = findViewById(R.id.tv_seller_name);
        nameTextview.setText(name);
    }

    private void setSellerAddress(Address address) {
        final TextView addressTextview = findViewById(R.id.tv_address);
        addressTextview.setText(address.getCity());
    }

    private void setSellerPoints(Integer points) {
        final TextView pointsTextview = findViewById(R.id.tv_puntos);
        pointsTextview.setText(points != null ? points.toString() : "");
    }

    private void setItemSoldAmount(Integer soldAmount) {
        final TextView soldTextview = findViewById(R.id.tv_transactions);
        soldTextview.setText(soldAmount != null ? soldAmount.toString() : "");
    }

    private void setRatings(RatingsData ratingData) {
        final ProgressBar goodProgressbar = findViewById(R.id.pb_opinions_buena);
        final TextView goodTextview = findViewById(R.id.tv_buenas);
        int goodPercent = (int) (ratingData.getPositives() * 100);
        String goodText = String.format("Buena (%d%%)", goodPercent);
        goodProgressbar.setProgress(goodPercent);
        goodTextview.setText(goodText);

        final ProgressBar neutralProgressBar = findViewById(R.id.pb_opinions_regular);
        final TextView regularTextview = findViewById(R.id.tv_regular);
        int regularPercent = (int) (ratingData.getNeutrals() * 100);
        String regularText = String.format("Regular (%d%%)", regularPercent);
        neutralProgressBar.setProgress(regularPercent);
        regularTextview.setText(regularText);

        final ProgressBar badProgressBar = findViewById(R.id.pb_malas);
        final TextView badTextview = findViewById(R.id.tv_malas);
        int badPercent = (int) (ratingData.getNegatives() * 100);
        String badText = String.format("Mala (%d%%)", badPercent);
        badProgressBar.setProgress(badPercent);
        badTextview.setText(badText);
    }

    private void setUpMoreDetailsButton(final String permalink) {
        mMoreDetailsButton.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(permalink));
            startActivity(intent);
        });
    }

    @Override
    public void onLoadSellerInfoError() {
        hideProgressBar();
        MeliSnackbar snackBar = MeliSnackbar.make(mProgressBar, "Error de conección", 3);
        snackBar.setAction("Reintentar", v -> loadSellerInfo(mSellerId));
        snackBar.show();

    }
}
