package com.test.anton.githubtrends.model;


import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class User implements Serializable {

    @SerializedName("login")
    private String mLogin;
    @SerializedName("avatar_url")
    private String mAvatarUrl;
    @SerializedName("bio")
    private String mBio;
    @SerializedName("email")
    private String mEmail;

    public String getLogin() {
        return mLogin;
    }

    public void setLogin(String login) {
        mLogin = login;
    }

    public String getAvatarUrl() {
        return mAvatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        mAvatarUrl = avatarUrl;
    }

    public String getBio() {
        return mBio;
    }

    public void setBio(String bio) {
        mBio = bio;
    }

    public String getEmail() {
        return mEmail;
    }

    public void setEmail(String email) {
        mEmail = email;
    }
}
