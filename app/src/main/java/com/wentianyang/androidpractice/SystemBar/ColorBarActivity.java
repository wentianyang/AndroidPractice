package com.wentianyang.androidpractice.SystemBar;

import android.app.Activity;
import android.content.Intent;
import com.wentianyang.androidpractice.BaseActivity;
import com.wentianyang.androidpractice.R;

public class ColorBarActivity extends BaseActivity {

    public static void startActivity(Activity activity) {
        Intent intent = new Intent(activity, ColorBarActivity.class);
        activity.startActivity(intent);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_color_bar;
    }

    @Override
    protected void initBeforeView() {

    }

    @Override
    protected void initView() {

    }
}
