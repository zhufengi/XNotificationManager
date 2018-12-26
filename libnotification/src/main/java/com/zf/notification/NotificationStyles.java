package com.zf.notification;

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

    private Context mContext;

    public NotificationStyles(Context mContext){
        this.mContext = mContext;
    }


    /**
     * 自定义通知栏布局
     *
     * @param title     标题
     * @param content   内容
     * @param intent    按钮内容
     * @param largeIcon 图标
     * @return
     */
    public RemoteViews setCustomRemoteViews(String title, String content, String intent, int largeIcon) {
        //自定义布局
        RemoteViews remoteView = new RemoteViews(mContext.getPackageName(), R.layout.notification_layout);
        /**大图标*/
        remoteView.setImageViewResource(R.id.imgNotifition, largeIcon);
        /**标题*/
        remoteView.setTextViewText(R.id.text1, title);
        /**内容*/
        remoteView.setTextViewText(R.id.text2, content);
        /**设置等按钮*/
        remoteView.setViewVisibility(R.id.flButton, View.GONE);
        if (intent != null) {
            remoteView.setViewVisibility(R.id.flButton, View.VISIBLE);
            remoteView.setImageViewResource(R.id.imgIntent, R.drawable.btn_notification);
            remoteView.setTextViewText(R.id.tvIntent, intent);
        }
        return remoteView;
    }
}
