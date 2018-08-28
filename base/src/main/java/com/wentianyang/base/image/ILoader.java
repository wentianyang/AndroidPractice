package com.wentianyang.base.image;

import android.content.Context;
import android.widget.ImageView;
import java.security.cert.PKIXRevocationChecker.Option;

/**
 * Created by YTW on 2018/8/28.
 */

public interface ILoader {

    void init(Context context, int cacheSizeInM);

    void loadNet(ImageView target, String url, Option option);

    class Options {

        public static final int RES_NONE = -1;
        public int loadingResId = RES_NONE;     // 加载中的资源id
        public int loadErrorResId = RES_NONE;   // 加载失败的资源id
        public ImageView.ScaleType mScaleType = null;
    }
}
