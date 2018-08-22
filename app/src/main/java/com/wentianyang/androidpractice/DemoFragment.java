package com.wentianyang.androidpractice;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import com.wentianyang.androidpractice.model.GankItem;
import com.wentianyang.androidpractice.mvp.presenter.ToolbarPresenter;
import com.wentianyang.androidpractice.mvp.view.ToolbarView;
import com.wentianyang.base.common.MvpFragment;
import java.util.List;

public class DemoFragment extends MvpFragment<ToolbarView, ToolbarPresenter> implements ToolbarView {

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
    public ToolbarPresenter createPresenter() {
        return new ToolbarPresenter();
    }

    @Override
    public void onReload(View v) {
        getPresenter().fetchData(mContext);
    }

    @Override
    public void onSuccess(List<GankItem> s) {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_demo;
    }

    @Override
    public void bindUI() {
        loaded();
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        getPresenter().fetchData(mContext);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
    }
}
