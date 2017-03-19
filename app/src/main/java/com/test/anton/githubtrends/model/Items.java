package com.test.anton.githubtrends.model;


import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class Items implements Serializable {
    @SerializedName("items")
    private List<Repository> mItems;

    public List<Repository> getItems() {
        return mItems;
    }

    public void setItems(List<Repository> items) {
        mItems = items;
    }
}
