package com.zf.notification;

import android.util.Log;

/**
 * @author: zhufengi
 * github : https://github.com/zhufengi
 * date   : 2018/12/23
 * description : 日志控制类
 */
public class Logger {


    /**日志开关*/
    private static boolean logSwitch = true;

    /**
     * 初始化日志：true 打开日志，false 关闭日志
     * @param b
     */
    public static void init(boolean b){
        logSwitch = b;
    }

    /**
     *
     * @param tag
     * @param log
     */
    public static void d(String tag,String log){
        if (logSwitch){
            Log.d(tag,log);
        }
    }

    /**
     *
     * @param tag
     * @param log
     */
    public static void i(String tag,String log){
        if (logSwitch){
            Log.d(tag,log);
        }
    }

    /**
     *
     * @param tag
     * @param log
     */
    public static void w(String tag,String log){
        if (logSwitch){
            Log.d(tag,log);
        }
    }

    /**
     *
     * @param tag
     * @param log
     */
    public static void e(String tag,String log){
        if (logSwitch){
            Log.d(tag,log);
        }
    }
}
