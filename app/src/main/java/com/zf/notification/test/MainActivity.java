package com.zf.notification.test;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RemoteViews;

import com.zf.notification.NotificationStyles;
import com.zf.notification.NotifyId;
import com.zf.notification.XNotification;
import com.zf.notification.XNotificationChannel;
import com.zf.notification.NotificationIntentHelper;
import com.zf.notification.XNotificationBuildManager;
import com.zf.notification.XNotificationManager;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        onXNotificationManager();
        onXNotificationBuildManager();
    }

    private void onXNotificationManager(){
        XNotificationManager.getInstance(this).setLogger(true);
        XNotificationManager.getInstance(this).sendNotification("自定义通知栏","我是通知栏",R.mipmap.ic_launcher,false);

        XNotificationManager.getInstance(this).sendCustomNotification("通知","我是通知栏",2,"点我",0,R.mipmap.ic_launcher,
              false,      NotificationIntentHelper.onPendingIntentClass(this, 0, MainActivity.class));
    }

    private void onXNotificationBuildManager(){
        XNotificationBuildManager.Builder builder  =new XNotificationBuildManager.Builder();
//        builder.mContext(this)
//                .setTitle("你啊哈")
//                .setContent("adsjklks")
//                .setSmallIcon(R.mipmap.ic_launcher)
//                .setNotifyId(5)
//                .sendNotification(NotificationIntentHelper.onPendingIntentClass(this,2,MainActivity.class));
        RemoteViews build = NotificationStyles.setCustomRemoteViews(remoteViews("build", "1234567890"), "build....", "1234567890", "", 0);
        builder.mContext(this)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setNotifyId(7)
                .setRemoteViewStyles(build)
                .sendCustomNotification(NotificationIntentHelper.onPendingIntentClass(this,2,MainActivity.class));

    }

    public  RemoteViews remoteViews(String title, String content) {
        //自定义布局
        RemoteViews remoteView = new RemoteViews(XNotification.context.getPackageName(), R.layout.item_notification_layout);
        /**标题*/
        remoteView.setTextViewText(R.id.text1, title);
        /**内容*/
        remoteView.setTextViewText(R.id.text2, content);
        /**设置等按钮*/
        return remoteView;
    }

}
