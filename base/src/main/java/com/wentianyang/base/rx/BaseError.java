package com.wentianyang.base.rx;

/**
 * @Date 创建时间:  2018/8/15
 * @Author: YTW
 * @Description:
 **/

public class BaseError extends Exception {

    public static final int ERROR_HTTP = 0;
    public static final int ERROR_PARSE = 1;
    public static final int ERROR_CONNECT = 2;
    public static final int ERROR_TIME_OUT = 3;
    public static final int ERROR_UNKNOW_HOST = 4;
    public static final int ERROR_UNKNOW = 5;
    public static final int ERROR_NO_NETWORK = 6;

    private int errorType;

    public BaseError(String message, int errorType) {
        super(message);
        this.errorType = errorType;
    }

    public int getErrorType() {
        return errorType;
    }
}
