package com.zf.notification;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.NotificationCompat;



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
 * @time: 2018/12/23
 * @description: XNotificationManager
 * 通知栏管理器
 *
 */
public class XNotificationManager extends ContextWrapper {

    private String TAG = "HNotificationManager";
    private NotificationManager manager = null;
    private static XNotificationManager instance;
    private NotificationStyles notificationStyles = null;

    /**
     * 通知栏高版本区分
     */
    private final int currentHighVersionDefaultApi = Build.VERSION_CODES.O;
    /**
     * 默认channel id
     */
    private String channelID = "channel_1";
    /**
     * 默认名字
     */
    private String channelName = "channel_hemihua_1";
    /**
     * 更新默认id
     */
    public int notifyID = 1;
    /**更新自定义id*/
    public int notifyCustomID = 2;
    /**channel 设置*/
    private ChannelStatus channelStatus;

    /**
     *
     * @param context
     */
    public XNotificationManager(Context context) {
        super(context);
        channelStatus = new ChannelStatus();
        notificationStyles = new NotificationStyles(this);
    }

    public static XNotificationManager getInstance (Context context){
        if (instance == null){
            synchronized (XNotificationManager.class){
                if (instance == null){
                    instance = new XNotificationManager(context);
                }
            }
        }
        return instance;
    }

    /**
     * 控制日志开关,默认为关
     * @param b
     */
    public void setLogger(boolean b){
        Logger.init(b);
    }

    /**
     * 获取系统通知栏服务
     *
     * @return
     */
    private NotificationManager getManager() {
        if (manager == null) {
            manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
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
    public void createNotificationChannel() {
        NotificationChannel channel = new NotificationChannel(channelID, channelName, NotificationManager.IMPORTANCE_HIGH);
        channel.setLockscreenVisibility(Notification.VISIBILITY_PRIVATE);
        getManager().createNotificationChannel(channel);
    }

    /**
     * 设置通知栏信息--Android API 26（8.0及其以上）
     *
     * @param title
     * @param content
     * @return
     */
    @TargetApi(Build.VERSION_CODES.O)
    private Notification.Builder setChannelNotification(String title, String content) {
        return new Notification.Builder(getApplicationContext(), channelID)
                .setContentTitle(title)
                .setContentText(content)
                .setSmallIcon(android.R.drawable.stat_notify_more)
                .setAutoCancel(true);
    }

    @TargetApi(Build.VERSION_CODES.O)
    public Notification.Builder setChannelNotificationNoIcon(String title, String content) {
        createNotificationChannel();
        return new Notification.Builder(getApplicationContext(), channelID)
                .setContentTitle(title)
                .setContentText(content)
                .setAutoCancel(true);
    }

    /**
     * 设置通知栏信息--Android API 25（8.0以下）
     *
     * @param title
     * @param content
     * @return
     */
    private NotificationCompat.Builder setNotification(String title, String content) {
        return new NotificationCompat.Builder(getApplicationContext(), channelID)
                .setContentTitle(title)
                .setContentText(content)
                .setSmallIcon(android.R.drawable.stat_notify_more)
                .setAutoCancel(true);
    }

    /**
     * 默认发送通知
     *
     * @param title
     * @param content
     */
    public void sendNotification(String title, String content) {
        Logger.i(TAG,"11111111");
        cancelAll();
        if (Build.VERSION.SDK_INT >= currentHighVersionDefaultApi) {
            createNotificationChannel();
            Notification notification = setChannelNotification(title, content).build();
            getManager().notify(notifyID, notification);
        } else {
            Notification notification = setNotification(title, content).build();
            getManager().notify(notifyID, notification);
        }
    }

    /**
     * 默认发送通知
     *
     * @param title
     * @param content
     * @param icon
     */
    public void sendNotification(String title, String content, int icon) {
        cancelAll();
        if (Build.VERSION.SDK_INT >= currentHighVersionDefaultApi) {
            createNotificationChannel();
            Notification notification = setCustomNotificationHighVersion(title, content, null, android.R.drawable.sym_def_app_icon, icon).build();
            getManager().notify(notifyID, notification);
        } else {
            Notification notification = setCustomNotification(title, content, null, android.R.drawable.sym_def_app_icon, icon).build();
            getManager().notify(notifyID, notification);
        }
    }

    /**
     * 自定义样式通知栏--Android API 26（8.0及其以上）
     *
     * @param title     标题
     * @param content   内容
     * @param intent    按钮文本
     * @param smallIcon 小图标
     * @param largeIcon 大图标
     * @return
     */
    @RequiresApi(api = Build.VERSION_CODES.O)
    private Notification.Builder setCustomNotificationHighVersion(String title, String content, String intent, int smallIcon, int largeIcon) {
        return new Notification.Builder(getApplicationContext(), channelID)
                .setWhen(System.currentTimeMillis())
                .setSmallIcon(android.R.drawable.sym_def_app_icon)
                .setContent(notificationStyles.setCustomRemoteViews(title, content, intent, largeIcon))
                .setAutoCancel(true);

    }

    /**
     * 设置通知栏信息--Android API 25（8.0以下）
     *
     * @param title
     * @param content
     * @param intent
     * @param smallIcon
     * @param largeIcon
     * @return
     */
    private NotificationCompat.Builder setCustomNotification(String title, String content, String intent, int smallIcon, int largeIcon) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext(), channelID);
        builder.setContentTitle(title)
                .setContentText(content)
                .setWhen(System.currentTimeMillis())
                .setSmallIcon(smallIcon)
//                .setLargeIcon(BitmapFactory.decodeResource(getApplicationContext().getResources(), largeIcon))
                .setContent(notificationStyles.setCustomRemoteViews(title, content, intent, largeIcon))
                .setAutoCancel(true);
        return builder;
    }

