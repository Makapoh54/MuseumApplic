package com.test.anton.githubtrends.github.users;

import com.test.anton.githubtrends.model.User;

/**
 * The contract between the UserDetails view and UserDetails presenter
 */
public interface UserDetailsContract {

    interface View {
        void showDetails(User repositories);

        void showErrorMessage();
    }
}
