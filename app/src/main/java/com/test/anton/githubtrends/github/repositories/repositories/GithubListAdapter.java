package com.test.anton.githubtrends.github.repositories.repositories;


import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.test.anton.githubtrends.R;
import com.test.anton.githubtrends.github.repositories.users.UserDetailsActivity;
import com.test.anton.githubtrends.model.Repository;
import com.test.anton.githubtrends.utils.PicassoUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GithubListAdapter extends RecyclerView.Adapter<GithubListAdapter.ViewHolder> {

    private Context mContext;
    private List<Repository> mRepositoriesList;
    private LayoutInflater mInflater;

    public GithubListAdapter(Context context, ArrayList<Repository> carerList) {
        mRepositoriesList = carerList;
        mInflater = LayoutInflater.from(context);
        mContext = context;
    }

    public Repository getItem(int position) {
        return mRepositoriesList.get(position);
    }

    public void setNewItems(List<Repository> newList) {
        mRepositoriesList = newList;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.github_list_iteam, parent, false);

        return new ViewHolder(view, new GithubListAdapter.ViewHolder.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                if (!TextUtils.isEmpty(mRepositoriesList.get(position).getTopContributorUrl())) {
                    Intent intent = new Intent(mContext, UserDetailsActivity.class);
                    intent.putExtra(UserDetailsActivity.USER_URL, mRepositoriesList.get(position).getTopContributorUrl());
                    intent.putExtra(UserDetailsActivity.REPOSITORY_TITLE, mRepositoriesList.get(position).getName());
                    mContext.startActivity(intent);
                }
            }
        });
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Repository repo = mRepositoriesList.get(position);
        if (!TextUtils.isEmpty(repo.getRepoLogoUrl())) {
            PicassoUtils.loadCirclePhoto(mContext, repo.getRepoLogoUrl(),
                    mContext.getResources().getDimensionPixelSize(R.dimen.size_list_icon),
                    R.drawable.place_holder,
                    holder.mIcon);
        }
        holder.mTitle.setText(repo.getName());
        holder.mContributions.setText(String.valueOf(repo.getContributorsCount()));
        if (!TextUtils.isEmpty(repo.getLanguage())) {
            holder.mLanguage.setText(repo.getLanguage());
        }
        if (!TextUtils.isEmpty(repo.getOrganization())) {
            holder.mOrganisation.setText(repo.getOrganization());
        }
    }

    @Override
    public void onViewRecycled(ViewHolder holder) {
        super.onViewRecycled(holder);
        holder.cancelRequest();
    }

    @Override
    public int getItemCount() {
        return mRepositoriesList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private OnItemClickListener mItemClickListener;
        @BindView(R.id.rl_github_list_item)
        RelativeLayout mItemLayout;
        @BindView(R.id.tv_repo_title)
        TextView mTitle;
        @BindView(R.id.tv_repo_lang)
        TextView mLanguage;
        @BindView(R.id.tv_repo_cont)
        TextView mContributions;
        @BindView(R.id.tv_repo_org)
        TextView mOrganisation;
        @BindView(R.id.iv_repo_icon)
        ImageView mIcon;

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
            PicassoUtils.cancelRequest(mIcon);
            mIcon.setImageResource(R.drawable.place_holder);
        }

        @Override
        public void onClick(View v) {
            mItemClickListener.onItemClick(v, getAdapterPosition()); //OnItemClickListener mItemClickListener;
        }

        public static interface OnItemClickListener {
            public void onItemClick(View view, int position);
        }
    }
}
