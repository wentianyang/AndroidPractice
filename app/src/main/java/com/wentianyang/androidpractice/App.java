package com.wentianyang.androidpractice;

import android.app.Application;
import android.support.annotation.Nullable;
import com.kingja.loadsir.core.LoadSir;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;
import com.orhanobut.logger.PrettyFormatStrategy;
import com.wentianyang.androidpractice.action.colorbar.ColorAction;
import com.wentianyang.androidpractice.action.colorbar.ColorActionName;
import com.wentianyang.androidpractice.action.imagebar.ImageBarAction;
import com.wentianyang.androidpractice.action.imagebar.ImageBarActionName;
import com.wentianyang.androidpractice.action.toolbar.ToolBarAction;
import com.wentianyang.androidpractice.action.toolbar.ToolBarActionName;
import com.wentianyang.base.BaseApp;
import com.wentianyang.base.callback.ConnectCallback;
import com.wentianyang.base.callback.DefaultCallback;
import com.wentianyang.base.callback.EmptyCallback;
import com.wentianyang.base.callback.NoNetworkCallback;
import com.wentianyang.base.callback.ParseCallback;
import com.wentianyang.base.callback.TimeOutCallback;
import com.wentianyang.base.callback.UnKnowCallback;
import com.wentianyang.base.callback.UnKnowHostCallback;
import com.wentianyang.base.config.ConfigManager;
import com.wentianyang.base.log.LogCatStrategy;
import com.wentianyang.routerlib.Router;

/**
 * @Date 创建时间:  2018/8/6
 * @Author: YTW
 * @Description:
 **/

public class App extends BaseApp {

    public static final String HOST = "http://gank.io/api/";
//    public static final String HOST = "https://music.163.com/";

    @Override
    public void onCreate() {
        super.onCreate();

        ConfigManager.init(this)
            .withApiHost(HOST)
            .withLog(BuildConfig.DEBUG);

        configPageState();
        configLogger();

        initModulesApp(this);
        initModulesData(this);
    }

    private void configLogger() {
        PrettyFormatStrategy strategy = PrettyFormatStrategy.newBuilder()
            .logStrategy(new LogCatStrategy())
            .methodCount(0)
            .methodOffset(7)
            .showThreadInfo(false)
            .tag("")
            .build();
        Logger.addLogAdapter(new AndroidLogAdapter(strategy) {
            @Override
            public boolean isLoggable(int priority, @Nullable String tag) {
                return BuildConfig.DEBUG;
            }
        });
    }

    private void configPageState() {
        LoadSir.beginBuilder()
            .addCallback(new ConnectCallback())
            .addCallback(new EmptyCallback())
            .addCallback(new NoNetworkCallback())
            .addCallback(new TimeOutCallback())
            .addCallback(new UnKnowCallback())
            .addCallback(new ParseCallback())
            .addCallback(new UnKnowHostCallback())
            .addCallback(new DefaultCallback())
            .setDefaultCallback(DefaultCallback.class)
            .commit();
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
