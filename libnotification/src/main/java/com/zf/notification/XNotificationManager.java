package com.zf.notification;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.ContextWrapper;
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
    /**默认的notifyId*/
    private int defaultNotifyId = 0;
    /**NotifyId 设置*/
    private static NotifyId notifyIds;
    /**channel 设置*/
    private static XNotificationChannel channelStatus = null;

    /**
     * 私有 XNotificationManager
     * @param context
     */
    private XNotificationManager(Context context) {
        super(context);
        if (channelStatus == null){
            throw new UnsupportedOperationException("u need initChannel");
        }
        createNotificationChannelBuild();
        notificationStyles = new NotificationStyles(this);
    }

    /**
     * 外部调用
     * @param context
     * @return
     */
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
     * init Channel
     * 建议在Application中处理
     * @param channelId
     * @param channelName
     */
    public static void initChannel(String channelId,String channelName){
        channelStatus = XNotificationChannel.createNotificationChannel(channelId,channelName);
    }

    /**
     * 控制日志开关,默认为开
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
    private void createNotificationChannelBuild() {
        android.app.NotificationChannel channel = new android.app.NotificationChannel(channelStatus.getChannelId(), channelStatus.getChannelName(), NotificationManager.IMPORTANCE_HIGH);
        channel.setLockscreenVisibility(Notification.VISIBILITY_PRIVATE);
        getManager().createNotificationChannel(channel);
    }

    /**
     * 设置通知栏信息--Android API 26（8.0及其以上）
     *
     * @param title
     * @param content
     * @param smallIcon
     * @param cancel
     * @return
     */
    @TargetApi(Build.VERSION_CODES.O)
    private Notification.Builder setChannelNotification(String title, String content, int smallIcon,boolean cancel) {
        return new Notification.Builder(getApplicationContext(), channelStatus.getChannelId())
                .setContentTitle(title)
                .setWhen(System.currentTimeMillis())
                .setContentText(content)
                .setSmallIcon(smallIcon)
                .setAutoCancel(cancel);
    }

    /**
     * 设置通知栏信息--Android API 25（8.0以下）
     *
     * @param title
     * @param content
     * @param smallIcon
     * @param cancel
     * @return
     */
    private NotificationCompat.Builder setNotification(String title, String content,int smallIcon,boolean cancel) {
        return new NotificationCompat.Builder(getApplicationContext(), channelStatus.getChannelId())
                .setContentTitle(title)
                .setWhen(System.currentTimeMillis())
                .setContentText(content)
                .setSmallIcon(smallIcon)
                .setAutoCancel(cancel);
    }

    /**
     * 默认发送通知
     *
     * @param title 标题
     * @param content 文本
     * @param smallIcon 小图标
     * @param cancel 点击后是否取消
     */
    public void sendNotification(String title, String content,int smallIcon,boolean cancel) {
        Logger.i(TAG,"sendNotification... ");
        if (Build.VERSION.SDK_INT >= currentHighVersionDefaultApi) {
            Notification notification = setChannelNotification(title, content,smallIcon,cancel).build();
            getManager().notify(defaultNotifyId, notification);
        } else {
            Notification notification = setNotification(title, content,smallIcon,cancel).build();
            getManager().notify(defaultNotifyId, notification);
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
     * @param cancel 点击后是否取消通知栏
     * @return
     */
    @RequiresApi(api = Build.VERSION_CODES.O)
    private Notification.Builder setCustomNotificationHighVersion(String title, String content, String intent, int smallIcon, int largeIcon,boolean cancel) {
        return new Notification.Builder(getApplicationContext(), channelStatus.getChannelId())
                .setWhen(System.currentTimeMillis())
                .setSmallIcon(smallIcon)
                .setContent(notificationStyles.setDefaultRemoteViews(title, content, intent, largeIcon))
                .setAutoCancel(cancel);

    }

    /**
     * 设置通知栏信息--Android API 25（8.0以下）
     *
     * @param title
     * @param content
     * @param intent
     * @param smallIcon
     * @param largeIcon
     * @param cancel 点击后是否取消通知栏
     * @return
     */
    private NotificationCompat.Builder setCustomNotification(String title, String content, String intent, int smallIcon, int largeIcon,boolean cancel) {
        return new NotificationCompat.Builder(getApplicationContext(), channelStatus.getChannelId())
                .setContentTitle(title)
                .setContentText(content)
                .setWhen(System.currentTimeMillis())
                .setSmallIcon(smallIcon)
                .setContent(notificationStyles.setDefaultRemoteViews(title, content, intent, largeIcon))
                .setAutoCancel(cancel);
    }

    /**
     * 发送自定义布局通知栏
     * @param title 标题
     * @param content 文本
     * @param notifyID 通知id
     * @param intent 按钮文字
     * @param largeIcon 大图标
     * @param cancel 点击后是否取消
     * @param contentIntent PendingIntent
     */
    public void sendCustomNotification(String title, String content, int notifyID, String intent, int largeIcon,int smallIcon,boolean cancel, PendingIntent contentIntent) {
        Logger.i(TAG, "sendCustomNotification ...");
        if (Build.VERSION.SDK_INT >= currentHighVersionDefaultApi) {
            Logger.i(TAG, "sendCustomNotification ...api>=26");
            Notification notification = setCustomNotificationHighVersion(title, content, intent, smallIcon, largeIcon,cancel).build();
            notification.contentIntent = contentIntent;
            getManager().notify(notifyID, notification);
        } else {
            Logger.i(TAG, "sendCustomNotification ...api<26");
            Notification notification = setCustomNotification(title, content, intent, smallIcon, largeIcon,cancel).build();
            notification.contentView = notificationStyles.setDefaultRemoteViews(title, content, intent, largeIcon);
            notification.contentIntent = contentIntent;
            getManager().notify(notifyID, notification);
        }

    }


}
