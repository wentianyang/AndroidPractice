package com.wentianyang.androidpractice;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import com.wentianyang.base.common.ToolBarActivity;

public class ColorBarActivity extends ToolBarActivity {

    private static final String TAG = "ColorBarActivity";

    public static void startActivity(Activity activity) {
        Intent intent = new Intent(activity, ColorBarActivity.class);
        activity.startActivity(intent);
    }

    @Override
    public int getLayoutID() {
        return R.layout.activity_color_bar;
    }

    @Override
    public void initView() {
    }

    @Override
    public boolean enableEventBus() {
        return false;
    }

    @Override
    protected void initToolbar() {
        mToolbar.setNavigationIcon(R.drawable.ic_3d_rotation_black_24dp);
    }

    @Override
    public void onReload(View v) {

    }
}
