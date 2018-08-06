package com.wentianyang.routerlib;

import android.content.Context;
import java.util.HashMap;

/**
 * @Date 创建时间:  2018/8/6
 * @Author: YTW
 * @Descriptioon: 消息通道
 **/

public abstract class RouterAction {

    public abstract Object startAction(Context context, HashMap<String, Object> requestData);
}
