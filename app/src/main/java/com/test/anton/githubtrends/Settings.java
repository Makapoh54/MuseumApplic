package com.test.anton.githubtrends;

/**
 * Various endpoints, sync options, etc.
 */
public class Settings {
    // Endpoints
    public static final String DOMAIN_NAME = "https://api.github.com/";

    // Data Synchronization
    public static final int SYNCHRONIZATION_INTERVAL_ONLINE = 2; // minutes
    public static final int SYNCHRONIZATION_INTERVAL_OFFLINE = 7; //days
    public static final long SYNCHRONIZATION_CACHE_SIZE = 10 * 1024 * 1024;// 10 mb
}