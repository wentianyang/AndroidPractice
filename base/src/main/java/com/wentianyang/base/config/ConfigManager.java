package com.wentianyang.base.config;

import android.content.Context;
import com.wentianyang.base.config.AppConfig.ConfigKey;

/**
 * @Date 创建时间:  2018/8/10
 * @Author: YTW
 * @Description:
 **/

public class ConfigManager {

    /**
     * 初始化配置
     */
    public static AppConfig init(Context context) {
        AppConfig.getInstance().getConfigs()
            .put(AppConfig.ConfigKey.APP_CONTEXT, context.getApplicationContext());
        return AppConfig.getInstance();
    }

    /**
     * 获取AppConfig
     */
    public static AppConfig getAppConfig() {
        return AppConfig.getInstance();
    }

    /**
     * 获取ApplicationContext
     */
    public static Context getApplicationContext() {
        return getConfig(ConfigKey.APP_CONTEXT);
    }

    /**
     * 通过key值获取相应配置
     * @param key
     * @param <T>
     * @return
     */
    public static <T> T getConfig(Object key) {
        return getAppConfig().getConfig(key);
    }
}
