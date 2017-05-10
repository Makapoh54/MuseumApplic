package com.test.anton.museumapp.model;

import com.google.firebase.database.IgnoreExtraProperties;
import com.google.firebase.database.PropertyName;

@IgnoreExtraProperties
public class ItemImage {

    private String mItemId;
    @PropertyName("image")
    public String mItemImageUrl;

    public ItemImage(String itemId, String itemImageUrl) {
        mItemId = itemId;
        mItemImageUrl = itemImageUrl;
    }

    public ItemImage(String itemImageUrl) {
        mItemImageUrl = itemImageUrl;
    }

    public String getItemId() {
        return mItemId;
    }

    public void setItemId(String itemId) {
        mItemId = itemId;
    }

    public String getItemImageUrl() {
        return mItemImageUrl;
    }

    public void setItemImageUrl(String itemImageUrl) {
        mItemImageUrl = itemImageUrl;
    }
}
