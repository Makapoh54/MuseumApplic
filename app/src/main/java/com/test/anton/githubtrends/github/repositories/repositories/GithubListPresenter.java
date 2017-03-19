package com.test.anton.githubtrends.github.repositories.repositories;

import com.test.anton.githubtrends.data.GithubService;
import com.test.anton.githubtrends.model.Contributor;
import com.test.anton.githubtrends.model.Items;
import com.test.anton.githubtrends.model.Repository;
import com.test.anton.githubtrends.utils.Utils;

import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import timber.log.Timber;

public class GithubListPresenter {
    private final GithubContract.View mRepositoryView;
    private final GithubService mGithubService;

    public GithubListPresenter(GithubContract.View repositoryView, GithubService githubService) {
        mRepositoryView = repositoryView;
        mGithubService = githubService;
    }

    public void retrieveRepositories() {
        Map<String, String> options = new HashMap<>();

        String currentDate = "created:>" + Utils.getDateMinusMonthToString();
        options.put("q", currentDate);
        options.put("sort", "stars");
        options.put("order", "desc");

        mGithubService.search(options).enqueue(new Callback<Items>() {
            @Override
            public void onResponse(Call<Items> call, Response<Items> response) {
                if (response.isSuccessful()) {
                    final List<Repository> result = response.body().getItems();

                    for (final ListIterator<Repository> i = result.listIterator(); i.hasNext(); ) {
                        final Repository element = i.next();

                        mGithubService.getContributors(element.getContributors_url()).enqueue(new Callback<List<Contributor>>() {
                            @Override
                            public void onResponse(Call<List<Contributor>> call, Response<List<Contributor>> response) {

                                List<Contributor> contributors = response.body();
                                element.setContributorsCount(contributors.size());
                                element.setTopContributorUrl(contributors.get(0).getUrl());
                                element.setRepoLogoUrl(element.getOwner().getAvatar_url());
                                if (element.getOwner().getType().equals("Organization")) {
                                    element.setOrganization(element.getOwner().getLogin());
                                }

                                i.set(element);

                                mRepositoryView.showRepositories(result);
                                mRepositoryView.onItemsLoadComplete();
                                Timber.i("Contributor was loaded from api");
                            }

                            @Override
                            public void onFailure(Call<List<Contributor>> call, Throwable t) {
                                mRepositoryView.showErrorMessage();
                                Timber.e(t, "Unable to load contributor data.");
                            }
                        });

                    }

                    mRepositoryView.showRepositories(result);
                    Timber.i("Repositories were loaded from api.");
                }
            }

            @Override
            public void onFailure(Call<Items> call, Throwable t) {
                mRepositoryView.showErrorMessage();
                Timber.e(t, "Unable to load repos data.");
            }
        });
    }

}