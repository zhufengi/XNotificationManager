package com.zf.notification;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        XNotificationManager manager = new XNotificationManager(this);
        manager.setLogger(true);
        manager.sendNotification("自定义通知栏","我是通知栏");
    }
}
