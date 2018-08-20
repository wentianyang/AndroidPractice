package com.wentianyang.base.callback;

import com.kingja.loadsir.callback.Callback;
import com.wentianyang.base.R;

/**
 * Created by YTW on 2018/8/20.
 */

public class NoNetworkCallback extends Callback {

    @Override
    protected int onCreateView() {
        return R.layout.error_no_net_work_layout;
    }
}
