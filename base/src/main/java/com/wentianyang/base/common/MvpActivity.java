package com.wentianyang.base.common;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import com.hannesdorfmann.mosby3.mvp.MvpPresenter;
import com.hannesdorfmann.mosby3.mvp.delegate.ActivityMvpDelegate;
import com.hannesdorfmann.mosby3.mvp.delegate.ActivityMvpDelegateImpl;
import com.hannesdorfmann.mosby3.mvp.delegate.MvpDelegateCallback;
import com.kingja.loadsir.callback.Callback;
import com.kingja.loadsir.callback.Callback.OnReloadListener;
import com.kingja.loadsir.core.LoadService;
import com.kingja.loadsir.core.LoadSir;
import com.trello.rxlifecycle2.LifecycleTransformer;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;
import com.wentianyang.base.SuccessEvent;
import com.wentianyang.base.callback.ConnectCallback;
import com.wentianyang.base.callback.NoNetworkCallback;
import com.wentianyang.base.callback.ParseCallback;
import com.wentianyang.base.callback.TimeOutCallback;
import com.wentianyang.base.callback.UnKnowCallback;
import com.wentianyang.base.callback.UnKnowHostCallback;
import com.wentianyang.base.mvp.BaseView;
import com.wentianyang.base.rx.BaseError;
import com.wentianyang.base.rx.MsgEvent;
import com.wentianyang.base.rx.RxBus;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * @Date 创建时间:  2018/8/17
 * @Author: YTW
 * @Description:
 **/

public abstract class MvpActivity<V extends BaseView, P extends MvpPresenter<V>> extends
    RxAppCompatActivity implements BaseView, MvpDelegateCallback<V, P>, OnReloadListener {

    private static final String TAG = "MvpActivity";

    protected ActivityMvpDelegate mMvpDelegate;
    protected P mPresenter;
    protected LoadService mLoadService;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getMvpDelegate().onCreate(savedInstanceState);

        if (getLayoutId() > 0) {
            setContentView(getLayoutId());
            bindUI();
        }
        initData(savedInstanceState);

        registerRxBus();
    }

    @SuppressLint("CheckResult")
    private void registerRxBus() {
        RxBus.getInstance().toObservable(MsgEvent.class)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(new Consumer<MsgEvent>() {
                @Override
                public void accept(MsgEvent msgEvent) throws Exception {
                    Object data = msgEvent.getData();
                    if (data instanceof BaseError) {
                        showError((BaseError) data);
                    } else if (data instanceof SuccessEvent) {
                        registerPageState();
                        mLoadService.showSuccess();
                    }
                }
            });
    }

    @Override
    public void showError(BaseError error) {
        switch (error.getErrorType()) {
            case BaseError.ERROR_CONNECT:
                showPageState(ConnectCallback.class);
                Log.d(TAG, "showError: ERROR_CONNECT");
            case BaseError.ERROR_HTTP:
                showPageState(ConnectCallback.class);
                Log.d(TAG, "showError: ERROR_HTTP");
                break;
            case BaseError.ERROR_PARSE:
                showPageState(ParseCallback.class);
                Log.d(TAG, "showError: ERROR_PARSE");
                break;
            case BaseError.ERROR_TIME_OUT:
                showPageState(TimeOutCallback.class);
                Log.d(TAG, "showError: ERROR_TIME_OUT");
                break;
            case BaseError.ERROR_UNKNOW_HOST:
                showPageState(UnKnowHostCallback.class);
                Log.d(TAG, "showError: ERROR_UNKNOW_HOST");
                break;
            case BaseError.ERROR_UNKNOW:
                showPageState(UnKnowCallback.class);
                Log.d(TAG, "showError: ERROR_UNKNOW");
                break;
            case BaseError.ERROR_NO_NETWORK:
                showPageState(NoNetworkCallback.class);
                Log.d(TAG, "showError: ERROR_NO_NETWORK");
                break;
            default:
        }
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

    private void registerPageState() {
        if (mLoadService == null) {
            mLoadService = LoadSir.getDefault().register(this, this);
        }
    }

    private void showPageState(Class<? extends Callback> clazz) {
        registerPageState();
        mLoadService.showCallback(clazz);
    }

    @Override
    public LifecycleTransformer bindLifecycle() {
        return bindToLifecycle();
    }
}
