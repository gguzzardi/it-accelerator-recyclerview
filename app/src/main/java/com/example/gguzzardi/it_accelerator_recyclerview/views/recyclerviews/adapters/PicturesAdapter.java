package com.example.gguzzardi.it_accelerator_recyclerview.views.recyclerviews.adapters;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.gguzzardi.it_accelerator_recyclerview.R;
import com.example.gguzzardi.it_accelerator_recyclerview.model.PictureData;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

public class PicturesAdapter extends RecyclerView.Adapter<PicturesAdapter.ViewHolder> {
    public class ViewHolder extends RecyclerView.ViewHolder {
        public SimpleDraweeView image;

        public ViewHolder(View itemView) {
            super(itemView);

            image = itemView.findViewById(R.id.rv_item_images);
        }
    }

    private List<PictureData> mPicturesData;

    public PicturesAdapter(List<PictureData> picturesData) {
        mPicturesData = picturesData;
    }

    @Override
    public PicturesAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View contactView = inflater.inflate(R.layout.rv_item_image, parent, false);

        PicturesAdapter.ViewHolder viewHolder = new ViewHolder(contactView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(PicturesAdapter.ViewHolder viewHolder, int position) {
        PictureData pictureData = mPicturesData.get(position);

        Uri uri = Uri.parse(pictureData.getUrl());
        viewHolder.image.setImageURI(uri);
    }

    @Override
    public int getItemCount() {
        return mPicturesData.size();
    }
}
