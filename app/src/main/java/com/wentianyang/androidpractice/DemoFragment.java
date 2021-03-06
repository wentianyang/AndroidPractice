package com.wentianyang.androidpractice;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import com.wentianyang.androidpractice.model.GankItem;
import com.wentianyang.androidpractice.mvp.presenter.GirlPresenter;
import com.wentianyang.androidpractice.mvp.view.GirlView;
import com.wentianyang.base.common.MvpFragment;
import com.wentianyang.base.log.LogUtils;
import java.util.List;

public class DemoFragment extends MvpFragment<GirlView, GirlPresenter> implements
    GirlView {

    public DemoFragment() {
    }

    public static DemoFragment newInstance() {
        DemoFragment fragment = new DemoFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void onUserInvisible() {

    }

    @Override
    protected void onFirstUserInvisible() {

    }

    @Override
    protected void onUserVisible() {

    }

    @Override
    protected void onFirstUserVisible() {

    }

    @NonNull
    @Override
    public GirlPresenter createPresenter() {
        return new GirlPresenter();
    }

    @Override
    public void onReload(View v) {
        getPresenter().fetchData(mContext);
    }

    @Override
    public void onSuccess(List<GankItem> s) {
        LogUtils.d("data success " + s);
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        getPresenter().fetchData(mContext);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public int getLayoutID() {
        return R.layout.fragment_demo;
    }

    @Override
    public void initView() {
        loaded();
    }

    @Override
    public boolean enableEventBus() {
        return true;
    }
}
