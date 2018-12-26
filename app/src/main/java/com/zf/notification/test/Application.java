package com.zf.notification.test;

import com.zf.notification.XNotificationManager;

public class Application extends android.app.Application {
    @Override
    public void onCreate() {
        super.onCreate();
        XNotificationManager.initChannel("test_id","test_channel");
    }
}
