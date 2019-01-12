package com.zf.notification;

import android.content.Context;

/**
 * @author: zhufengi
 * github : https://github.com/zhufengi
 * date   : 2019/1/12
 * description : 通知栏框架初始化类
 * 1、全局初始化
 * 2、控制输出日志
 */
public class XNotification {

    public static Context context = null;
    /**
     * XNotificationManager 初始化
     */
    public static void init(Context mContext){
        if (mContext == null){
            throw new UnsupportedOperationException("XNotification context can not be null");
        }
        context = mContext;
    }


    /**
     * 控制日志开关,默认为开
     * @param b
     */
    public static void setLogger(boolean b){
        Logger.init(b);
    }

}
