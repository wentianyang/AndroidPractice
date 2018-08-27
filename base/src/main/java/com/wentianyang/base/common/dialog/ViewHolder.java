package com.wentianyang.base.common.dialog;

import android.support.annotation.IdRes;
import android.util.SparseArray;
import android.view.View;
import android.widget.TextView;

/**
 * @Date 创建时间:  2018/8/27
 * @Author: YTW
 * @Description:
 **/

public class ViewHolder {

    private SparseArray<View> mViews;
    private View convertView;

    public ViewHolder(View view) {
        convertView = view;
        mViews = new SparseArray<>();
    }

    public static ViewHolder create(View view) {
        return new ViewHolder(view);
    }

    public <T extends View> T getView(@IdRes int viewId) {
        View view = mViews.get(viewId);
        if (view == null) {
            view = convertView.findViewById(viewId);
            mViews.put(viewId, view);
        }
        return (T) view;
    }

    public void setText(int viewId, String text) {
        TextView textView = getView(viewId);
        textView.setText(text);
    }

    public void setTextColor(int viewId, int colorId) {
        TextView textView = getView(viewId);
        textView.setTextColor(colorId);
    }

    public void setBackgroundResource(int viewId, int resId) {
        View view = getView(viewId);
        view.setBackgroundResource(resId);
    }

    public void setOnClickListener(int viewId, View.OnClickListener listener) {
        View view = getView(viewId);
        view.setOnClickListener(listener);
    }
}
