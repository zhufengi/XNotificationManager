package com.zf.notification.test;

import com.zf.notification.XNotificationChannel;
import com.zf.notification.XNotification;

public class Application extends android.app.Application {
    @Override
    public void onCreate() {
        super.onCreate();
        /*初始化*/
        XNotification.init(this);
        /*调试日志*/
        XNotification.setLogger(true);
        /*创建全局的channel*/
        XNotificationChannel.createNotificationChannel("test","testname");
//        XNotificationManager.initChannel("test_id2","test_channel2");
    }
}
