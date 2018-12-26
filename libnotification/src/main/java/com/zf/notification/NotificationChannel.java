package com.zf.notification;

import java.io.Serializable;

/**
 * @author: zhufengi
 * github : https://github.com/zhufengi
 * date   : 2018/12/25
 * description : channel类
 */
public class NotificationChannel implements Serializable {

    private String channelId;
    private String channelName;

    private NotificationChannel(String channelId,String channelName){
        this.channelId = channelId;
        this.channelName = channelName;
    }

    /**
     * 创建notification channel
     * @param channelId
     * @param channelName
     */
    public static NotificationChannel createNotificationChannel(String channelId,String channelName){
        return new NotificationChannel(channelId,channelName);
    }

    public String getChannelId() {
        return channelId;
    }

    public String getChannelName() {
        return channelName;
    }

    @Override
    public String toString() {
        return "NotificationChannel{" +
                "channelId='" + channelId + '\'' +
                ", channelName='" + channelName + '\'' +
                '}';
    }
}
