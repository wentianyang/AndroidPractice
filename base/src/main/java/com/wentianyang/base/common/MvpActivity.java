package com.wentianyang.base.common;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.hannesdorfmann.mosby3.mvp.MvpPresenter;
import com.hannesdorfmann.mosby3.mvp.delegate.ActivityMvpDelegate;
import com.hannesdorfmann.mosby3.mvp.delegate.ActivityMvpDelegateImpl;
import com.hannesdorfmann.mosby3.mvp.delegate.MvpDelegateCallback;
import com.kingja.loadsir.callback.Callback.OnReloadListener;
import com.trello.rxlifecycle2.LifecycleTransformer;
import com.wentianyang.base.common.dialog.BaseDialogFragment;
import com.wentianyang.base.mvp.BaseView;

/**
 * @Date 创建时间:  2018/8/17
 * @Author: YTW
 * @Description:
 **/

public abstract class MvpActivity<V extends BaseView, P extends MvpPresenter<V>> extends
    BaseActivity implements BaseView, MvpDelegateCallback<V, P>, OnReloadListener {

    protected ActivityMvpDelegate mMvpDelegate;
    protected P mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getMvpDelegate().onCreate(savedInstanceState);
        initData(savedInstanceState);
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        getMvpDelegate().onPostCreate(savedInstanceState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        getMvpDelegate().onSaveInstanceState(outState);
    }

    @Override
    protected void onStart() {
        super.onStart();
        getMvpDelegate().onStart();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        getMvpDelegate().onRestart();
    }

    @Override
    protected void onResume() {
        super.onResume();
        getMvpDelegate().onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        getMvpDelegate().onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
        getMvpDelegate().onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        getMvpDelegate().onDestroy();
    }

    @Override
    public void onContentChanged() {
        super.onContentChanged();
        getMvpDelegate().onContentChanged();
    }

    protected ActivityMvpDelegate<V, P> getMvpDelegate() {
        if (mMvpDelegate == null) {
            mMvpDelegate = new ActivityMvpDelegateImpl<>(this, this, true);
        }
        return mMvpDelegate;
    }

    @NonNull
    @Override
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


    @Override
    public LifecycleTransformer bindLifecycle() {
        return bindToLifecycle();
    }

//    @Override
//    public BaseDialogFragment getProgressDialog() {
//        if (mProgressDialog == null) {
//            mProgressDialog = ProgressDialog.newInstance();
//        }
//        return mProgressDialog;
//    }
//
//    @Override
//    public void showLoading() {
//        if (mProgressDialog == null) {
//            mProgressDialog = (ProgressDialog) getProgressDialog();
//        }
//        mProgressDialog.show(getFragmentManager(), ProgressDialog.TAG);
//    }
//
//    @Override
//    public void hideLoading() {
//        if (mProgressDialog != null) {
//            mProgressDialog.dismiss();
//        }
//    }


    @Override
    public BaseDialogFragment getLoadingDialog() {
        return super.getLoadingDialog();
    }
}
