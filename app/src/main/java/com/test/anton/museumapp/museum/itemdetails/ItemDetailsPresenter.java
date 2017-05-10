package com.test.anton.museumapp.museum.itemdetails;


import com.google.firebase.database.DataSnapshot;
import com.test.anton.museumapp.database.FirebaseDb;
import com.test.anton.museumapp.model.Item;

import static com.test.anton.museumapp.database.Snapshot.toExhibitions;

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
