package com.wentianyang.base;


import android.app.Application;

/**
 * @Date 创建时间:  2018/8/8
 * @Author: YTW
 * @Description:
 **/

public abstract class BaseApp extends Application {

    protected abstract void initModulesApp(Application application);

    protected abstract void initModulesData(Application application);
}
