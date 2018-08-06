package com.wentianyang.routerlib;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * @Date 创建时间:  2018/8/6
 * @Author: YTW
 * @Description: 为了让系统成为一个闭环，我们要在消息发送之后，给发送者一个反馈，所以这里就需要实现消息反馈的载体类
 **/

public class RouterResponse {

    public final static int SUCCESS_CODE = 1;
    public final static int FAIL_CODE = 0;
    public final static String SUCCESS_DES = "success";
    public final static String FAIL_DES = "fail";

    private int statusCode;
    private String statusDes;
    private Object body;

    public JSONObject getResult() {
        JSONObject jsResult = null;
        try {
            jsResult = new JSONObject()
                .put("statusCode", statusCode)
                .put("statusDes", statusDes)
                .put("body", body);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsResult;
    }

    public void setStatus(int statusCode, String statusDes, Object body) {
        this.statusCode = statusCode;
        this.statusDes = statusDes;
        this.body = body;
    }
}