    /**
     * @param title
     * @param content
     * @param intent
     * @param largeIcon
     * @param contentIntent
     */
    public void sendCustomNotification(String title, String content, String intent, int largeIcon, PendingIntent contentIntent) {
        cancelAll();
        Logger.i(TAG, "sendCustomNotification ...");
        if (Build.VERSION.SDK_INT >= currentHighVersionDefaultApi) {
            Logger.i(TAG, "sendCustomNotification ...api>=26");
            createNotificationChannel();
            Notification notification = setCustomNotificationHighVersion(title, content, intent, android.R.drawable.sym_def_app_icon, largeIcon).build();
            notification.contentIntent = contentIntent;
            getManager().notify(notifyCustomID, notification);
        } else {
            Logger.i(TAG, "sendCustomNotification ...api<26");
            Notification notification = setCustomNotification(title, content, intent, android.R.drawable.sym_def_app_icon, largeIcon).build();
            notification.contentView = notificationStyles.setCustomRemoteViews(title, content, intent, largeIcon);
            notification.contentIntent = contentIntent;
            getManager().notify(notifyCustomID, notification);
        }

    }

    /**
     *
     * @param title
     * @param content
     * @param notifyID
     * @param intent
     * @param largeIcon
     * @param contentIntent
     */
    public void sendCustomNotification(String title, String content, int notifyID, String intent, int largeIcon, PendingIntent contentIntent) {
        cancelAll();
        Logger.i(TAG, "sendCustomNotification ...");
        if (Build.VERSION.SDK_INT >= currentHighVersionDefaultApi) {
            Logger.i(TAG, "sendCustomNotification ...api>=26");
            createNotificationChannel();
            Notification notification = setCustomNotificationHighVersion(title, content, intent, android.R.drawable.sym_def_app_icon, largeIcon).build();
            notification.contentIntent = contentIntent;
            getManager().notify(notifyID, notification);
        } else {
            Logger.i(TAG, "sendCustomNotification ...api<26");
            Notification notification = setCustomNotification(title, content, intent, android.R.drawable.sym_def_app_icon, largeIcon).build();
            notification.contentView = notificationStyles.setCustomRemoteViews(title, content, intent, largeIcon);
            notification.contentIntent = contentIntent;
            getManager().notify(notifyID, notification);
        }

    }



    /**
     * notification intent
     * @param context
     * @param cls
     * @param requestCode
     */
    public PendingIntent onPendingIntent(Context context, Class<?> cls, int requestCode) {
        Intent intent = new Intent(context, cls);
        return PendingIntent.getActivity(context, requestCode, intent, PendingIntent.FLAG_CANCEL_CURRENT);
    }

    /**
     * notification intent
     * @param action
     * @param context
     * @param cls
     * @param requestCode
     */
    public PendingIntent onPendingIntent(String action, Context context, Class<?> cls, int requestCode) {
        Intent intent = new Intent(action);
        return PendingIntent.getActivity(context, requestCode, intent, PendingIntent.FLAG_CANCEL_CURRENT);
    }
}
