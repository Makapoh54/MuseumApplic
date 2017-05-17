package com.test.anton.museumapp.museum.exhibitions;


import com.test.anton.museumapp.database.FirebaseDb;

import static com.test.anton.museumapp.database.Snapshot.toExhibitions;

public class ExhibitionsListPresenter {
    private final ExhibitionsContract.View mRepositoryView;

    public ExhibitionsListPresenter(ExhibitionsContract.View repositoryView) {
        mRepositoryView = repositoryView;
    }

    public void retrieveExhibitions() {
        FirebaseDb.getAllExhibitions(snapshot -> {
            mRepositoryView.showRepositories(toExhibitions(snapshot));
        });
    }
}