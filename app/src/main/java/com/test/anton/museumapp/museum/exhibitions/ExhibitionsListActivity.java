package com.test.anton.museumapp.museum.exhibitions;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.test.anton.museumapp.R;
import com.test.anton.museumapp.model.Exhibition;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import timber.log.Timber;

/**
 * ExhibitionsListActivity represents View in MVP design pattern.
 */
public class ExhibitionsListActivity extends AppCompatActivity implements ExhibitionsContract.View {

    private ExhibitionsListAdapter mExhibitionsListAdapter;
    private ExhibitionsListPresenter mExhibitionsListPresenter;
    private ArrayList<Exhibition> mExhibitionsList = new ArrayList<Exhibition>();
    private Unbinder mUnbinder;

    @BindView(R.id.exhibitions_list)
    RecyclerView mRecyclerView;

    @BindView(R.id.appbar_toolbar)
    Toolbar mToolbar;

    @BindView(R.id.progress_bar)
    View mProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exhibitions_list);
        Timber.i("ExhibitionsListActivity created");
        mUnbinder = ButterKnife.bind(this);

        mExhibitionsListAdapter = new ExhibitionsListAdapter(this, mExhibitionsList);
        mExhibitionsListPresenter = new ExhibitionsListPresenter(this);

        configureLayout();
    }

    private void configureLayout() {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(mExhibitionsListAdapter);
        mRecyclerView.setHasFixedSize(true);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mExhibitionsListPresenter.retrieveExhibitions();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mExhibitionsListPresenter.cancelRetrieveRepositories();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mUnbinder.unbind();
    }

    @Override
    public void showRepositories(List<Exhibition> exhibitions) {
        exhibitions.add(0, new Exhibition());
        mExhibitionsListAdapter.setNewItems(exhibitions);
    }

    @Override
    public void showErrorMessage() {
        Toast.makeText(this, R.string.error_no_repositories, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void refresh() {
        mExhibitionsListPresenter.retrieveExhibitions();
    }

    @Override
    public void onItemsLoadComplete() {
    }

    public void showProgress() {
        mProgressBar.setVisibility(View.VISIBLE);
    }

    public void hideProgress() {
        mProgressBar.setVisibility(View.GONE);
    }
}
