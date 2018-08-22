package com.wentianyang.base.rx;

/**
 * @Date 创建时间:  2018/8/16
 * @Author: YTW
 * @Description:
 **/

public class MsgEvent<T> {

    private int type;
    private int request;
    private String msg;
    private T data;

    public MsgEvent(T data) {
        this.data = data;
    }

    public MsgEvent(int type, int request, String msg) {
        this.type = type;
        this.request = request;
        this.msg = msg;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getRequest() {
        return request;
    }

    public void setRequest(int request) {
        this.request = request;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
