package com.wentianyang.base.callback;

import com.kingja.loadsir.callback.Callback;
import com.wentianyang.base.R;

/**
 * Created by YTW on 2018/8/17.
 */

public class UnKnowHostCallback extends Callback {

    @Override
    protected int onCreateView() {
        return R.layout.error_host_layout;
    }
}
