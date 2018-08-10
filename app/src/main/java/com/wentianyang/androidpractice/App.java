package com.wentianyang.androidpractice;

import android.app.Application;
import com.wentianyang.androidpractice.action.colorbar.ColorAction;
import com.wentianyang.androidpractice.action.colorbar.ColorActionName;
import com.wentianyang.androidpractice.action.imagebar.ImageBarAction;
import com.wentianyang.androidpractice.action.imagebar.ImageBarActionName;
import com.wentianyang.androidpractice.action.toolbar.ToolBarAction;
import com.wentianyang.androidpractice.action.toolbar.ToolBarActionName;
import com.wentianyang.base.BaseApp;
import com.wentianyang.base.config.ConfigManager;
import com.wentianyang.routerlib.Router;

/**
 * @Date 创建时间:  2018/8/6
 * @Author: YTW
 * @Description:
 **/

public class App extends BaseApp {

    public static final String HOST = "";

    @Override
    public void onCreate() {
        super.onCreate();

        ConfigManager.init(this)
            .withApiHost(HOST);

        initModulesApp(this);
        initModulesData(this);
    }

    private void registerAction() {
        Router.getInstance().registerActions(ToolBarActionName.NAME, new ToolBarAction());
        Router.getInstance().registerActions(ImageBarActionName.NAME, new ImageBarAction());
        Router.getInstance().registerActions(ColorActionName.NAME, new ColorAction());
//        Router.getInstance().registerActions(MainActionName.NAME, new MainAction());
    }

    @Override
    protected void initModulesApp(Application application) {

    }

    @Override
    protected void initModulesData(Application application) {

    }
}
