package com.wentianyang.base.image;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Looper;
import android.os.RecoverySystem.ProgressListener;
import android.support.annotation.Nullable;
import android.widget.ImageView;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.load.resource.gif.GifDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.wentianyang.base.GlideApp;

/**
 * @Date 创建时间:  2018/8/29
 * @Author: YTW
 * @Description:
 **/

public class GlideStrategy implements ILoaderStrategy {

    @Override
    public void loadImage(String url, ImageView imageView) {
        loadNormal(imageView.getContext(), url, -1, imageView);
    }

    @Override
    public void loadImage(String url, int placeholder, ImageView imageView) {
        loadNormal(imageView.getContext(), url, placeholder, imageView);
    }

    @Override
    public void loadImage(Context context, String url, int placeholder, ImageView imageView) {
        loadNormal(context, url, placeholder, imageView);
    }

    @Override
    public void loadCircleImage(String url, int placeholder, ImageView imageView) {
        RequestOptions options = new RequestOptions()
            .centerCrop()
            .placeholder(placeholder)
            .diskCacheStrategy(DiskCacheStrategy.ALL);
        loadImageWithOption(url, options, imageView);
    }

    @SuppressLint("CheckResult")
    private void loadImageWithOption(String url, RequestOptions options, ImageView imageView) {
        GlideApp.with(imageView.getContext()).load(url).apply(options);
    }

    @Override
    public void loadGifImage(String url, int placeholder, ImageView imageView) {
        loadGif(imageView.getContext(), url, placeholder, imageView);
    }

    @Override
    public void loadImageWithProgress(String url, ImageView imageView, ProgressListener listener) {
    }

    @Override
    public void loadGifWithProgress(String url, ImageView imageView,
        ProgressLoadListener listener) {

    }

    @Override
    public void clearImageDiskCache(final Context context) {
        try {
            if (Looper.myLooper() == Looper.getMainLooper()) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        GlideApp.get(context.getApplicationContext()).clearDiskCache();
                    }
                }).start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void clearImageMemoryCache(Context context) {
        try {
            if (Looper.myLooper() == Looper.getMainLooper()) {
                GlideApp.get(context.getApplicationContext()).clearMemory();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void trimMemory(Context context, int level) {
        GlideApp.get(context).trimMemory(level);
    }

    @Override
    public String getCacheSize(Context context) {
        return null;
    }

    @SuppressLint("CheckResult")
    private void loadNormal(final Context context, final String url, int placeholder,
        ImageView imageView) {
        GlideApp.with(context)
            .load(url)
            .placeholder(placeholder)
            .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
            .listener(new RequestListener<Drawable>() {
                @Override
                public boolean onLoadFailed(@Nullable GlideException e, Object model,
                    Target<Drawable> target, boolean isFirstResource) {
                    return false;
                }

                @Override
                public boolean onResourceReady(Drawable resource, Object model,
                    Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                    return false;
                }
            }).into(imageView);
    }

    private void loadGif(final Context context, String url, int placeholder, ImageView imageView) {
        GlideApp.with(context)
            .asGif()
            .load(url)
            .dontAnimate()
            .placeholder(placeholder)
            .skipMemoryCache(true)
            .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
            .listener(new RequestListener<GifDrawable>() {
                @Override
                public boolean onLoadFailed(@Nullable GlideException e, Object model,
                    Target<GifDrawable> target, boolean isFirstResource) {
                    return false;
                }

                @Override
                public boolean onResourceReady(GifDrawable resource, Object model,
                    Target<GifDrawable> target,
                    DataSource dataSource, boolean isFirstResource) {
                    return false;
                }
            }).into(imageView);
    }
}
