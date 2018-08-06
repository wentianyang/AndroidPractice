package com.wentianyang.routerlib;

import android.content.Context;
import android.util.Log;
import java.util.HashMap;

/**
 * @Date 创建时间:  2018/8/6
 * @Author: YTW
 * @Description:
 **/

public class Router {

    private static volatile Router INSTANCE = null;
    private HashMap<String, RouterAction> mActions = null;
    private String TAG = "Router";

    private Router() {
        mActions = new HashMap<>();
    }

    public static Router getInstance() {
        if (INSTANCE == null) {
            synchronized (Router.class) {
                if (INSTANCE == null) {
                    INSTANCE = new Router();
                }
            }
        }
        return INSTANCE;
    }

    public void registerActions(String action, RouterAction routerAction) {
        if (mActions.containsKey(action)) {
            return;
        }
        mActions.put(action, routerAction);
    }

    public RouterResponse sendMessage(Context context, RouterRequest request) {
        RouterResponse response = new RouterResponse();
        RouterAction action = findRequest(request);
        if (action != null) {
            Object object = action.startAction(context, request.getData());
            response.setStatus(RouterResponse.SUCCESS_CODE, RouterResponse.SUCCESS_DES, object);
        } else {
            response.setStatus(RouterResponse.FAIL_CODE, RouterResponse.FAIL_DES,
                "can not find this action, check to see if you have been registered...");
        }
        return response;
    }

    private RouterAction findRequest(RouterRequest request) {
        String action = request.getAction();
        Log.d(TAG, "findRequest: " + action);
        Log.d(TAG, "findRequest: " + mActions.toString());
        if (mActions.containsKey(action)) {
            return mActions.get(action);
        }
        return null;
    }
}
