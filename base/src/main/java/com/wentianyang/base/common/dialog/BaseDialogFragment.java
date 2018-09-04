package com.wentianyang.base.common.dialog;

import android.app.Dialog;
import android.app.DialogFragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.FloatRange;
import android.support.annotation.Nullable;
import android.support.annotation.StyleRes;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;
import com.wentianyang.base.R;
import com.wentianyang.base.log.LogUtils;
import com.wentianyang.base.util.DimenUtils;
import com.wentianyang.base.util.ScreenUtils;

/**
 * @Date 创建时间:  2018/8/27
 * @Author: YTW
 * @Description:
 **/

public abstract class BaseDialogFragment extends DialogFragment {

    protected int mLayoutResId;

    private OnDialogCancelListener mDialogCancelListener;

    private float mDimAmount = 0.5f; // 透明度
    private boolean mShowBottomEnable; // 是否底部显示
    private int mMargin = 0; //左右边距
    private int mAnimStyle = 0; // 动画
    private boolean mOutCancel = true; // 点击外部取消
    protected Context mContext;
    private int mWidth;
    private int mHeight;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NO_TITLE, R.style.Base_AlertDialog);
        mLayoutResId = setLayoutId();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
        Bundle savedInstanceState) {
        View view = inflater.inflate(mLayoutResId, container, false);
        convertView(ViewHolder.create(view), this);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        initParams();
    }

    private void initParams() {
        Window window = getDialog().getWindow();
        if (window != null) {
            WindowManager.LayoutParams params = window.getAttributes();
            params.dimAmount = mDimAmount;

            // 设置dialog位置
            if (mShowBottomEnable) {
                params.gravity = Gravity.BOTTOM;
            }

            // 设置dialog宽度
            if (mWidth == 0) {
                params.width =
                    ScreenUtils.getScreenWidth(getActivity()) - 2 * DimenUtils.dp2px(mMargin);
            } else {
                params.width = DimenUtils.dp2px(mWidth);
            }

            // 设置dialog高度
            if (mHeight == 0) {
                params.height = LayoutParams.WRAP_CONTENT;
            } else {
                params.height = DimenUtils.dp2px(mHeight);
            }

            // 设置动画
            if (mAnimStyle != 0) {
                window.setWindowAnimations(mAnimStyle);
            }

//            window.setBackgroundDrawableResource(android.R.color.transparent);

            window.setAttributes(params);
        }
        setCancelable(mOutCancel);
    }

    /**
     * 设置dialog布局
     */
    public abstract int setLayoutId();

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return super.onCreateDialog(savedInstanceState);
    }

    /**
     * 设置透明度
     */
    public BaseDialogFragment setDiaAmout(@FloatRange(from = 0, to = 1) float dimAmount) {
        mDimAmount = dimAmount;
        return this;
    }

    /**
     * 是否显示底部
     */
    public BaseDialogFragment setShowBottom(boolean showBottom) {
        mShowBottomEnable = showBottom;
        return this;
    }

    /**
     * 设置宽高
     */
    public BaseDialogFragment setSize(int width, int height) {
        mWidth = width;
        mHeight = height;
        return this;
    }

    public BaseDialogFragment setMargin(int margin) {
        mMargin = margin;
        return this;
    }

    public BaseDialogFragment setAnimStyle(@StyleRes int animStyle) {
        mAnimStyle = animStyle;
        return this;
    }

    public BaseDialogFragment setOutCancel(boolean outCancel) {
        mOutCancel = outCancel;
        return this;
    }

    public BaseDialogFragment show(FragmentManager manager) {
        super.show(manager, String.valueOf(System.currentTimeMillis()));
        return this;
    }

    public void setDialogCancelListener(OnDialogCancelListener dialogCancelListener) {
        mDialogCancelListener = dialogCancelListener;
    }

    @Override
    public void onCancel(DialogInterface dialog) {
        super.onCancel(dialog);
        if (mDialogCancelListener != null) {
            LogUtils.d("dialog cancel...");
            mDialogCancelListener.onCancel();
        }
    }

    public abstract void convertView(ViewHolder holder, BaseDialogFragment dialog);

    public interface OnDialogCancelListener {

        void onCancel();
    }
}
