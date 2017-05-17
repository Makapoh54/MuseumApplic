package com.test.anton.museumapp.museum.exhibdetails;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.test.anton.museumapp.R;
import com.test.anton.museumapp.museum.itemdetails.ItemDetailsActivity;
import com.test.anton.museumapp.utils.PicassoUtils;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import timber.log.Timber;

import static com.test.anton.museumapp.museum.itemdetails.ItemDetailsActivity.ITEM_ID;
import static com.test.anton.museumapp.museum.itemdetails.ItemDetailsActivity.ITEM_IMAGE;

public class ItemsGalleryAdapter extends RecyclerView.Adapter<ItemsGalleryAdapter.ViewHolder> {

    private Context mContext;
    private LinkedHashMap<String, String> mItemsList;
    private LayoutInflater mInflater;

    public ItemsGalleryAdapter(Context context, LinkedHashMap<String, String> carerList) {
        mItemsList = carerList;
        mInflater = LayoutInflater.from(context);
        mContext = context;
    }

    public String getImage(int position) {
        return (new ArrayList<String>(mItemsList.values())).get(position);
    }

    public String getKey(int position) {
        return (new ArrayList<String>(mItemsList.keySet())).get(position);
    }

    public void setNewItems(LinkedHashMap<String, String> newList) {
        mItemsList = newList;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.horizontal_gallery_item, parent, false);

        return new ViewHolder(view, new ItemsGalleryAdapter.ViewHolder.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Timber.i("Item Clicked: " +  getKey(position));
                Intent intent = new Intent(mContext, ItemDetailsActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString(ITEM_IMAGE, getImage(position));
                bundle.putString(ITEM_ID, getKey(position));
                intent.putExtras(bundle);
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final String url = getImage(position);

        PicassoUtils.loadPhoto(mContext, url,
                R.color.semi_transparent_gray,
                holder.mImage);
    }

    @Override
    public void onViewRecycled(ViewHolder holder) {
        super.onViewRecycled(holder);
        holder.cancelRequest();
    }

    @Override
    public int getItemCount() {
        return mItemsList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private OnItemClickListener mItemClickListener;
        @BindView(R.id.exhibition_item)
        RelativeLayout mItemLayout;
        @BindView(R.id.item_image)
        ImageView mImage;

        public ViewHolder(View view, OnItemClickListener onItemClickListener) {
            super(view);
            ButterKnife.bind(this, view);
            mItemClickListener = onItemClickListener;
            mItemLayout.setOnClickListener(this);
        }

        /**
         * Cancels photo loading request
         * Avoids wrong item photo displaying on scrolling
         */
        public void cancelRequest() {
            PicassoUtils.cancelRequest(mImage);
            mImage.setImageResource(R.drawable.place_holder_grey);
        }

        @Override
        public void onClick(View v) {
            mItemClickListener.onItemClick(v, getAdapterPosition()); //OnItemClickListener mItemClickListener;
        }

        public interface OnItemClickListener {
            void onItemClick(View view, int position);
        }
    }
}
