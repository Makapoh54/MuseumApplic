package com.test.anton.museumapp.museum.exhibdetails;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ms.square.android.expandabletextview.ExpandableTextView;
import com.test.anton.museumapp.R;
import com.test.anton.museumapp.model.Exhibition;
import com.test.anton.museumapp.utils.PicassoUtils;

import java.util.LinkedHashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import timber.log.Timber;

import static com.test.anton.museumapp.model.Exhibition.EXHIBITION;

/**
 * ExhibitionDetailsActivity represents View in MVP design pattern
 */
public class ExhibitionDetailsActivity extends AppCompatActivity {

    private Unbinder mUnbinder;
    private StaggeredGridLayoutManager mLayoutManager;
    private Exhibition mExhibition;

    @BindView(R.id.progress_bar)
    View mProgressBar;
    @BindView(R.id.expand_text_view)
    ExpandableTextView mExhibDescription;
    @BindView(R.id.horizontal_items_gallery)
    RecyclerView mRecyclerView;
    @BindView(R.id.exhibition_details_description)
    TextView mExhibitionDetailsTitle;
    @BindView(R.id.exhibition_details_image)
    ImageView mExhibitionImage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.exhibition_details);
        Timber.i("ExhibitionDetailsActivity created");
        mUnbinder = ButterKnife.bind(this);

        Intent intent = this.getIntent();
        Bundle bundle = intent.getExtras();

        mExhibition = (Exhibition) bundle.getSerializable(EXHIBITION);

        configureLayout();
    }

    private void configureLayout() {
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setNestedScrollingEnabled(false);

        mLayoutManager = new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.HORIZONTAL);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mExhibDescription.setText(mExhibition.getLargeDescription());
        mExhibitionDetailsTitle.setText(mExhibition.getTitle());

        PicassoUtils.loadPhoto(this, mExhibition.getImage(),
                R.drawable.place_holder_grey,
                mExhibitionImage);

        ItemsGalleryAdapter rcAdapter = new ItemsGalleryAdapter(ExhibitionDetailsActivity.this, new LinkedHashMap<>(mExhibition.getItems()));
        mRecyclerView.setAdapter(rcAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        //showProgress();
        // mExhibitionDetailsPresenter.retrieveDetails(mUserUrl);
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mUnbinder.unbind();
    }

    public void showProgress() {
        mProgressBar.setVisibility(View.VISIBLE);
    }

    public void hideProgress() {
        mProgressBar.setVisibility(View.GONE);
    }
}
