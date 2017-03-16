package com.test.anton.githubtrends.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Repository implements Parcelable{
    public String mTitle;
    public String mLanguage;
    public int mContributorsCount;
    public String mOrganistaion;
    public String mPhotoUrl;

    public Repository(String title, String language, int contributorsCount, String organistaion, String photoUrl) {
        mTitle = title;
        mLanguage = language;
        mContributorsCount = contributorsCount;
        mOrganistaion = organistaion;
        mPhotoUrl = photoUrl;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public String getLanguage() {
        return mLanguage;
    }

    public void setLanguage(String language) {
        mLanguage = language;
    }

    public int getContributorsCount() {
        return mContributorsCount;
    }

    public void setContributorsCount(int contributorsCount) {
        mContributorsCount = contributorsCount;
    }

    public String getOrganistaion() {
        return mOrganistaion;
    }

    public void setOrganistaion(String organistaion) {
        mOrganistaion = organistaion;
    }

    public String getPhotoUrl() {
        return mPhotoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        mPhotoUrl = photoUrl;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {

    }
}

