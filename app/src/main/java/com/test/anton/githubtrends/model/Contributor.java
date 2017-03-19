package com.test.anton.githubtrends.model;


import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Contributor implements Serializable {

    @SerializedName("url")
    private String mUrl;

    public String getUrl() {
        return mUrl;
    }

    public void setUrl(String url) {
        mUrl = url;
    }
}
