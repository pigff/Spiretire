package com.fjrcloud.sciencepro.utils;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.fjrcloud.sciencepro.R;

/**
 * Created by lin on 2017/2/22.
 */
public class ImgLoadUtils {

    public static void loadUrl(Context context, String url, int errorImg, ImageView view) {
        Glide.with(context)
                .load(url)
                .error(errorImg)
                .fitCenter()
                .into(view);
    }

    public static void loadUrl(Context context, String url, ImageView view) {
        Glide.with(context)
                .load(url)
                .error(R.drawable.img_error)
                .into(view);
    }

    public static void loadUrl(Context context, String url, int errorImg, ImageView view, int wide, int height) {
        Glide.with(context)
                .load(url)
                .error(errorImg)
                .override(wide, height)
                .crossFade()
                .fitCenter()
                .into(view);
    }

    public static void loadCircleUrl(Context context, String url, int errorImg, ImageView view) {
        Glide.with(context)
                .load(url)
                .error(errorImg)
                .crossFade()
                .bitmapTransform(new GlideCircleTransform(context))
                .into(view);
    }

    public static void loadCircleUrl(Context context, String url, int errorImg, ImageView view, int width, int height) {
        Glide.with(context)
                .load(url)
                .error(errorImg)
                .crossFade()
                .override(width, height)
                .bitmapTransform(new GlideCircleTransform(context))
                .into(view);
    }

    public static void loadCircleUrl(Context context, String url, ImageView view) {
        Glide.with(context)
                .load(url)
                .error(R.drawable.img_error)
                .crossFade()
                .bitmapTransform(new GlideCircleTransform(context))
                .into(view);
    }
}
