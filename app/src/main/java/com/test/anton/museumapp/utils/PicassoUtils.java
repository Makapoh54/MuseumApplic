package com.test.anton.museumapp.utils;

import android.content.Context;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import timber.log.Timber;

public class PicassoUtils {

    public static void loadPhoto(Context context, String path, int placeHolder, ImageView view) {
        if (!TextUtils.isEmpty(path)) {
            Picasso.with(context)
                    .load(path)
                    .placeholder(placeHolder)
                    .into(view, new com.squareup.picasso.Callback() {
                        @Override
                        public void onSuccess() {
                        }

                        @Override
                        public void onError() {
                            Timber.d("Something went wrong with picasso");
                        }
                    });
        }
    }

    public static void loadPhotoFit(Context context, String path, int placeHolder, ImageView view) {
        if (!TextUtils.isEmpty(path)) {
            Picasso.with(context)
                    .load(path)
                    .fit()
                    .centerCrop()
                    .placeholder(placeHolder)
                    .into(view, new com.squareup.picasso.Callback() {
                        @Override
                        public void onSuccess() {
                        }

                        @Override
                        public void onError() {
                            Timber.d("Something went wrong with picasso");
                        }
                    });
        }
    }

    public static void cancelRequest(ImageView imageView) {
        Picasso.with(imageView.getContext())
                .cancelRequest(imageView);
    }
}
