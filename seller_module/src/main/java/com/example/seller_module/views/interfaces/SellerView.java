package com.example.seller_module.views.interfaces;

import com.example.seller_module.model.Seller;

public interface SellerView {
    void onLoadSellerInfoSuccess(Seller sellerInfo);
    void onLoadSellerInfoError();
}
