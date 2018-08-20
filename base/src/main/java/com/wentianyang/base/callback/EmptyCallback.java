package com.wentianyang.base.callback;

import com.kingja.loadsir.callback.Callback;
import com.wentianyang.base.R;

/**
 * @Date 创建时间:  2018/8/20
 * @Author: YTW
 * @Description:
 **/

public class EmptyCallback extends Callback {

    @Override
    protected int onCreateView() {
        return R.layout.empty_layout;
    }
}
