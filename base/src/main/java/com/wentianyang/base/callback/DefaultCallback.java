package com.wentianyang.base.callback;

import android.content.Context;
import android.view.View;
import com.kingja.loadsir.callback.Callback;
import com.wentianyang.base.R;

/**
 * @Date 创建时间:  2018/8/23
 * @Author: YTW
 * @Description:
 **/

public class DefaultCallback extends Callback {

    @Override
    protected int onCreateView() {
        return R.layout.default_progress_layout;
    }

    @Override
    public boolean getSuccessVisible() {
        return super.getSuccessVisible();
    }

    @Override
    protected boolean onReloadEvent(Context context, View view) {
        return true;
    }
}
