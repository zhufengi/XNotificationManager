package com.zf.notification;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.NotificationCompat;
import android.widget.RemoteViews;
import static android.content.Context.NOTIFICATION_SERVICE;

/**
 *
 * ┌───┐   ┌───┬───┬───┬───┐ ┌───┬───┬───┬───┐ ┌───┬───┬───┬───┐ ┌───┬───┬───┐
 * │Esc│   │ F1│ F2│ F3│ F4│ │ F5│ F6│ F7│ F8│ │ F9│F10│F11│F12│ │P/S│S L│P/B│  ┌┐    ┌┐    ┌┐
 * └───┘   └───┴───┴───┴───┘ └───┴───┴───┴───┘ └───┴───┴───┴───┘ └───┴───┴───┘  └┘    └┘    └┘
 * ┌───┬───┬───┬───┬───┬───┬───┬───┬───┬───┬───┬───┬───┬───────┐ ┌───┬───┬───┐ ┌───┬───┬───┬───┐
 * │~ `│! 1│@ 2│# 3│$ 4│% 5│^ 6│& 7│* 8│( 9│) 0│_ -│+ =│ BacSp │ │Ins│Hom│PUp│ │N L│ / │ * │ - │
 * ├───┴─┬─┴─┬─┴─┬─┴─┬─┴─┬─┴─┬─┴─┬─┴─┬─┴─┬─┴─┬─┴─┬─┴─┬─┴─┬─────┤ ├───┼───┼───┤ ├───┼───┼───┼───┤
 * │ Tab │ Q │ W │ E │ R │ T │ Y │ U │ I │ O │ P │{ [│} ]│ | \ │ │Del│End│PDn│ │ 7 │ 8 │ 9 │   │
 * ├─────┴┬──┴┬──┴┬──┴┬──┴┬──┴┬──┴┬──┴┬──┴┬──┴┬──┴┬──┴┬──┴─────┤ └───┴───┴───┘ ├───┼───┼───┤ + │
 * │ Caps │ A │ S │ D │ F │ G │ H │ J │ K │ L │: ;│" '│ Enter  │               │ 4 │ 5 │ 6 │   │
 * ├──────┴─┬─┴─┬─┴─┬─┴─┬─┴─┬─┴─┬─┴─┬─┴─┬─┴─┬─┴─┬─┴─┬─┴────────┤     ┌───┐     ├───┼───┼───┼───┤
 * │ Shift  │ Z │ X │ C │ V │ B │ N │ M │< ,│> .│? /│  Shift   │     │ ↑ │     │ 1 │ 2 │ 3 │   │
 * ├─────┬──┴─┬─┴──┬┴───┴───┴───┴───┴───┴──┬┴───┼───┴┬────┬────┤ ┌───┼───┼───┐ ├───┴───┼───┤ E││
 * │ Ctrl│    │Alt │         Space         │ Alt│    │    │Ctrl│ │ ← │ ↓ │ → │ │   0   │ . │←─┘│
 * └─────┴────┴────┴───────────────────────┴────┴────┴────┴────┘ └───┴───┴───┘ └───────┴───┴───┘
 *
 *
 * @author: zhufengi
 * @github: https://github.com/zhufengi
 * @time: 2018/12/30
 * @description: XNotificationBuildManager
 * 通知栏管理器 build模式
 *
 */
public class XNotificationBuildManager {

    private static String TAG = "HNotificationManager";
    private static NotificationManager manager = null;
    /**
     * 通知栏高版本区分
     */
    private static int currentHighVersionDefaultApi = Build.VERSION_CODES.O;
    /**默认的notifyId*/
    private static int defaultNotifyId = 0;
    /**NotifyId 设置*/
    private final NotifyId notifyId;
    /**channel 设置*/
    private final XNotificationChannel channelStatus;
    /*显示风格*/
    private final RemoteViews remoteViewStyles;
    private final Context mContext;
    /*标题*/
    private final String title;
    /*文本*/
    private final String content;
    /*按钮文字*/
    private final String intent;
    /*小图标*/
    private final int smallIcon;
    /*大图标*/
    private final int largeIcon;
    /*点击取消*/
    private final boolean autoCancel;


    /**
     *
     * @param builder
     */
    private XNotificationBuildManager(Builder builder) {
        this.notifyId = builder.notifyId;
        this.channelStatus = builder.channelStatus;
        this.remoteViewStyles = builder.remoteViewStyles;
        this.mContext = builder.mContext;
        this.title = builder.title;
        this.content = builder.content;
        this.intent = builder.intent;
        this.smallIcon = builder.smallIcon;
        this.largeIcon = builder.largeIcon;
        this.autoCancel = builder.autoCancel;

    }
     public static class Builder{
        private  Context mContext;
        private  XNotificationChannel channelStatus;
        private  RemoteViews remoteViewStyles;
        private  String title;
        private  String content;
        private  String intent;
        private  int smallIcon;
        private  int largeIcon;
        private  NotifyId notifyId;
        private  boolean autoCancel;


