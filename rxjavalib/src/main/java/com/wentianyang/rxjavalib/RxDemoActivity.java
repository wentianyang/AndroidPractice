package com.wentianyang.rxjavalib;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import com.jakewharton.rxbinding2.view.RxView;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
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

        Button button = findViewById(R.id.btn_rx);

        RxView.clicks(button)
            .throttleFirst(3,  TimeUnit.SECONDS)
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
    }
}
