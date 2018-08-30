package com.wentianyang.base.log;

import com.orhanobut.logger.Logger;
import com.wentianyang.base.config.AppConfig.ConfigKey;
import com.wentianyang.base.config.ConfigManager;

/**
 * @Date 创建时间:  2018/8/30
 * @Author: YTW
 * @Description:
 **/

public class LogUtils {

    private static boolean log = ConfigManager.getConfig(ConfigKey.LOG);

    public static void d(Object messsage) {
        if (log) {
            Logger.d(messsage);
        }
    }

    public static void d(String message, Object... args) {
        if (log) {
            Logger.d(message, args);
        }
    }

    public static void i(String message, Object... args) {
        if (log) {
            Logger.i(message, args);
        }
    }

    public static void e(String message, Object... args) {
        if (log) {
            Logger.e(message, args);
        }
    }

    public static void e(Throwable e, String message, Object... args) {
        if (log) {
            Logger.e(e, message, args);
        }
    }

    public static void w(String message, Object... args) {
        if (log) {
            Logger.w(message, args);
        }
    }

    public static void json(String message) {
        if (log) {
            Logger.json(message);
        }
    }

    public static void xml(String message) {
        if (log) {
            Logger.xml(message);
        }
    }
}
