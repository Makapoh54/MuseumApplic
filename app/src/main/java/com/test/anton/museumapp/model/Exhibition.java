package com.test.anton.museumapp.model;

import com.google.firebase.database.IgnoreExtraProperties;
import com.google.firebase.database.PropertyName;

import java.io.Serializable;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

@IgnoreExtraProperties
public class Exhibition implements Serializable {

    public static final String EXHIBITION = "Exhibition";

    @PropertyName("artist")
    public String mArtist;
    @PropertyName("image")
    public String mImage;
    @PropertyName("large_description")
    public String mLargeDescription;
    @PropertyName("short_description")
    public String mShortDescription;
    @PropertyName("title")
    public String mTitle;
    @PropertyName("items")
    public HashMap<String, String> mItems;

    public Exhibition() {
    }

    public Exhibition(String title, String artist, String image, String shortDescription) {
        mTitle = title;
        mArtist = artist;
        mImage = image;
        mShortDescription = shortDescription;
    }

    public Exhibition(String title, String artist, String image, String shortDescription, String largeDescription, LinkedHashMap<String, String> itemsArray) {
        mTitle = title;
        mArtist = artist;
        mImage = image;
        mShortDescription = shortDescription;
        mLargeDescription = largeDescription;
        mItems = itemsArray;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public String getArtist() {
        return mArtist;
    }

    public void setArtist(String artist) {
        mArtist = artist;
    }

    public String getImage() {
        return mImage;
    }

    public void setImage(String image) {
        mImage = image;
    }

    public String getShortDescription() {
        return mShortDescription;
    }

    public void setShortDescription(String shortDescription) {
        mShortDescription = shortDescription;
    }

    public String getLargeDescription() {
        return mLargeDescription;
    }

    public void setLargeDescription(String largeDescription) {
        mLargeDescription = largeDescription;
    }

    public HashMap<String, String> getItems() {
        return mItems;
    }

    public void setItems(HashMap<String, String> items) {
        mItems = items;
    }

}
