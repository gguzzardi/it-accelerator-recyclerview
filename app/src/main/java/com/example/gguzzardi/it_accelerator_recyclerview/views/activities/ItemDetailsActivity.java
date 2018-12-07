package com.example.gguzzardi.it_accelerator_recyclerview.views.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.gguzzardi.it_accelerator_recyclerview.R;

public class ItemDetailsActivity extends AppCompatActivity {

    public static final String EXTRA_ITEM_ID = "extra_item_id";

    private String mItemId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_details);
        getItemIdExtra();
    }

    private void getItemIdExtra() {
        Bundle extras = getIntent().getExtras();
        if(extras == null) {
            String errorMessage = getResources().getString(R.string.error_loading_item);
            Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show();
            finish();
        } else {
            mItemId = extras.getString(EXTRA_ITEM_ID);
        }
    }
}
