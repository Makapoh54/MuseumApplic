package com.test.anton.museumapp.museum.exhibitions;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.test.anton.museumapp.R;
import com.test.anton.museumapp.model.Exhibition;
import com.test.anton.museumapp.museum.exhibdetails.ExhibitionDetailsActivity;
import com.test.anton.museumapp.utils.PicassoUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.test.anton.museumapp.model.Exhibition.EXHIBITION;

public class ExhibitionsListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int TYPE_HEADER = 0;
    private static final int TYPE_ITEM = 1;
    private Context mContext;
    private List<Exhibition> mExhibitionsList;
    private LayoutInflater mInflater;

    public ExhibitionsListAdapter(Context context, ArrayList<Exhibition> carerList) {
        mExhibitionsList = carerList;
        mInflater = LayoutInflater.from(context);
        mContext = context;
    }

    public Exhibition getItem(int position) {
        return mExhibitionsList.get(position);
    }

    public void setNewItems(List<Exhibition> newList) {
        mExhibitionsList = newList;
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.exhibiton_list_item, parent, false);

        RecyclerView.ViewHolder viewHolder = null;
        if (viewType == TYPE_HEADER) {
            View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.exhibition_list_header, parent, false);
            viewHolder = new HeaderViewHolder(layoutView);
        } else if (viewType == TYPE_ITEM) {
            mInflater.inflate(R.layout.exhibiton_list_item, parent, false);
            viewHolder = new ItemViewHolder(view, new ExhibitionsListAdapter.ItemViewHolder.OnItemClickListener() {
                @Override
                public void onItemClick(View view, int position) {
                    Intent intent = new Intent(mContext, ExhibitionDetailsActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable(EXHIBITION, mExhibitionsList.get(position));
                    intent.putExtras(bundle);
                    mContext.startActivity(intent);
                }
            });
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final Exhibition repo = mExhibitionsList.get(position);

        if (holder instanceof ItemViewHolder) {
            if (!TextUtils.isEmpty(repo.getImage())) {
                PicassoUtils.loadPhotoFit(mContext, repo.getImage(),
                        R.drawable.place_holder_grey,
                        ((ItemViewHolder) holder).mIcon);
            }
            ((ItemViewHolder) holder).mTitle.setText(repo.getTitle());
            ((ItemViewHolder) holder).mContributions.setText(String.valueOf(repo.getArtist()));
            if (!TextUtils.isEmpty(repo.getShortDescription())) {
                ((ItemViewHolder) holder).mLanguage.setText(repo.getShortDescription());
            }
        }
    }

    @Override
    public void onViewRecycled(RecyclerView.ViewHolder holder) {
        super.onViewRecycled(holder);
        if (holder instanceof ItemViewHolder) {
            ((ItemViewHolder) holder).cancelRequest();
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (isPositionHeader(position))
            return TYPE_HEADER;
        return TYPE_ITEM;
    }

    private boolean isPositionHeader(int position) {
        return position == 0;
    }

    @Override
    public int getItemCount() {
        return mExhibitionsList.size();
    }

    public static class ItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private OnItemClickListener mItemClickListener;
        @BindView(R.id.exhibition_card_view)
        CardView mItemLayout;
        @BindView(R.id.exhibition_title)
        TextView mTitle;
        @BindView(R.id.exhibition_author)
        TextView mLanguage;
        @BindView(R.id.exhibition_style)
        TextView mContributions;
        @BindView(R.id.exhibition_image)
        ImageView mIcon;

        public ItemViewHolder(View view, OnItemClickListener onItemClickListener) {
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
            PicassoUtils.cancelRequest(mIcon);
            mIcon.setImageResource(R.drawable.place_holder_grey);
        }

        @Override
        public void onClick(View v) {
            mItemClickListener.onItemClick(v, getAdapterPosition()); //OnItemClickListener mItemClickListener;
        }

        public static interface OnItemClickListener {
            public void onItemClick(View view, int position);
        }
    }

    public static class HeaderViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.exhibition_date)
        TextView mTitle;

        public HeaderViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
