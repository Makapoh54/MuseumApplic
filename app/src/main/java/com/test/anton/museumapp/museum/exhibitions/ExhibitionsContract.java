package com.test.anton.museumapp.museum.exhibitions;

import com.test.anton.museumapp.model.Exhibition;

import java.util.List;

/**
 * The contract between the ExhibitionList view and ExhibitionList presenter
 */
public interface ExhibitionsContract {

    interface View {
        void showRepositories(List<Exhibition> exhibitions);

        void showErrorMessage();

        void refresh();

        void onItemsLoadComplete();
    }
}
