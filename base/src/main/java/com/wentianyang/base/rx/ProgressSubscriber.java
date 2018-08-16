package com.wentianyang.base.rx;

import android.app.DialogFragment;

/**
 * @Date 创建时间:  2018/8/15
 * @Author: YTW
 * @Description: 带Progress的Subscriber
 **/

public abstract class ProgressSubscriber<T> extends BaseSubscriber<T> {

    private DialogFragment mDialogFragment;

    public ProgressSubscriber(DialogFragment dialogFragment) {
        mDialogFragment = dialogFragment;
    }

    @Override
    public void onError(Throwable t) {
        super.onError(t);
        if (mDialogFragment != null) {
            mDialogFragment.dismiss();
        }
    }

    @Override
    public void onComplete() {
        super.onComplete();
        if (mDialogFragment != null) {
            mDialogFragment.dismiss();
        }
    }
}