       public Builder mContext(Context mContext){
           this.mContext = mContext;
           return this;
       }
       public Builder setChannelStatus(XNotificationChannel channelStatus){
           this.channelStatus = channelStatus;
           return this;
       }
       public Builder setRemoteViewStyles(RemoteViews remoteViewStyles){
           this.remoteViewStyles = remoteViewStyles;
           return this;
       }
       public Builder setTitle(String title){
           this.title = title;
           return this;
       }
       public Builder setContent(String content){
           this.content = content;
           return this;
       }
       public Builder setIntent(String intent){
           this.intent = intent;
           return this;
       }
       public Builder setSmallIcon(int smallIcon){
           this.smallIcon = smallIcon;
           return this;
       }
       public Builder setLargeIcon(int largeIcon){
           this.largeIcon = largeIcon;
           return this;
       }
       public Builder setNotifyId(NotifyId notifyId){
           this.notifyId = notifyId;
           return this;
       }
       public Builder setAutoCancel(boolean autoCancel){
           this.autoCancel = autoCancel;
           return this;
       }
         private void initApplicationChannel(){
           if (channelStatus == null){

           }
         }

         /**
          * 获取系统通知栏服务
          *
          * @return
          */
         private NotificationManager getManager() {
             if (manager == null) {
                 manager = (NotificationManager) mContext.getSystemService(NOTIFICATION_SERVICE);
                 createNotificationChannelBuild();
             }
             return manager;
         }

         /**
          * 取消通知
          *
          * @param id
          */
         public void cancel(int id) {
             getManager().cancel(id);
         }

         /**
          * 取消全部通知
          */
         public void cancelAll() {
             getManager().cancelAll();
         }


         /**
          * 创建通知渠道 Android O(8.0)
          */
         @TargetApi(Build.VERSION_CODES.O)
         private void createNotificationChannelBuild() {
             android.app.NotificationChannel channel = new android.app.NotificationChannel(channelStatus.getChannelId(), channelStatus.getChannelName(), NotificationManager.IMPORTANCE_HIGH);
             channel.setLockscreenVisibility(Notification.VISIBILITY_PRIVATE);
             getManager().createNotificationChannel(channel);
         }

         /**
          * 配置通知栏普通样式信息(标题，文本)
          * Android API 26（8.0及其以上）
          *
          * @return
          */
         @TargetApi(Build.VERSION_CODES.O)
         private Notification.Builder setChannelNotification() {
             return new Notification.Builder(mContext, channelStatus.getChannelId())
                     .setWhen(System.currentTimeMillis())
                     .setContentTitle(title)
                     .setContentText(content)
                     .setSmallIcon(smallIcon)
                     .setAutoCancel(autoCancel);
         }

         /**
          * 配置通知栏普通样式信息(标题，文本)
          * Android API 25（8.0以下）
          *
          * @return
          */
         private NotificationCompat.Builder setNotification() {
             return new NotificationCompat.Builder(mContext, channelStatus.getChannelId())
                     .setWhen(System.currentTimeMillis())
                     .setContentTitle(title)
                     .setContentText(content)
                     .setSmallIcon(smallIcon)
                     .setAutoCancel(autoCancel);
         }




         /**
          * 配置自定义样式通知栏
          * Android API 26（8.0及其以上）
          *
          * @return
          */
         @RequiresApi(api = Build.VERSION_CODES.O)
         private Notification.Builder setCustomNotificationHighVersion() {
             if (remoteViewStyles == null){
                 Logger.i(TAG,"remoteViewStyles == null ");
                 this.remoteViewStyles = NotificationStyles.setDefaultRemoteViews(title, content, intent, largeIcon);
             }
             return new Notification.Builder(mContext, channelStatus.getChannelId())
                     .setWhen(System.currentTimeMillis())
                     .setSmallIcon(smallIcon)
                     .setCustomContentView(remoteViewStyles)
                     .setAutoCancel(autoCancel);

         }

         /**
          * 配置自定义样式通知栏
          * Android API 25（8.0以下）
          *
          * @return
          */
         private NotificationCompat.Builder setCustomNotification() {
             if (remoteViewStyles == null){
                 Logger.i(TAG,"remoteViewStyles == null ");
                 this.remoteViewStyles = NotificationStyles.setDefaultRemoteViews(title, content, intent, largeIcon);
             }
             return new NotificationCompat.Builder(mContext, channelStatus.getChannelId())
                     .setWhen(System.currentTimeMillis())
                     .setCustomContentView(remoteViewStyles)
                     .setSmallIcon(smallIcon)
                     .setAutoCancel(autoCancel);
         }

         /**
          * 默认发送通知
          *
          */
         @SuppressLint("NewApi")
         public void sendNotification(PendingIntent contentIntent) {
             Logger.i(TAG,"sendNotification... ");
             if (Build.VERSION.SDK_INT >= currentHighVersionDefaultApi) {
                 Notification notification = setChannelNotification().build();
                 notification.contentIntent = contentIntent;
                 getManager().notify(defaultNotifyId, notification);
             } else {
                 Notification notification = setNotification().build();
                 notification.contentIntent = contentIntent;
                 getManager().notify(defaultNotifyId, notification);
             }
         }

         /**
          * 发送自定义布局通知栏
          * @param contentIntent PendingIntent
          */
         @SuppressLint("NewApi")
         public void sendCustomNotification(PendingIntent contentIntent) {
             if (Build.VERSION.SDK_INT >= currentHighVersionDefaultApi) {
                 Logger.i(TAG, "sendCustomNotification ...api>=26");
                  Notification notification = setCustomNotificationHighVersion().build();
                 notification.contentIntent = contentIntent;
                 getManager().notify(2, notification);
             } else {
                 Logger.i(TAG, "sendCustomNotification ...api<26");
                 Notification notification = setCustomNotification().build();
                 notification.contentIntent = contentIntent;
                 getManager().notify(2, notification);
             }

         }

    }


}
