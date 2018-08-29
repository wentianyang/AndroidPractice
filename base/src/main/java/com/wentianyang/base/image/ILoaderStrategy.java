package com.wentianyang.base.image;

import android.content.Context;
import android.os.RecoverySystem.ProgressListener;
import android.widget.ImageView;

/**
 * Created by YTW on 2018/8/28.
 */

public interface ILoaderStrategy {

    void loadImage(String url, ImageView imageView);

    void loadImage(String url, int placeholder, ImageView imageView);

    void loadImage(Context context, String url, int placeholder, ImageView imageView);

    void loadGifImage(String url, int placeholder, ImageView imageView);

    void loadImageWithProgress(String url, ImageView imageView, ProgressListener listener);

    void loadGifWithProgress(String url, ImageView imageView, ProgressLoadListener listener);

    // 清除磁盘缓存
    void clearImageDiskCache(final Context context);

    // 清除内存
    void clearImageMemoryCache(Context context);

    // 根据不同的内存状态，来响应不同的释放策略
    void trimMemory(Context context, int level);

    // 获取缓存大小
    String getCacheSize(Context context);
}
