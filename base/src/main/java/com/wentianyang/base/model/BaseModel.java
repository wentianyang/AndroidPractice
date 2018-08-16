package com.wentianyang.base.model;

import java.io.Serializable;

/**
 * @Date 创建时间:  2018/8/15
 * @Author: YTW
 * @Description:
 **/

public class BaseModel<T> implements Serializable{

//    private int code;
//    private String message;
//    private T data;
//
//    public int getCode() {
//        return code;
//    }
//
//    @Override
//    public String toString() {
//        return "BaseModel{" +
//            "code=" + code +
//            ", message='" + message + '\'' +
//            ", data=" + data +
//            '}';
//    }
//
//    public void setCode(int code) {
//        this.code = code;
//    }
//
//    public String getMessage() {
//        return message;
//    }
//
//    public void setMessage(String message) {
//        this.message = message;
//    }
//
//    public T getData() {
//        return data;
//    }
//
//    public void setData(T data) {
//        this.data = data;
//    }
    private boolean error;

    private T results;

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public T getResults() {
        return results;
    }

    public void setResults(T results) {
        this.results = results;
    }
}
