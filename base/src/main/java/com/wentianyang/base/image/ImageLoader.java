package com.wentianyang.base.image;

import android.content.Context;
import android.os.RecoverySystem.ProgressListener;
import android.widget.ImageView;

/**
 * @Date 创建时间:  2018/8/28
 * @Author: YTW
 * @Description:
 **/

public class ImageLoader implements ILoaderStrategy {

    public static final int PIC_DEFAULT_TYPE = 0;

    public static final int LOAD_STRATEGY_DEFAULT = 0;

    private static ImageLoader sInstance;

    private ILoaderStrategy mStrategy;

    private ImageLoader() {
        mStrategy = new GlideStrategy();
    }

    public static ImageLoader getInstance() {
        if (sInstance == null) {
            synchronized (ImageLoader.class) {
                if (sInstance == null) {
                    sInstance = new ImageLoader();
                }
            }
        }
        return sInstance;
    }

    @Override
    public void loadImage(String url, ImageView imageView) {
        mStrategy.loadImage(url, imageView);
    }

    @Override
    public void loadImage(String url, int placeholder, ImageView imageView) {
        mStrategy.loadImage(url, placeholder, imageView);
    }

    @Override
    public void loadImage(Context context, String url, int placeholder, ImageView imageView) {
        mStrategy.loadImage(context, url, placeholder, imageView);
    }

    @Override
    public void loadCircleImage(String url, int placeholder, ImageView imageView) {
        mStrategy.loadCircleImage(url, placeholder, imageView);
    }

    @Override
    public void loadGifImage(String url, int placeholder, ImageView imageView) {
        mStrategy.loadGifImage(url, placeholder, imageView);
    }

    @Override
    public void loadImageWithProgress(String url, ImageView imageView, ProgressListener listener) {
        mStrategy.loadImageWithProgress(url, imageView, listener);
    }

    @Override
    public void loadGifWithProgress(String url, ImageView imageView,
        ProgressLoadListener listener) {
        mStrategy.loadGifWithProgress(url, imageView, listener);
    }

    @Override
    public void clearImageDiskCache(Context context) {
        mStrategy.clearImageDiskCache(context);
    }

    @Override
    public void clearImageMemoryCache(Context context) {
        mStrategy.clearImageMemoryCache(context);
    }

    @Override
    public void trimMemory(Context context, int level) {
        mStrategy.trimMemory(context, level);
    }

    @Override
    public String getCacheSize(Context context) {
        return mStrategy.getCacheSize(context);
    }
}
