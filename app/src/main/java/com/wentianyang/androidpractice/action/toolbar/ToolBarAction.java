package com.wentianyang.androidpractice.action.toolbar;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import com.wentianyang.androidpractice.ToolBarActivity;
import com.wentianyang.routerlib.RouterAction;
import java.util.HashMap;

/**
 * Created by YTW on 2018/8/6.
 */

public class ToolBarAction extends RouterAction {

    @Override
    public Object startAction(Context context, HashMap<String, Object> requestData) {
        if (context instanceof Activity) {
            Intent intent = new Intent(context, ToolBarActivity.class);
            context.startActivity(intent);
        } else {
            Intent intent = new Intent(context, ToolBarActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        }
        return "success";
    }
}
