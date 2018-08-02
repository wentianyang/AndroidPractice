package com.wentianyang.rxjavalib;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.jakewharton.rxbinding2.view.RxView;
import com.jakewharton.rxbinding2.widget.RxTextView;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiFunction;
import java.util.concurrent.TimeUnit;

public class RxDemoActivity extends AppCompatActivity {

    private static final String TAG = "RxDemoActivity";

    public static void startActivity(Activity activity) {
        Intent intent = new Intent(activity, RxDemoActivity.class);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_demo);

        final Button button = findViewById(R.id.btn_rx);
        EditText account = findViewById(R.id.et_account);
        EditText pwd = findViewById(R.id.et_pwd);
        TextView title = findViewById(R.id.tv_title);

        RxView.clicks(button)
            .throttleFirst(3, TimeUnit.SECONDS)
            .subscribe(new Observer<Object>() {
                @Override
                public void onSubscribe(Disposable d) {
                    Log.d(TAG, "onSubscribe: ");
                }

                @Override
                public void onNext(Object o) {
                    Log.d(TAG, "onNext: ");
                }

                @Override
                public void onError(Throwable e) {
                    Log.e(TAG, "onError: ");
                }

                @Override
                public void onComplete() {
                    Log.d(TAG, "onComplete: ");
                }
            });

        /**
         * 使用combineLatest来做表单验证
         */
        Observable.combineLatest(RxTextView.textChanges(account),
            RxTextView.textChanges(pwd), new BiFunction<CharSequence, CharSequence, Boolean>() {
                @Override
                public Boolean apply(CharSequence account, CharSequence pwd) throws Exception {
                    return !TextUtils.isEmpty(account) && !TextUtils.isEmpty(pwd);
                }
            })
            .subscribe(new Observer<Boolean>() {
                @Override
                public void onSubscribe(Disposable d) {

                }

                @Override
                public void onNext(Boolean aBoolean) {
                    Log.d(TAG, "combineLatest onNext : " + aBoolean);
                    button.setClickable(aBoolean);
                }

                @Override
                public void onError(Throwable e) {

                }

                @Override
                public void onComplete() {

                }
            });
    }
}
