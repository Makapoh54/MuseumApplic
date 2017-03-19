package com.test.anton.githubtrends.github.users;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.test.anton.githubtrends.R;
import com.test.anton.githubtrends.caching.Injector;
import com.test.anton.githubtrends.model.User;
import com.test.anton.githubtrends.utils.PicassoUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import timber.log.Timber;

/**
 * UserDetailsActivity represents View in MVP design patern
 */
public class UserDetailsActivity extends AppCompatActivity implements UserDetailsContract.View {
    public static final String USER_URL = "USER_URL";
    public static final String REPOSITORY_TITLE = "REPOSITORY_TITLE";

    private Unbinder mUnbinder;
    private UserDetailsPresenter mUserDetailsPresenter;
    private String mUserUrl;
    private String mRepoTitle;

    @BindView(R.id.tv_details_repo_title)
    TextView mRepositoryTitle;
    @BindView(R.id.tv_details_email)
    TextView mDetailsEmail;
    @BindView(R.id.tv_details_name)
    TextView mDetailsName;
    @BindView(R.id.tv_details_bio)
    TextView mDetailsBio;
    @BindView(R.id.iv_details_avatar)
    ImageView mDetailsAvatar;
    @BindView(R.id.progress_bar)
    View mProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_details);
        Timber.i("UserDetailsActivity created");
        mUnbinder = ButterKnife.bind(this);

        mUserDetailsPresenter = new UserDetailsPresenter(this, Injector.provideGithubService());
        mUserUrl = getIntent().getStringExtra(USER_URL);
        mRepoTitle = getIntent().getStringExtra(REPOSITORY_TITLE);
    }

    @Override
    protected void onResume() {
        super.onResume();
        showProgress();
        mUserDetailsPresenter.retrieveDetails(mUserUrl);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mUnbinder.unbind();
    }

    @Override
    public void showDetails(User user) {
        mDetailsName.setText(user.getLogin());
        if (!TextUtils.isEmpty(user.getEmail())) {
            mDetailsEmail.setText(user.getEmail());
        }
        if (!TextUtils.isEmpty(user.getBio())) {
            mDetailsBio.setText(user.getBio());
        }
        mRepositoryTitle.setText(mRepoTitle);
        PicassoUtils.loadCirclePhoto(this, user.getAvatarUrl(),
                this.getResources().getDimensionPixelSize(R.dimen.size_details_icon),
                R.drawable.place_holder,
                mDetailsAvatar);
        hideProgress();
    }

    @Override
    public void showErrorMessage() {
        Toast.makeText(this, R.string.error_no_user, Toast.LENGTH_SHORT).show();
        hideProgress();
    }

    public void showProgress() {
        mProgressBar.setVisibility(View.VISIBLE);
    }

    public void hideProgress() {
        mProgressBar.setVisibility(View.GONE);
    }
}
