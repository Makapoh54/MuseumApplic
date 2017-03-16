package com.test.anton.githubtrends;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.test.anton.githubtrends.model.Repository;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class MainActivity extends AppCompatActivity {

    public static View.OnClickListener onCarerClickListener;
    private GithubListAdapter mGithubListAdapter;
    private ArrayList<Repository> mGithubData = new ArrayList<Repository>();
    private Unbinder mUnbinder;

    @BindView(R.id.github_list)
    RecyclerView mRecyclerView;
    @Nullable
    @BindView(R.id.progress_bar)
    View mProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mUnbinder = ButterKnife.bind(this);
        mGithubData.add(new Repository("ABC", "ABC", 123, "ABC", "https://s-media-cache-ak0.pinimg.com/736x/23/18/5f/23185f48e7371c3f3332c08175f2f0ac.jpg"));
        mGithubData.add(new Repository("ABC", "ABC", 123, "ABC", "https://s-media-cache-ak0.pinimg.com/736x/23/18/5f/23185f48e7371c3f3332c08175f2f0ac.jpg"));
        mGithubData.add(new Repository("ABC", "ABC", 123, "ABC", "https://s-media-cache-ak0.pinimg.com/736x/23/18/5f/23185f48e7371c3f3332c08175f2f0ac.jpg"));
        mGithubData.add(new Repository("ABC", "ABC", 123, "ABC", "https://s-media-cache-ak0.pinimg.com/736x/23/18/5f/23185f48e7371c3f3332c08175f2f0ac.jpg"));
        mGithubListAdapter = new GithubListAdapter(this, mGithubData);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mGithubListAdapter);
        //getGithubList();
    }

    private void getGithubList() {
        showProgress();
        mGithubListAdapter = new GithubListAdapter(this, mGithubData);
        mRecyclerView.setAdapter(mGithubListAdapter);
        hideProgress();
    }

    public void showProgress() {
        mProgressBar.setVisibility(View.VISIBLE);
    }

    public void hideProgress() {
        mProgressBar.setVisibility(View.GONE);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mUnbinder.unbind();
    }
}
