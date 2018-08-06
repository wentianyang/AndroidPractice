package com.wentianyang.routerlib;

import java.util.HashMap;

/**
 * @Date 创建时间:  2018/8/6
 * @Author: YTW
 * @Description: 消息发送载体
 **/

public class RouterRequest {

    // 通道名称
    private String action;
    // 通道传递的消息体
    private HashMap<String, Object> data;

    private RouterRequest() {
        data = new HashMap<>();
    }

    public static RouterRequest create() {
        return new RouterRequest();
    }

    public String getAction() {
        return action;
    }

    public HashMap<String, Object> getData() {
        return data;
    }

    public RouterRequest data(String action, Object value) {
        this.data.put(action, value);
        return this;
    }

    public RouterRequest action(String action) {
        this.action = action;
        return this;
    }
}
