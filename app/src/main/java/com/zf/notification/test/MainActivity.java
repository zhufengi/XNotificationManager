package com.zf.notification.test;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.zf.notification.XNotificationManager;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        XNotificationManager.getInstance(this).sendNotification("自定义通知栏","我是通知栏");
    }
}
