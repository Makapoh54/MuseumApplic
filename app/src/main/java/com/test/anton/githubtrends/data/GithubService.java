package com.test.anton.githubtrends.data;

import com.test.anton.githubtrends.model.Contributor;
import com.test.anton.githubtrends.model.Items;
import com.test.anton.githubtrends.model.User;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

public interface GithubService {

    @GET("search/repositories")
    Call<Items> search(@QueryMap Map<String, String> options);

    @GET
    Call<List<Contributor>> getContributors(@Url String url);

    @GET
    Call<User> getUser(@Url String url);
}
