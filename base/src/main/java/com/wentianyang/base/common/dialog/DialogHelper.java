package com.wentianyang.base.common.dialog;

import android.app.Dialog;
import android.app.FragmentManager;
import android.app.ProgressDialog;
import android.content.Context;
import com.wentianyang.base.common.dialog.CommonDialogFragment.OnCallDialog;
import com.wentianyang.base.common.dialog.CommonDialogFragment.OnDialogCancelListener;

/**
 * @Date 创建时间:  2018/8/23
 * @Author: YTW
 * @Description: 用来显示各种Dialog
 **/

public class DialogHelper {

    private static final String TAG = "DialogHelper";

    private static final String PROGRESS_TAG = TAG + ":pregress";

    public static CommonDialogFragment showProgress(FragmentManager fragmentManager,
        String message) {
        return showProgress(fragmentManager, message, true, null);
    }

    public static CommonDialogFragment showProgress(FragmentManager fragmentManager, String message,
        boolean cancelable) {
        return showProgress(fragmentManager, message, cancelable, null);
    }

    public static CommonDialogFragment showProgress(FragmentManager fragmentManager,
        final String message,
        boolean cancelable, OnDialogCancelListener listener) {
        CommonDialogFragment fragment = CommonDialogFragment.newInstance(new OnCallDialog() {
            @Override
            public Dialog getDialog(Context context) {
                ProgressDialog dialog = new ProgressDialog(context);
                dialog.setMessage(message);
                return dialog;
            }
        }, cancelable, listener);
        fragment.show(fragmentManager, PROGRESS_TAG);
        return fragment;
    }
}
