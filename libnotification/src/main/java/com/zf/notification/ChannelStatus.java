package com.zf.notification;

import java.io.Serializable;

/**
 * @author: zhufengi
 * github : https://github.com/zhufengi
 * date   : 2018/12/25
 * description : channel类
 */
public class ChannelStatus implements Serializable {

    public String channelId;
    public String channelName;

    @Override
    public String toString() {
        return "ChannelStatus{" +
                "channelId='" + channelId + '\'' +
                ", channelName='" + channelName + '\'' +
                '}';
    }
}
