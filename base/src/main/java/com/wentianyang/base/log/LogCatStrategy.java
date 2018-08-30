package com.wentianyang.base.log;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import com.orhanobut.logger.LogStrategy;

/**
 * @Date 创建时间:  2018/8/30
 * @Author: YTW
 * @Description:
 **/

public class LogCatStrategy implements LogStrategy {

    @Override
    public void log(int priority, @Nullable String tag, @NonNull String message) {
        Log.println(priority, tag, message);
    }
}
