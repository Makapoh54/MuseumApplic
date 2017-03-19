package com.test.anton.githubtrends.utils;


import android.support.annotation.NonNull;
import android.view.View;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class Utils {

    public static void hideView(@NonNull View view) {
        view.setVisibility(View.GONE);
    }

    public static void showView(@NonNull View view) {
        view.setVisibility(View.VISIBLE);
    }

    public static String getDateMinusMonthToString(){
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MONTH, -1);
        return df.format(cal.getTime());
    }
}
