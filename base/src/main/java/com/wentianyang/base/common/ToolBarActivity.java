package com.wentianyang.base.common;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import com.wentianyang.base.R;

/**
 * @Date 创建时间:  2018/9/4
 * @Author: YTW
 * @Description:
 **/

public abstract class ToolBarActivity extends BaseActivity {

    protected Toolbar mToolbar;

    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mToolbar = findViewById(R.id.tool_bar);
        if (mToolbar != null) {
            setSupportActionBar(mToolbar);
            getSupportActionBar().setTitle("");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
            initToolbar();
        }
    }

    protected abstract void initToolbar();
}
