package com.wentianyang.base.rx;

import com.wentianyang.base.common.IHub;

/**
 * @Date 创建时间:  2018/8/15
 * @Author: YTW
 * @Description: 带Progress的Subscriber
 **/

public abstract class ProgressSubscriber<T> extends BaseSubscriber<T> {

    //    private DialogFragment mDialogFragment;
    private IHub mIHub;

    public ProgressSubscriber(IHub hub) {
//        mDialogFragment = dialogFragment;
        mIHub = hub;
    }

    @Override
    public void onError(Throwable t) {
        super.onError(t);
//        if (mDialogFragment != null) {
//            mDialogFragment.dismiss();
//        }
        mIHub.hideLoading();
    }

    @Override
    public void onComplete() {
        super.onComplete();
//        if (mDialogFragment != null) {
//            mDialogFragment.dismiss();
//        }
        mIHub.hideLoading();
    }
}
