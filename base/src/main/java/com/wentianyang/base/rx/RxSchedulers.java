package com.wentianyang.base.rx;

import android.app.DialogFragment;
import android.content.Context;
import com.wentianyang.base.util.NetUtils;
import io.reactivex.Flowable;
import io.reactivex.FlowableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import org.reactivestreams.Publisher;
import org.reactivestreams.Subscription;

/**
 * @Date 创建时间:  2018/8/15
 * @Author: YTW
 * @Description: 统一线程调度
 **/

public class RxSchedulers {

    /**
     * 基本调度
     */
    public static <T> FlowableTransformer<T, T> scheduler(final Context context) {
        return new FlowableTransformer<T, T>() {
            @Override
            public Publisher<T> apply(Flowable<T> flowable) {
                return flowable
                    .subscribeOn(Schedulers.io())
                    .doOnSubscribe(new Consumer<Subscription>() {
                        // 可以用作流程开始前的初始化
                        // doOnSubscribe() 执行在 subscribe() 发生的线程；而如果在 doOnSubscribe() 之后有 subscribeOn() 的话，它将执行在离它最近的 subscribeOn() 所指定的线程
                        @Override
                        public void accept(Subscription subscription) throws Exception {
                            // 检查网络连接
                            if (!NetUtils.isConnted(context)) {
                                subscription.cancel();
                                // TODO: 2018/8/15 发送无网络事件
                            }
                        }
                    })
                    .observeOn(AndroidSchedulers.mainThread());
            }
        };
    }

    public static <T> FlowableTransformer<T, T> scheduler(final Context context,
        final DialogFragment dialog) {
        return new FlowableTransformer<T, T>() {
            @Override
            public Publisher<T> apply(Flowable<T> flowable) {
                return flowable
                    .subscribeOn(Schedulers.io())
                    .doOnSubscribe(new Consumer<Subscription>() {
                        @Override
                        public void accept(Subscription subscription) throws Exception {
                            // 检查网络连接
                            if (!NetUtils.isConnted(context)) {
                                subscription.cancel();
                            } else {
                                if (dialog != null) {
                                    // TODO: 2018/8/15 Dialog 相关操作
                                }
                            }
                        }
                    })
                    .observeOn(AndroidSchedulers.mainThread());
            }
        };
    }
}
