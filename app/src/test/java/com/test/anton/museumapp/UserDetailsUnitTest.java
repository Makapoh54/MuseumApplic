package com.test.anton.museumapp;


import com.test.anton.museumapp.data.GithubService;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class UserDetailsUnitTest {

    private ExhibitionDetailsPresenter mExhibitionDetailsPresenter;
    private User mUser;

    private String mUserUrl = "https://api.github.com/users/antonscornijs";

    @Mock
    ExhibitionDetailsContract.View mUserView;

    @Mock
    GithubService mGithubService;

    @Mock
    Call<User> mMockCall;

    @Mock
    ResponseBody mResponseBody;

    @Captor
    ArgumentCaptor<Callback<User>> mArgumentCaptor;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        mExhibitionDetailsPresenter = new ExhibitionDetailsPresenter(mUserView, mGithubService);
        mUser = new User();
    }

    @Test
    public void retrieveDetailsGoodRequest() {
        when(mGithubService.getUser(mUserUrl)).thenReturn(mMockCall);
        Response<User> res = Response.success(mUser);

        mExhibitionDetailsPresenter.retrieveDetails(mUserUrl);

        verify(mMockCall).enqueue(mArgumentCaptor.capture());
        mArgumentCaptor.getValue().onResponse(null, res);

        verify(mUserView).showDetails(mUser);
    }

    @Test
    public void retrieveDetailsBadRequest() {
        when(mGithubService.getUser(mUserUrl)).thenReturn(mMockCall);
        Response<User> res = Response.error(500, mResponseBody);

        mExhibitionDetailsPresenter.retrieveDetails(mUserUrl);

        verify(mMockCall).enqueue(mArgumentCaptor.capture());
        mArgumentCaptor.getValue().onResponse(null, res);

        verifyZeroInteractions(mUserView);
    }

    @Test
    public void retrieveDetailsErrorMessageCheck() {
        when(mGithubService.getUser(mUserUrl)).thenReturn(mMockCall);
        Throwable throwable = new Throwable(new RuntimeException());

        mExhibitionDetailsPresenter.retrieveDetails(mUserUrl);

        verify(mMockCall).enqueue(mArgumentCaptor.capture());
        mArgumentCaptor.getValue().onFailure(null, throwable);

        verify(mUserView).showErrorMessage();
    }

}
