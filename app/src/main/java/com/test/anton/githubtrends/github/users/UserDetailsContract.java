package com.test.anton.githubtrends.github.users;

import com.test.anton.githubtrends.model.User;

public interface UserDetailsContract {

    interface View {
        void showDetails(User repositories);

        void showErrorMessage();
    }
}
