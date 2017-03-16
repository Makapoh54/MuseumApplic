package com.test.anton.githubtrends.utils;


import android.support.annotation.NonNull;
import android.view.View;

public class Utils {

    public static void hideView(@NonNull View view) {
        view.setVisibility(View.GONE);
    }

    public static void showView(@NonNull View view) {
        view.setVisibility(View.VISIBLE);
    }
}
