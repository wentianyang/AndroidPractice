package com.wentianyang.androidpractice.systembar;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import com.wentianyang.androidpractice.BaseActivity;
import com.wentianyang.androidpractice.R;
import com.wentianyang.base.eventbus.MsgEvent;
import com.wentianyang.base.rx.RxBus;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class ColorBarActivity extends BaseActivity {

    private static final String TAG = "ColorBarActivity";

    public static void startActivity(Activity activity) {
        Intent intent = new Intent(activity, ColorBarActivity.class);
        activity.startActivity(intent);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_color_bar;
    }

    @Override
    protected void initBeforeView() {
        RxBus.getInstance().toObservableSticky(MsgEvent.class)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(new Observer<MsgEvent>() {
                @Override
                public void onSubscribe(Disposable disposable) {
                    Log.d(TAG, "onSubscribe: ");
                }

                @Override
                public void onNext(MsgEvent msgEvent) {
                    Log.d(TAG, "onNext: " + msgEvent.getMsg());
                }

                @Override
                public void onError(Throwable throwable) {
                    Log.d(TAG, "onError: ");
                }

                @Override
                public void onComplete() {
                    Log.d(TAG, "onComplete: ");
                }
            });
    }

    @Override
    protected void initView() {

    }
}
