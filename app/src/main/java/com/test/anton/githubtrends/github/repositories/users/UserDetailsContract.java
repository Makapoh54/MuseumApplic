package com.test.anton.githubtrends.github.repositories.users;

import com.test.anton.githubtrends.model.User;

public class UserDetailsContract {
    interface View {
        void showDetails(User repositories);

        void showErrorMessage();
    }
}
