package com.wentianyang.base.util;

import android.content.res.Resources;

/**
 * Created by YTW on 2018/8/27.
 */

public class DimenUtils {


    public static float px2dp(float px) {
        float densityDpi = Resources.getSystem().getDisplayMetrics().densityDpi;
        return px / (densityDpi / 160f);
    }

    public static int dp2px(float dp) {
        float density = Resources.getSystem().getDisplayMetrics().density;
        return Math.round(dp * density);
    }
}
