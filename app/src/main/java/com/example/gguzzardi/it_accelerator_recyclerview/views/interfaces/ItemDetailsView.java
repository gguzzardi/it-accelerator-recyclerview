package com.example.gguzzardi.it_accelerator_recyclerview.views.interfaces;

import com.example.gguzzardi.it_accelerator_recyclerview.model.ItemDetails;

public interface ItemDetailsView {
    void onLoadItemDetailsSuccess(ItemDetails itemDetails);
    void onLoadItemDetailsError();
}
