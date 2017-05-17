package com.test.anton.museumapp.museum.itemdetails;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.github.chrisbanes.photoview.PhotoView;
import com.squareup.picasso.Picasso;
import com.test.anton.museumapp.R;
import com.test.anton.museumapp.model.Item;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import timber.log.Timber;


public class ItemDetailsActivity extends AppCompatActivity implements ItemDetailsContract.View {

    public static final String ITEM_IMAGE = "ItemImage";
    public static final String ITEM_ID = "ItemId";

    @BindView(R.id.progress_bar)
    View mProgressBar;
    @BindView(R.id.iv_photo)
    PhotoView mPhotoView;
    @BindView(R.id.bottom_sheet)
    View mBottomSheet;
    @BindView(R.id.item_details_title)
    TextView mItemTitle;
    @BindView(R.id.item_details_artist)
    TextView mItemArtist;
    @BindView(R.id.item_details_medium)
    TextView mItemMedium;
    @BindView(R.id.item_details_content)
    TextView mItemDescription;
    @BindView(R.id.item_details_dateCreated)
    TextView mItemDateCreated;

    private String mItemImage;
    private String mItemId;
    private Unbinder mUnbinder;

    private BottomSheetBehavior mBottomSheetBehavior;
    private ItemDetailsPresenter mItemDetailsPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_details);
        Timber.i("ItemDetailsActivity created");
        mUnbinder = ButterKnife.bind(this);

        Intent intent = this.getIntent();
        Bundle bundle = intent.getExtras();

        mItemImage = bundle.getString(ITEM_IMAGE);
        mItemId = bundle.getString(ITEM_ID);

        mItemDetailsPresenter = new ItemDetailsPresenter(this);
        mItemDetailsPresenter.retrieveItem(mItemId);
        configureLayout();
    }

    private void configureLayout() {
        mBottomSheetBehavior = BottomSheetBehavior.from(mBottomSheet);

        Picasso.with(this)
                .load(mItemImage)
                .into(mPhotoView);

        mPhotoView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hideBottomBar();
            }
        });
    }

    void hideBottomBar() {
        mBottomSheetBehavior.setHideable(true);
        mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
        mBottomSheetBehavior.setHideable(false);
    }

    @Override
    public void onBackPressed() {
        switch (mBottomSheetBehavior.getState()) {
            case (BottomSheetBehavior.STATE_HIDDEN):
                mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                mPhotoView.setScale(mPhotoView.getMinimumScale());
                break;
            case (BottomSheetBehavior.STATE_EXPANDED):
                mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                mPhotoView.setScale(mPhotoView.getMinimumScale());
                break;
            case (BottomSheetBehavior.STATE_COLLAPSED):
                if (mPhotoView.getScale() != mPhotoView.getMinimumScale()) {
                    mPhotoView.setScale(mPhotoView.getMinimumScale());
                } else {
                    super.onBackPressed();
                }
                break;
        }
    }

    @Override
    public void showDetails(Item item) {
        if (item != null) {
            mItemTitle.setText(item.getTitle());
            if (!TextUtils.isEmpty(item.getArtist())) {
                mItemArtist.setText(item.getArtist());
            } else {
                mItemMedium.setPadding(0, 0, 0, 0);
            }
            mItemDescription.setText(item.getDescription());
            mItemMedium.setText(item.getMedium());
            mItemDateCreated.setText(item.getDateCreated());
        }
    }
}
