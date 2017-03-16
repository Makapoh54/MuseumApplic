package com.test.anton.githubtrends;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

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
        View view = mInflater.inflate(R.layout.list_iteam, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Repository repo = mRepositoriesList.get(position);
        if (!TextUtils.isEmpty(repo.getPhotoUrl())) {
            PicassoUtils.loadCirclePhoto(mContext, repo.getPhotoUrl(),
                    mContext.getResources().getDimensionPixelSize(R.dimen.size_list_icon),
                    R.drawable.place_holder,
                    holder.mIcon);
        }
        holder.mTitle.setText(repo.getTitle());
        holder.mLanguage.setText(repo.getLanguage());
        holder.mContributions.setText(String.valueOf(repo.getContributorsCount()));
        holder.mOrganisation.setText(repo.getOrganistaion());
        holder.itemView.setOnClickListener(MainActivity.onCarerClickListener);
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

    public static class ViewHolder extends RecyclerView.ViewHolder {

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

/*        private TextView mTitle;
        private TextView mLanguage;
        private TextView mContributions;
        private TextView mOrganisation;
        private ImageView mIcon;*/


        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
/*            mIcon = (ImageView) view.findViewById(R.id.iv_repo_icon);
            mTitle = (TextView) view.findViewById(R.id.tv_repo_title);
            mLanguage = (TextView) view.findViewById(R.id.tv_repo_lang);
            mContributions = (TextView) view.findViewById(R.id.tv_repo_cont);
            mOrganisation = (TextView) view.findViewById(R.id.tv_repo_org);*/

        }

        /**
         * Cancels photo loading request
         * Avoids wrong item photo displaying on scrolling
         */
        public void cancelRequest() {
            PicassoUtils.cancelRequest(mIcon);
            mIcon.setImageResource(R.drawable.place_holder);
        }
    }
}
