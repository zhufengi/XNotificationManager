package com.zf.notification.test;

import android.app.PendingIntent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.zf.notification.NotificationIntentHelper;
import com.zf.notification.XNotificationManager;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        XNotificationManager.getInstance(this).setLogger(true);
        XNotificationManager.getInstance(this).sendNotification("自定义通知栏","我是通知栏");
        XNotificationManager.getInstance(this).setCurrentNotifyId(1);

        XNotificationManager.getInstance(this).sendCustomNotification("通知","我是通知栏","点我",0,
          NotificationIntentHelper.onPendingIntentClass(this, 0, MainActivity.class));
    }
}
