package com.wentianyang.base.rx;

import com.google.gson.JsonParseException;
import com.google.gson.stream.MalformedJsonException;
import com.wentianyang.base.eventbus.MsgEvent;
import com.wentianyang.base.eventbus.SuccessEvent;
import com.wentianyang.base.log.LogUtils;
import com.wentianyang.base.model.BaseModel;
import io.reactivex.subscribers.ResourceSubscriber;
import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.text.ParseException;
import org.json.JSONException;
import retrofit2.HttpException;

/**
 * @Date 创建时间:  2018/8/15
 * @Author: YTW
 * @Description:
 **/

public abstract class BaseSubscriber<T> extends ResourceSubscriber<BaseModel<T>> {

    @Override
    public void onNext(BaseModel<T> model) {
        if (model.isError()) {
            onError(new ConnectException());
        } else {
            onSuccess(model.getResults());
            RxBus.getInstance().post(new MsgEvent<>(new SuccessEvent()));
        }
    }

    @Override
    public void onError(Throwable t) {
        LogUtils.e(t, "subscriber error");
        BaseError baseError;
//        CompositeException exception = (CompositeException) t;
//        if (exception.size() > 0) {
//            t = exception.getExceptions().get(0);
//        }

        if (t instanceof HttpException) {
            // Http 错误
            baseError = new BaseError("HTTP 错误", BaseError.ERROR_HTTP);
        } else if (t instanceof JSONException
            || t instanceof JsonParseException
            || t instanceof ParseException
            || t instanceof MalformedJsonException) {
            // 数据解析错误
            baseError = new BaseError("数据解析错误", BaseError.ERROR_PARSE);
        } else if (t instanceof ConnectException) {
            // 网络连接错误
            baseError = new BaseError("网络连接错误", BaseError.ERROR_CONNECT);
        } else if (t instanceof SocketTimeoutException) {
            // 网络连接超时错误
            baseError = new BaseError("网络连接超时", BaseError.ERROR_TIME_OUT);
        } else if (t instanceof UnknownHostException) {
            // 未知服务器错误
            baseError = new BaseError("未知服务器错误", BaseError.ERROR_UNKNOW_HOST);
        } else {
            baseError = new BaseError("未知错误", BaseError.ERROR_UNKNOW);
        }
        onFail(baseError);
        RxBus.getInstance().post(new MsgEvent<>(baseError));
    }

    @Override
    public void onComplete() {

    }

    public abstract void onSuccess(T t);

    public abstract void onFail(BaseError error);
}
