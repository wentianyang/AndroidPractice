package com.wentianyang.base.rx;

import android.content.Context;
import com.wentianyang.base.common.IHub;
import com.wentianyang.base.eventbus.MsgEvent;
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
                            if (!NetUtils.isConnected(context)) {
                                subscription.cancel();
                                BaseError error = new BaseError("请检查网络连接...",
                                    BaseError.ERROR_NO_NETWORK);
                                RxBus.getInstance().post(new MsgEvent<>(error));
                            }
                        }
                    })
                    .observeOn(AndroidSchedulers.mainThread());
            }
        };
    }

    /**
     * 带进度条
     */
    public static  FlowableTransformer schedulerWithProgress(final Context context,
        final IHub hub) {
        return new FlowableTransformer() {
            @Override
            public Publisher apply(Flowable flowable) {
                return flowable
                    .subscribeOn(Schedulers.io())
                    .doOnSubscribe(new Consumer<Subscription>() {
                        @Override
                        public void accept(final Subscription subscription) throws Exception {
                            // 检查网络连接
                            if (!NetUtils.isConnected(context)) {
                                subscription.cancel();
                                BaseError error = new BaseError("请检查网络连接...",
                                    BaseError.ERROR_NO_NETWORK);
                                RxBus.getInstance().post(new MsgEvent<>(error));
                            } else {
//                                dialog.show(((Activity) context).getFragmentManager())
//                                    .setOutCancel(true)
//                                    .setSize(DimenUtils.dp2px(45), DimenUtils.dp2px(45))
//                                    .setDialogCancelListener(new OnDialogCancelListener() {
//                                        @Override
//                                        public void onCancel() {
//                                            subscription.cancel();
//                                        }
//                                    });
                                hub.showLoading();
                            }
                        }
                    })
                    .observeOn(AndroidSchedulers.mainThread());
            }
        };
    }
}
