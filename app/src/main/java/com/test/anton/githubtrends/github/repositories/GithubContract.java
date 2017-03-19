package com.test.anton.githubtrends.github.repositories;

import com.test.anton.githubtrends.model.Repository;

import java.util.List;

/**
 * The contract between the GithubList view and GithubList presenter
 */
public interface GithubContract {

    interface View {
        void showRepositories(List<Repository> repositories);

        void showErrorMessage();

        void refresh();

        void onItemsLoadComplete();
    }
}
