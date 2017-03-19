package com.test.anton.githubtrends.utils;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class PicassoUtils {

    public static void loadCirclePhoto(Context context, String path, int size, int placeHolder, final ImageView view) {
        if (!TextUtils.isEmpty(path)) {
            Picasso.with(context)
                    .load(path)
                    .centerCrop()
                    .resize(size, size)
                    .placeholder(placeHolder)
                    .into(view, new com.squareup.picasso.Callback() {
                        @Override
                        public void onSuccess() {
                            Log.d("PICASSO", "Image Loaded");
                        }

                        @Override
                        public void onError() {
                            Log.d("PICASSO", "Something went wrong with picasso");
                        }
                    });
        }
    }

    public static void cancelRequest(ImageView imageView) {
        Picasso.with(imageView.getContext())
                .cancelRequest(imageView);
    }
}
