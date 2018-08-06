package com.wentianyang.androidpractice.systembar;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import com.wentianyang.androidpractice.BaseActivity;
import com.wentianyang.androidpractice.R;

public class ImageBarActivity extends BaseActivity {

    private static final String TAG = "ImageBarActivity";

    public static final String EXTRA_DATA = "EXTRA_DATA";

    public static void startActivity(Activity activity) {
        Intent intent = new Intent(activity, ImageBarActivity.class);
        activity.startActivity(intent);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_image_bar;
    }

    @Override
    protected void initBeforeView() {
        String extra = getIntent().getStringExtra(EXTRA_DATA);
        Log.d(TAG, "initBeforeView: " + extra);
    }

    @Override
    protected void initView() {

    }
}
