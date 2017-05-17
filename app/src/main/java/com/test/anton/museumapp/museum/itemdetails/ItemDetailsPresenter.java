package com.test.anton.museumapp.museum.itemdetails;


import com.test.anton.museumapp.database.FirebaseDb;
import com.test.anton.museumapp.model.Item;

public class ItemDetailsPresenter {
    private final ItemDetailsContract.View mItemDetailsView;
    private Item mItem;

    public ItemDetailsPresenter(ItemDetailsContract.View itemDetails) {
        mItemDetailsView = itemDetails;
    }

    public void retrieveItem(String uid) {
        FirebaseDb.getItem(uid, snapshot -> {
            mItemDetailsView.showDetails(snapshot.getValue(Item.class));
        });
    }
}
