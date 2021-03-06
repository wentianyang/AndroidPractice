package com.wentianyang.androidpractice;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.wentianyang.androidpractice.model.GankItem;
import com.wentianyang.androidpractice.mvp.presenter.GirlPresenter;
import com.wentianyang.androidpractice.mvp.view.GirlView;
import com.wentianyang.base.common.MvpActivity;
import com.wentianyang.base.image.ImageLoader;
import java.util.List;

public class DemoActivity extends MvpActivity<GirlView, GirlPresenter> implements GirlView {

    private ImageView mImageView;

    public static void startActivity(Activity activity) {
        Intent intent = new Intent(activity, DemoActivity.class);
        activity.startActivity(intent);
    }

    @Override
    public int getLayoutID() {
        return R.layout.activity_demo;
    }

    @Override
    public void initView() {
        mImageView = findViewById(R.id.img);
    }

    @Override
    public boolean isStatusBarColor() {
        return true;
    }

    @Override
    public boolean enableEventBus() {
        return true;
    }

    @NonNull
    @Override
    public GirlPresenter createPresenter() {
        return new GirlPresenter();
    }

    public void fetchData(View view) {
        getPresenter().fetchData(this);
    }

    @Override
    public void initData(Bundle savedInstanceState) {
    }

    @Override
    public void onReload(View v) {
        Toast.makeText(this, "onReload...", Toast.LENGTH_SHORT).show();
        getPresenter().fetchData(this);
    }

    @Override
    public void onSuccess(List<GankItem> s) {
        TextView tv = findViewById(R.id.data);
        StringBuffer sb = new StringBuffer();
        for (GankItem g : s) {
            sb.append(g.getUrl()).append("\n");
        }
        tv.setText(sb);
        ImageLoader.getInstance().loadCircleImage(s.get(0).getUrl(), R.drawable.img, mImageView);
    }
}
