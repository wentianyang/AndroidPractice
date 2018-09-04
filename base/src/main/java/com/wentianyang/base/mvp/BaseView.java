package com.wentianyang.base.mvp;

import android.os.Bundle;
import com.hannesdorfmann.mosby3.mvp.MvpView;
import com.trello.rxlifecycle2.LifecycleTransformer;
import com.wentianyang.base.common.dialog.BaseDialogFragment;

/**
 * @Date 创建时间:  2018/8/17
 * @Author: YTW
 * @Description:
 **/

public interface BaseView extends MvpView {
    
    void initData(Bundle savedInstanceState);

    LifecycleTransformer bindLifecycle();

    BaseDialogFragment getLoadingDialog();
}
