package com.wentianyang.base.common;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Toast;
import com.jaeger.library.StatusBarUtil;
import com.kingja.loadsir.callback.Callback;
import com.kingja.loadsir.callback.Callback.OnReloadListener;
import com.kingja.loadsir.core.LoadService;
import com.kingja.loadsir.core.LoadSir;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;
import com.wentianyang.base.R;
import com.wentianyang.base.callback.ConnectCallback;
import com.wentianyang.base.callback.NoNetworkCallback;
import com.wentianyang.base.callback.ParseCallback;
import com.wentianyang.base.callback.TimeOutCallback;
import com.wentianyang.base.callback.UnKnowCallback;
import com.wentianyang.base.callback.UnKnowHostCallback;
import com.wentianyang.base.common.dialog.BaseDialogFragment;
import com.wentianyang.base.common.dialog.ProgressDialog;
import com.wentianyang.base.eventbus.MsgEvent;
import com.wentianyang.base.eventbus.SuccessEvent;
import com.wentianyang.base.rx.BaseError;
import com.wentianyang.base.rx.RxBus;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * @Date 创建时间:  2018/9/4
 * @Author: YTW
 * @Description: 基本的Activity
 **/

public abstract class BaseActivity extends RxAppCompatActivity implements IBaseInit, IHub,
    OnReloadListener {

    private ProgressDialog mProgressDialog;
    // 状态页
    protected LoadService mLoadService;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getLayoutID() != 0) {
            setContentView(getLayoutID());
            // 设置统一状态栏
            if (isStatusBarColor()) {
                setStatusBarColor(this, getResources().getColor(R.color.colorPrimary));
            }
            initView();
        }

        // 是否启用eventbus
        if (enableEventBus()) {
            registerRxBus();
        }

    }

    /**
     * 注册EventBus
     */
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
                        showPageStatus((BaseError) data);
                    } else if (data instanceof SuccessEvent) {
                        registerPageState();
                        mLoadService.showSuccess();
                    }
                }
            });
    }

    private void registerPageState() {
        if (mLoadService == null) {
            mLoadService = LoadSir.getDefault().register(this, this);
        }
    }

    @Override
    public boolean isStatusBarColor() {
        return false;
    }

    /**
     * 设置状态栏颜色
     */
    protected void setStatusBarColor(Activity activity, int color) {
        StatusBarUtil.setColor(activity, color);
    }

    @Override
    public void startActivity(Activity activity, Class<?> clazz) {
        startActivity(activity, clazz, false);
    }

    @Override
    public void startActivity(Activity activity, Class<?> clazz, boolean isFinish) {
        startActivity(activity, clazz, isFinish, null);
    }

    @Override
    public void startActivity(Activity activity, Class<?> clazz, boolean isFinish, Bundle bundle) {
        Intent intent = new Intent(activity, clazz);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        activity.startActivity(intent);
        if (isFinish) {
            activity.finish();
        }
    }

    @Override
    public void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public BaseDialogFragment getLoadingDialog() {
        if (mProgressDialog == null) {
            mProgressDialog = ProgressDialog.newInstance();
            mProgressDialog.setDiaAmout(0);
        }
        return mProgressDialog;
    }

    @Override
    public void showLoading() {
        if (mProgressDialog == null) {
            mProgressDialog = (ProgressDialog) getLoadingDialog();
        }
        mProgressDialog.show(getFragmentManager(), ProgressDialog.TAG);
    }

    @Override
    public void hideLoading() {
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
        }
    }

    @Override
    public IHub getIHub() {
        return this;
    }

    /**
     * 统一处理状态页
     */
    @Override
    public void showPageStatus(BaseError error) {
        switch (error.getErrorType()) {
            case BaseError.ERROR_CONNECT:
                showPageState(ConnectCallback.class);
            case BaseError.ERROR_HTTP:
                showPageState(ConnectCallback.class);
                break;
            case BaseError.ERROR_PARSE:
                showPageState(ParseCallback.class);
                break;
            case BaseError.ERROR_TIME_OUT:
                showPageState(TimeOutCallback.class);
                break;
            case BaseError.ERROR_UNKNOW_HOST:
                showPageState(UnKnowHostCallback.class);
                break;
            case BaseError.ERROR_UNKNOW:
                showPageState(UnKnowCallback.class);
                break;
            case BaseError.ERROR_NO_NETWORK:
                showPageState(NoNetworkCallback.class);
                break;
            default:
        }
    }

    private void showPageState(Class<? extends Callback> clazz) {
        registerPageState();
        mLoadService.showCallback(clazz);
    }
}
