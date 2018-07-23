package com.wentianyang.androidpractice;

import android.annotation.SuppressLint;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import butterknife.ButterKnife;

/**
 * @Date 创建时间:  2018/7/17
 * @Author: YTW
 * @Description:
 **/

public abstract class BaseActivity extends AppCompatActivity {

    @SuppressLint("ObsoleteSdkInt")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initBeforeView();
        setContentView(getLayoutResId());

        /*给根Layout设置 fitsSystemWindows*/
        ViewGroup container = findViewById(Window.ID_ANDROID_CONTENT);
        View rootLayout = container.getChildAt(0);
        if (rootLayout != null && VERSION.SDK_INT >= 14) {
            rootLayout.setFitsSystemWindows(true);
        }
        ButterKnife.bind(this);
        initView();
    }

    abstract protected int getLayoutResId();

    abstract protected void initBeforeView();

    abstract protected void initView();
}
