package com.zf.notification;

import java.io.Serializable;

/**
 * @author: zhufengi
 * github : https://github.com/zhufengi
 * date   : 2018/12/25
 * description : channel类
 * 可做全局适配和局部适配
 */
public class XNotificationChannel implements Serializable {

    private static String channelId;
    private static String channelName;

    private XNotificationChannel(String channelId, String channelName){
        this.channelId = channelId;
        this.channelName = channelName;
    }

    /**
     * 创建notification channel
     * @param channelId
     * @param channelName
     */
    public static XNotificationChannel createNotificationChannel(String channelId, String channelName){
        return new XNotificationChannel(channelId,channelName);
    }

    /**
     * 获取channel id
     * @return
     */
    public  static String getChannelId() {
        return channelId;
    }

    /**
     * 获取channel name
     * @return
     */
    public static String getChannelName() {
        return channelName;
    }

    @Override
    public String toString() {
        return "XNotificationChannel{" +
                "channelId='" + channelId + '\'' +
                ", channelName='" + channelName + '\'' +
                '}';
    }
}
