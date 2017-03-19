package com.test.anton.githubtrends.model;


public class Repository {
    private String name;
    private String language;
    private int contributorsCount;
    private String repoLogoUrl;
    private String contributors_url;
    private String organization;
    private Owner owner;
    private String topContributorUrl;

    public Repository(String name, String language, int contributorsCount, String organization, String repoLogourl) {
        this.name = name;
        this.language = language;
        this.contributorsCount = contributorsCount;
        this.organization = organization;
        this.repoLogoUrl = repoLogourl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public int getContributorsCount() {
        return contributorsCount;
    }

    public void setContributorsCount(int contributorsCount) {
        this.contributorsCount = contributorsCount;
    }

    public String getRepoLogoUrl() {
        return repoLogoUrl;
    }

    public void setRepoLogoUrl(String avatar_url) {
        this.repoLogoUrl = avatar_url;
    }

    public String getContributors_url() {
        return contributors_url;
    }

    public void setContributors_url(String contributors_url) {
        this.contributors_url = contributors_url;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public String getTopContributorUrl() {
        return topContributorUrl;
    }

    public void setTopContributorUrl(String topContributorUrl) {
        this.topContributorUrl = topContributorUrl;
    }
}

