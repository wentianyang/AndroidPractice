package com.wentianyang.androidpractice.action.imagebar;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import com.wentianyang.androidpractice.ImageBarActivity;
import com.wentianyang.routerlib.RouterAction;
import java.util.HashMap;

/**
 * @Date 创建时间:  2018/8/6
 * @Author: YTW
 * @Description:
 **/

public class ImageBarAction extends RouterAction {

    @Override
    public Object startAction(Context context, HashMap<String, Object> requestData) {
        if (context instanceof Activity) {
            Intent intent = new Intent(context, ImageBarActivity.class);
            intent.putExtra(ImageBarActivity.EXTRA_DATA,
                requestData.get(ImageBarActivity.EXTRA_DATA).toString());
            context.startActivity(intent);
        } else {
            Intent intent = new Intent(context, ImageBarActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        }
        return "success";
    }
}
