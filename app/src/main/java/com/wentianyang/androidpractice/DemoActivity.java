package com.wentianyang.androidpractice;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import com.wentianyang.androidpractice.model.GankItem;
import com.wentianyang.androidpractice.mvp.presenter.ToolbarPresenter;
import com.wentianyang.androidpractice.mvp.view.ToolbarView;
import com.wentianyang.base.common.MvpActivity;
import java.util.List;

public class DemoActivity extends MvpActivity<ToolbarView, ToolbarPresenter> implements
    ToolbarView {

    public static void startActivity(Activity activity) {
        Intent intent = new Intent(activity, DemoActivity.class);
        activity.startActivity(intent);
    }

    @NonNull
    @Override
    public ToolbarPresenter createPresenter() {
        return new ToolbarPresenter();
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_demo;
    }

    @Override
    public void bindUI() {

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
    }
}
