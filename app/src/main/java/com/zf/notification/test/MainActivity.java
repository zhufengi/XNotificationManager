package com.zf.notification.test;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.zf.notification.NotifyId;
import com.zf.notification.XNotificationChannel;
import com.zf.notification.NotificationIntentHelper;
import com.zf.notification.XNotificationBuildManager;
import com.zf.notification.XNotificationManager;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        onXNotificationBuildManager();
        onXNotificationManager();
    }

    private void onXNotificationManager(){
        XNotificationManager.getInstance(this).setLogger(true);
        XNotificationManager.getInstance(this).sendNotification("自定义通知栏","我是通知栏",R.mipmap.ic_launcher);
        XNotificationManager.getInstance(this).setCurrentNotifyId(NotifyId.DEFAULT_ID3);

        XNotificationManager.getInstance(this).sendCustomNotification("通知","我是通知栏","点我",0,R.mipmap.ic_launcher,
                NotificationIntentHelper.onPendingIntentClass(this, 0, MainActivity.class));
    }

    private void onXNotificationBuildManager(){
        XNotificationBuildManager.Builder builder  =new XNotificationBuildManager.Builder();
        builder.mContext(this)
//                .setChannelStatus(XNotificationChannel.createNotificationChannel("abc","abcd"))
                .setTitle("你啊哈")
                .setContent("adsjklks")
                .setSmallIcon(R.mipmap.ic_launcher)
                .sendNotification(NotificationIntentHelper.onPendingIntentClass(this,2,MainActivity.class));

        XNotificationBuildManager.Builder builder2  =new XNotificationBuildManager.Builder();
        builder2.mContext(this)
                .setChannelStatus(XNotificationChannel.createNotificationChannel("11","22"))
                .setTitle("第二排")
                .setContent("aha")
                .setIntent("12")
                .setLargeIcon(R.mipmap.ic_launcher)
                .setSmallIcon(R.mipmap.ic_launcher)
//                .setRemoteViewStyles()
                .sendCustomNotification(NotificationIntentHelper.onPendingIntentClass(MainActivity.this,0,MainActivity.class));
    }
}
