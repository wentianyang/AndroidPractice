package com.wentianyang.androidpractice;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class UserActivity extends AppCompatActivity {

    public static void startActivity(Activity activity){
        Intent intent = new Intent(activity, UserActivity.class);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        getSupportFragmentManager().beginTransaction()
            .replace(R.id.content, DemoFragment.newInstance()).commit();
    }
}
