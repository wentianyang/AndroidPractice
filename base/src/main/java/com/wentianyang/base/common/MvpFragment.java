package com.wentianyang.base.common;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.hannesdorfmann.mosby3.mvp.MvpPresenter;
import com.hannesdorfmann.mosby3.mvp.delegate.FragmentMvpDelegate;
import com.hannesdorfmann.mosby3.mvp.delegate.FragmentMvpDelegateImpl;
import com.hannesdorfmann.mosby3.mvp.delegate.MvpDelegateCallback;
import com.trello.rxlifecycle2.components.support.RxFragment;
import com.wentianyang.base.mvp.BaseView;

/**
 * @Date 创建时间:  2018/8/17
 * @Author: YTW
 * @Description:
 **/
public abstract class MvpFragment<V extends BaseView, P extends MvpPresenter<V>> extends RxFragment
    implements FragmentMvpDelegate<V, P>, BaseView, MvpDelegateCallback<V, P> {

    protected FragmentMvpDelegate<V, P> mMvpDelegate;

    protected P mPresenter;

    // 第一次可见标识
    private boolean isFirstVisible = true;
    // 第一次不可见标识
    private boolean isFirstInvisible = true;
    // 数据是否加载完成，由具体的子类维护
    protected boolean isLoaded = false;
    // 视图创建完成标识
    private boolean isPrepared;
    protected Context mContext;

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        // 只在视图创建完成后调用视图是否可见方法
        if (isPrepared) {
            if (isVisibleToUser) {
                isFirstVisible();
            } else {
                isFirstInvisible();
            }
        }
    }

    protected void isFirstInvisible() {
        if (isFirstInvisible) {
            isFirstInvisible = false;
            onFirstUserInvisible();
        } else {
            onUserInvisible();
        }
    }

    /**
     * 相当于onPause
     */
    protected abstract void onUserInvisible();

    /**
     * fragment 第一次不可见
     */
    protected abstract void onFirstUserInvisible();

    protected void isFirstVisible() {
        if (isFirstVisible) {
            isFirstVisible = false;
            onFirstUserVisible();
        } else {
            onUserVisible();
        }
    }

    /**
     * 相当于onResume
     */
    protected abstract void onUserVisible();

    /**
     * fragment 第一次可见
     */
    protected abstract void onFirstUserVisible();

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        getMvpDelegate().onSaveInstanceState(outState);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
        getMvpDelegate().onAttach((Activity) context);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getMvpDelegate().onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
        @Nullable Bundle savedInstanceState) {
        View view;
        if (getLayoutId() != 0) {
            view = inflater.inflate(getLayoutId(), container, false);
        } else {
            view = super.onCreateView(inflater, container, savedInstanceState);
        }
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getMvpDelegate().onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
        getMvpDelegate().onResume();
        if (getUserVisibleHint()) {
            onUserVisible();
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getMvpDelegate().onActivityCreated(savedInstanceState);
        // 视图准备完成
        isPrepared = true;
        // 视图是否可见
        isVisibleToUser();
        if (isLoaded()) {
            initData(savedInstanceState);
        }
    }

    private boolean isLoaded() {
        return isLoaded;
    }

    protected void loaded() {
        isLoaded = true;
    }

    private void isVisibleToUser() {

    }

    @Override
    public void onPause() {
        super.onPause();
        getMvpDelegate().onPause();
        if (getUserVisibleHint()) {
            onUserInvisible();
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        getMvpDelegate().onStop();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        getMvpDelegate().onDestroyView();
        isPrepared = false;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        getMvpDelegate().onDestroy();
    }

    protected FragmentMvpDelegate<V, P> getMvpDelegate() {
        if (mMvpDelegate == null) {
            mMvpDelegate = new FragmentMvpDelegateImpl<>(this, this, true, true);
        }
        return mMvpDelegate;
    }

    @NonNull
    public abstract P createPresenter();

    @Override
    public P getPresenter() {
        return mPresenter;
    }

    @Override
    public void setPresenter(P presenter) {
        this.mPresenter = presenter;
    }

    @Override
    public V getMvpView() {
        return (V) this;
    }
}
