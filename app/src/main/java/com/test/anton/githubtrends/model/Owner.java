package com.test.anton.githubtrends.model;


import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Owner implements Serializable {

    @SerializedName("avatar_url")
    private String mAvatarUrl;
    @SerializedName("login")
    private String mLogin;
    @SerializedName("type")
    private String mType;

    public Owner(String avatarUrl, String login, String type) {
        mAvatarUrl = avatarUrl;
        mLogin = login;
        mType = type;
    }

    public String getAvatarUrl() {
        return mAvatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        mAvatarUrl = avatarUrl;
    }

    public String getLogin() {
        return mLogin;
    }

    public void setLogin(String login) {
        mLogin = login;
    }

    public String getType() {
        return mType;
    }

    public void setType(String type) {
        mType = type;
    }
}
