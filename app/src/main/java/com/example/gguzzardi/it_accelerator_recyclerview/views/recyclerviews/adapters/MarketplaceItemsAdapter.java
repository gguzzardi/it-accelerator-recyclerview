package com.example.gguzzardi.it_accelerator_recyclerview.views.recyclerviews.adapters;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.gguzzardi.it_accelerator_recyclerview.R;
import com.example.gguzzardi.it_accelerator_recyclerview.model.Item;

import java.util.List;

public class MarketplaceItemsAdapter extends RecyclerView.Adapter<MarketplaceItemsAdapter.ViewHolder> {

    public interface MarketplaceItemsListener {
        void onItemClicked();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public CardView itemCardview;
        public ImageView itemImage;
        public TextView itemPrice;
        public TextView itemDiscount;
        public TextView itemDescription;

        public ViewHolder(View itemView) {
            super(itemView);

            itemCardview = itemView.findViewById(R.id.cardview_item);
            itemImage = itemView.findViewById(R.id.image_item);
            itemPrice = itemView.findViewById(R.id.tv_item_price);
            itemDiscount = itemView.findViewById(R.id.tv_item_discount);
            itemDescription = itemView.findViewById(R.id.tv_description);
        }
    }

    private List<Item> mItems;
    private MarketplaceItemsListener mListener;

    public MarketplaceItemsAdapter(List<Item> items, MarketplaceItemsListener listener) {
        mItems = items;
        mListener = listener;
    }

    @Override
    public MarketplaceItemsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View contactView = inflater.inflate(R.layout.item_layout, parent, false);

        ViewHolder viewHolder = new ViewHolder(contactView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MarketplaceItemsAdapter.ViewHolder viewHolder, int position) {
        Item item = mItems.get(position);

        viewHolder.itemCardview.setOnClickListener( v -> mListener.onItemClicked());

        // viewHolder.itemImage.setImageDrawable();

        String priceString = String.format("$%.2f", item.getPrice());
        viewHolder.itemPrice.setText(priceString);

        if (item.getDiscount() <= 0) {
            viewHolder.itemDiscount.setVisibility(View.GONE);
        } else {
            String discount = String.format("%d%% OFF", item.getDiscount());
            viewHolder.itemDiscount.setText(discount);
        }
        viewHolder.itemDescription.setText(item.getDescription());
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }
}
