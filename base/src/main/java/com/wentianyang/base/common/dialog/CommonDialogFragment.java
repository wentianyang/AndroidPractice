package com.wentianyang.base.common.dialog;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build.VERSION;
import android.os.Build.VERSION_CODES;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

public class CommonDialogFragment extends DialogFragment {

    /*监听弹窗是否取消*/
    private OnDialogCancelListener mCancelListener;

    /*通过回调获取要显示的Dialog*/
    private OnCallDialog mOnCallDialog;

    public static CommonDialogFragment newInstance(OnCallDialog callDialog, boolean cancelable) {
        return newInstance(callDialog, cancelable, null);
    }

    public static CommonDialogFragment newInstance(OnCallDialog callDialog, boolean cancelable,
        OnDialogCancelListener cancelListener) {
        CommonDialogFragment fragment = new CommonDialogFragment();
        fragment.setCancelable(cancelable);
        fragment.mCancelListener = cancelListener;
        fragment.mOnCallDialog = callDialog;
        return fragment;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        if (mOnCallDialog == null) {
            super.onCreateDialog(savedInstanceState);
        }
        return mOnCallDialog.getDialog(getActivity());
    }

    @Override
    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        if (dialog != null) {
            // 5.0以下会出现白色背景边框，若在5.0以上则会造成文字部分的背景也变成透明
            if (VERSION.SDK_INT <= VERSION_CODES.KITKAT) {
                // 目前只有这两个dialog会出现边框
                if (dialog instanceof ProgressDialog || dialog instanceof DatePickerDialog) {
                    getDialog().getWindow()
                        .setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                }
            }
            Window window = getDialog().getWindow();
            WindowManager.LayoutParams layoutParams = window.getAttributes();
            layoutParams.dimAmount = 0.0f;
            window.setAttributes(layoutParams);
        }
    }

    @Override
    public void onCancel(DialogInterface dialog) {
        super.onCancel(dialog);
        if (mCancelListener != null) {
            mCancelListener.onCancel();
        }
    }

    public interface OnCallDialog {

        Dialog getDialog(Context context);
    }

    public interface OnDialogCancelListener {

        void onCancel();
    }
}
