package com.wentianyang.base.mvp;

import android.os.Bundle;
import com.hannesdorfmann.mosby3.mvp.MvpView;
import com.trello.rxlifecycle2.LifecycleTransformer;
import com.wentianyang.base.common.dialog.BaseDialogFragment;
import com.wentianyang.base.rx.BaseError;

/**
 * @Date 创建时间:  2018/8/17
 * @Author: YTW
 * @Description:
 **/

public interface BaseView extends MvpView {

    void showLoading();

    void hideLoading();

    void showError(BaseError error);

    int getLayoutId();

    void bindUI();

    void initData(Bundle savedInstanceState);

    LifecycleTransformer bindLifecycle();

    BaseDialogFragment getProgressDialog();
}
