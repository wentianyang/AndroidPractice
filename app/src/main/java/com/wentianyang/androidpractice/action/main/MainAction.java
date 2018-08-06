package com.wentianyang.androidpractice.action.main;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import com.wentianyang.androidpractice.MainActivity;
import com.wentianyang.routerlib.RouterAction;
import java.util.HashMap;

/**
 * @Date 创建时间:  2018/8/6
 * @Author: YTW
 * @Description:
 **/

public class MainAction extends RouterAction {

    @Override
    public Object startAction(Context context, HashMap<String, Object> requestData) {
        if (context instanceof Activity) {
            Intent intent = new Intent(context, MainActivity.class);
            context.startActivity(intent);
        } else {
            Intent intent = new Intent(context, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        }
        return "success";
    }
}
