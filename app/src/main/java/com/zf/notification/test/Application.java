package com.zf.notification.test;

import com.zf.notification.NotificationChannel;
import com.zf.notification.XNotificationManager;

public class Application extends android.app.Application {
    @Override
    public void onCreate() {
        super.onCreate();
        XNotificationManager.initChannel("test_id2","test_channel2");
    }
}
