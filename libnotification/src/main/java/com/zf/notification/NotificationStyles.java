package com.zf.notification;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.View;
import android.widget.RemoteViews;

/**
 * @author: zhufengi
 * github : https://github.com/zhufengi
 * date   : 2018/12/25
 * description : 通知栏样式
 */
public class NotificationStyles {

    private static Context mContext;

    public NotificationStyles(Context mContext){
        this.mContext = mContext;
    }

    /**
     * 带图标自定义样式布局
     *
     * @param title     标题
     * @param content   内容
     * @param intent    按钮内容
     * @param largeIcon 图标
     * @return
     */
    @SuppressLint("NewApi")
    public static RemoteViews setDefaultRemoteViews(String title, String content, String intent, int largeIcon) {
        //自定义布局
        RemoteViews remoteView = new RemoteViews(XNotification.context.getPackageName(), R.layout.notification_layout);
        //判断传递值为空情况
        if (title == null || "".equals(title)){
            remoteView.setViewVisibility(R.id.text1,View.GONE);
            remoteView.setTextColor(R.id.text1,XNotification.context.getColor(R.color.black));
        }
        if (content == null || "".equals(content)){
            remoteView.setViewVisibility(R.id.text2,View.GONE);
            remoteView.setTextColor(R.id.text2,XNotification.context.getColor(R.color.black));
        }
        if (intent == null || "".equals(intent)) {
            remoteView.setViewVisibility(R.id.tvIntent, View.GONE);
        }
        /**大图标*/
        remoteView.setImageViewResource(R.id.imgNotifition, largeIcon);
        /**标题*/
        remoteView.setTextViewText(R.id.text1, title);
        /**内容*/
        remoteView.setTextViewText(R.id.text2, content);
        /**设置等按钮*/
        remoteView.setTextViewText(R.id.tvIntent, intent);
        return remoteView;
    }

    /**
     * 自定义setCustomRemoteViews
     * @param customRemoteViews
     * @return
     */
    public RemoteViews setCustomRemoteViews(RemoteViews customRemoteViews) {
        if (customRemoteViews == null){
            throw new UnsupportedOperationException("customRemoteViews can not null");
        }
        return customRemoteViews;
    }
}
