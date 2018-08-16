package com.wentianyang.base.rx;

import com.jakewharton.rxrelay2.PublishRelay;
import com.jakewharton.rxrelay2.Relay;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Date 创建时间:  2018/8/16
 * @Author: YTW
 * @Description: 基于RxJava事件总线
 **/

public class RxBus {

    private final Relay<Object> mBus;
    private final Map<Class<?>, Object> mStickyEventMap;

    private RxBus() {
        mBus = PublishRelay.create().toSerialized();
        mStickyEventMap = new ConcurrentHashMap<>();
    }

    public static RxBus getInstance() {
        return Holder.sRxBus;
    }

    private static class Holder {

        private static RxBus sRxBus = new RxBus();
    }

    /**
     * 发送事件
     */
    public void post(Object event) {
        mBus.accept(event);
    }

    public <T> Observable<T> toObservable(Class<T> tClass) {
        return mBus.ofType(tClass);
    }

    public Observable<Object> toObservable() {
        return mBus;
    }

    public boolean hasObservable() {
        return mBus.hasObservers();
    }

    /**
     * 发送一个Stick事件
     */
    public void psotSticky(Object event) {
        synchronized (mStickyEventMap) {
            mStickyEventMap.put(event.getClass(), event);
        }
        post(event);
    }

    /**
     * 根据传递的 eventType 返回特定类型eventType)的 被观察者
     */
    public <T> Observable<T> toObservableSticky(final Class<T> eventType) {
        synchronized (mStickyEventMap) {
            Observable<T> observable = mBus.ofType(eventType);
            final Object event = mStickyEventMap.get(eventType);
            if (event != null) {
                // 如果之前发生了postStick事件，则在订阅事件发生后，发送这个事件
                return observable.mergeWith(Observable.create(new ObservableOnSubscribe<T>() {
                    @Override
                    public void subscribe(ObservableEmitter<T> observableEmitter) throws Exception {
                        observableEmitter.onNext(eventType.cast(event));
                    }
                }));
            } else {
                return observable;
            }
        }
    }

    /**
     * 根据eventType获取StickyType事件
     */
    public <T> T getStickyEvent(Class<T> eventType) {
        synchronized (mStickyEventMap) {
            return eventType.cast(mStickyEventMap.get(eventType));
        }
    }

    /**
     * 移除指定的eventType的Sticky事件
     */
    public <T> T removeStickyEvent(Class<T> eventType) {
        synchronized (mStickyEventMap) {
            return eventType.cast(mStickyEventMap.remove(eventType));
        }
    }

    /**
     * 移除所有的Sticky事件
     */
    public void removeAllStickyEvents() {
        synchronized (mStickyEventMap) {
            mStickyEventMap.clear();
        }
    }
}
