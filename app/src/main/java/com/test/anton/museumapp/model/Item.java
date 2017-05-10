package com.test.anton.museumapp.model;

import com.google.firebase.database.IgnoreExtraProperties;
import com.google.firebase.database.PropertyName;

@IgnoreExtraProperties
public class Item {

    public static final String ITEM = "Item";

    @PropertyName("title")
    public String mTitle;
    @PropertyName("description")
    public String mDescription;
    @PropertyName("artist")
    public String mArtist;
    @PropertyName("dateCreated")
    public String mDateCreated;
    @PropertyName("medium")
    public String mMedium;
    @PropertyName("image")
    public String mImage;

    public Item() {
    }

    public Item(String title, String description, String artist, String dateCreated, String medium, String image) {
        mTitle = title;
        mDescription = description;
        mArtist = artist;
        mDateCreated = dateCreated;
        mMedium = medium;
        mImage = image;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        mDescription = description;
    }

    public String getArtist() {
        return mArtist;
    }

    public void setArtist(String artist) {
        mArtist = artist;
    }

    public String getDateCreated() {
        return mDateCreated;
    }

    public void setDateCreated(String dateCreated) {
        mDateCreated = dateCreated;
    }

    public String getMedium() {
        return mMedium;
    }

    public void setMedium(String medium) {
        mMedium = medium;
    }

    public String getImage() {
        return mImage;
    }

    public void setImage(String image) {
        mImage = image;
    }
}
