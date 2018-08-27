package com.wentianyang.base.common.dialog;

import com.wentianyang.base.R;

/**
 * @Date 创建时间:  2018/8/27
 * @Author: YTW
 * @Description:
 **/

public class ProgressDialog extends BaseDialogFragment {

    public static ProgressDialog newInstance() {
        ProgressDialog dialog = new ProgressDialog();
        return dialog;
    }

    @Override
    public int setLayoutId() {
        return R.layout.dialog_loading;
    }

    @Override
    public void convertView(ViewHolder holder, BaseDialogFragment dialog) {

    }
}
