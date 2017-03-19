package com.test.anton.githubtrends.model;


import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Repository implements Serializable {

    @SerializedName("name")
    private String mName;
    @SerializedName("language")
    private String mLanguage;
    @SerializedName("contributorsCount")
    private int mContributorsCount;
    @SerializedName("repoLogoUrl")
    private String mRepoLogoUrl;
    @SerializedName("contributors_url")
    private String mContributorsUrl;
    @SerializedName("organization")
    private String mOrganization;
    @SerializedName("owner")
    private Owner mOwner;
    @SerializedName("topContributorUrl")
    private String mTopContributorUrl;

    public Repository(String name, String language, int contributorsCount, String repoLogoUrl, String contributorsUrl, String organization, Owner owner, String topContributorUrl) {
        mName = name;
        mLanguage = language;
        mContributorsCount = contributorsCount;
        mRepoLogoUrl = repoLogoUrl;
        mContributorsUrl = contributorsUrl;
        mOrganization = organization;
        mOwner = owner;
        mTopContributorUrl = topContributorUrl;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        this.mName = name;
    }

    public String getLanguage() {
        return mLanguage;
    }

    public void setLanguage(String language) {
        this.mLanguage = language;
    }

    public int getContributorsCount() {
        return mContributorsCount;
    }

    public void setContributorsCount(int contributorsCount) {
        this.mContributorsCount = contributorsCount;
    }

    public String getRepoLogoUrl() {
        return mRepoLogoUrl;
    }

    public void setRepoLogoUrl(String avatar_url) {
        this.mRepoLogoUrl = avatar_url;
    }

    public String getContributorsUrl() {
        return mContributorsUrl;
    }

    public void setContributorsUrl(String contributorsUrl) {
        this.mContributorsUrl = contributorsUrl;
    }

    public Owner getOwner() {
        return mOwner;
    }

    public void setOwner(Owner owner) {
        this.mOwner = owner;
    }

    public String getOrganization() {
        return mOrganization;
    }

    public void setOrganization(String organization) {
        this.mOrganization = organization;
    }

    public String getTopContributorUrl() {
        return mTopContributorUrl;
    }

    public void setTopContributorUrl(String topContributorUrl) {
        this.mTopContributorUrl = topContributorUrl;
    }
}

