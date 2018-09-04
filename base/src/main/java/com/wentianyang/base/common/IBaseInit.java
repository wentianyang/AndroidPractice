package com.wentianyang.base.common;

import android.app.Activity;
import android.os.Bundle;
import com.wentianyang.base.common.dialog.BaseDialogFragment;
import com.wentianyang.base.rx.BaseError;

/**
 * @Date 创建时间:  2018/9/4
 * @Author: YTW
 * @Description:
 **/

public interface IBaseInit {

    int getLayoutID();

    void initView();

    boolean isStatusBarColor();

    void startActivity(Activity activity, Class<?> clazz);

    void startActivity(Activity activity, Class<?> clazz, boolean isFinish);

    void startActivity(Activity activity, Class<?> clazz, boolean isFinish, Bundle bundle);

    void showToast(String msg);

    void showLoading();

    void hideLoading();

    BaseDialogFragment getLoadingDialog();

    boolean enableEventBus();

    void showPageStatus(BaseError error);
}
