package com.wentianyang.base.config;

import java.util.HashMap;

/**
 * @Date 创建时间:  2018/8/10
 * @Author: YTW
 * @Description:
 **/

public class AppConfig {

    private final HashMap<Object, Object> mConfigs = new HashMap<>();

    private AppConfig() {
        mConfigs.put(ConfigKey.CONFIG_READY, false);
    }

    public static AppConfig getInstance() {
        return Holder.INSTANCE;
    }

    /**
     * 配置host
     *
     * @param host 地址
     */
    public final AppConfig withApiHost(String host) {
        mConfigs.put(ConfigKey.BASE_URL, host);
        return this;
    }

    public HashMap<Object, Object> getConfigs() {
        return mConfigs;
    }

    public <T> T getConfig(Object key) {
        checkConfigReady();
        final Object value = mConfigs.get(key);
        if (value == null) {
            throw new NullPointerException(key.toString() + " is null");
        }
        return (T) value;
    }

    private void checkConfigReady() {
        boolean isReady = (boolean) mConfigs.get(ConfigKey.CONFIG_READY);
        if (!isReady) {
            throw new RuntimeException("config is not ready...");
        }
    }

    private static class Holder {

        static final AppConfig INSTANCE = new AppConfig();
    }

    public class ConfigKey {

        public static final String BASE_URL = "BASE_URL";
        public static final String CONFIG_READY = "CONFIG_READY";
        public static final String APP_CONTEXT = "APP_CONTEXT";
    }
}
