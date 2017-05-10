package com.test.anton.museumapp.museum.itemdetails;


import com.test.anton.museumapp.model.Item;

public class ItemDetailsContract {

    interface View {
        void showDetails(Item item);
    }
}
