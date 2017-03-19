package com.test.anton.githubtrends;


import com.test.anton.githubtrends.data.GithubService;
import com.test.anton.githubtrends.github.users.UserDetailsContract;
import com.test.anton.githubtrends.github.users.UserDetailsPresenter;
import com.test.anton.githubtrends.model.User;

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
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;

public class UserDetailsUnitTest {

    private UserDetailsPresenter mUserDetailsPresenter;
    private User mUser;

    private String mUserUrl = "https://api.github.com/users/antonscornijs";

    @Mock
    UserDetailsContract.View mUserView;

    @Mock
    GithubService mGithubService;

    @Mock
    Call<User> mockCall;

    @Mock
    ResponseBody responseBody;

    @Captor
    ArgumentCaptor<Callback<User>> argumentCaptor;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        mUserDetailsPresenter = new UserDetailsPresenter(mUserView, mGithubService);
        mUser = new User();
    }

    @Test
    public void retrieveDetailsGoodRequest() {
        when(mGithubService.getUser(mUserUrl)).thenReturn(mockCall);
        Response<User> res = Response.success(mUser);

        mUserDetailsPresenter.retrieveDetails(mUserUrl);

        verify(mockCall).enqueue(argumentCaptor.capture());
        argumentCaptor.getValue().onResponse(null, res);

        verify(mUserView).showDetails(mUser);
    }

    @Test
    public void retrieveDetailsBadRequest() {
        when(mGithubService.getUser(mUserUrl)).thenReturn(mockCall);
        Response<User> res = Response.error(500, responseBody);

        mUserDetailsPresenter.retrieveDetails(mUserUrl);

        verify(mockCall).enqueue(argumentCaptor.capture());
        argumentCaptor.getValue().onResponse(null, res);

        verifyZeroInteractions(mUserView);
    }

    @Test
    public void retrieveDetailsErrorMessageCheck() {
        when(mGithubService.getUser(mUserUrl)).thenReturn(mockCall);
        Throwable throwable = new Throwable(new RuntimeException());

        mUserDetailsPresenter.retrieveDetails(mUserUrl);

        verify(mockCall).enqueue(argumentCaptor.capture());
        argumentCaptor.getValue().onFailure(null, throwable);

        verify(mUserView).showErrorMessage();
    }

}
