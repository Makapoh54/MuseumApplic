package com.test.anton.githubtrends.github.repositories.repositories;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.test.anton.githubtrends.R;
import com.test.anton.githubtrends.caching.Injector;
import com.test.anton.githubtrends.model.Repository;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import timber.log.Timber;

public class GithubListActivity extends AppCompatActivity implements GithubContract.View {

    public static View.OnClickListener onRepositoryClickListener;
    private GithubListAdapter mGithubListAdapter;
    private GithubListPresenter mGithubListPresenter;
    private ArrayList<Repository> mGithubData = new ArrayList<Repository>();
    private Unbinder mUnbinder;

    @BindView(R.id.github_list)
    RecyclerView mRecyclerView;
    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout mSwipeRefreshLayout;
    @BindView(R.id.progress_bar)
    View mProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_github_list);
        Timber.i("GithubListActivity created");
        mUnbinder = ButterKnife.bind(this);

        mGithubListAdapter = new GithubListAdapter(this, mGithubData);
        mGithubListPresenter = new GithubListPresenter(this, Injector.provideGithubService());

        configureLayout();
    }

    private void configureLayout() {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(mGithubListAdapter);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(mRecyclerView.getContext(), LinearLayout.VERTICAL));

        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refresh();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        mGithubListPresenter.retrieveRepositories();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mUnbinder.unbind();
    }

    @Override
    public void showRepositories(List<Repository> repositories) {
        mGithubListAdapter.setNewItems(repositories);
    }

    @Override
    public void showErrorMessage() {
        Toast.makeText( this, R.string.error_no_repositories, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void refresh() {
        mGithubListPresenter.retrieveRepositories();
    }

    @Override
    public void onItemsLoadComplete() {
        mSwipeRefreshLayout.setRefreshing(false);
    }

    public void showProgress() {
        mProgressBar.setVisibility(View.VISIBLE);
    }

    public void hideProgress() {
        mProgressBar.setVisibility(View.GONE);
    }
}
