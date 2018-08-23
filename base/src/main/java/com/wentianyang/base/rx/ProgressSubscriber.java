package com.wentianyang.base.rx;

import android.app.DialogFragment;
import android.util.Log;

/**
 * @Date 创建时间:  2018/8/15
 * @Author: YTW
 * @Description: 带Progress的Subscriber
 **/

public abstract class ProgressSubscriber<T> extends BaseSubscriber<T> {

    private static final String TAG = "ProgressSubscriber";

    private DialogFragment mDialogFragment;

    public ProgressSubscriber(DialogFragment dialogFragment) {
        mDialogFragment = dialogFragment;
    }

    @Override
    public void onError(Throwable t) {
        super.onError(t);
        Log.d(TAG, "onError: " + mDialogFragment);
        if (mDialogFragment != null) {
            mDialogFragment.dismiss();
        }
    }

    @Override
    public void onComplete() {
        super.onComplete();
        Log.d(TAG, "onComplete: " + mDialogFragment);
        if (mDialogFragment != null) {
            mDialogFragment.dismiss();
        }
    }
}
