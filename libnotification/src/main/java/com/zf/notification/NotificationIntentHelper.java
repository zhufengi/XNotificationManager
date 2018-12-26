package com.zf.notification;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

/**
 * @author: zhufengi
 * github : https://github.com/zhufengi
 * date   : 2018/12/25
 * description :通知栏跳转帮助类
 */
public class NotificationIntentHelper {

    private static Intent intent = null;

    private NotificationIntentHelper(){
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    /**
     * notification intent class
     * @param context
     * @param cls
     * @param requestCode
     */
    public static PendingIntent onPendingIntentClass(Context context,int requestCode,Class<?> cls) {
        intent = new Intent(context, cls);
        return onPendingIntent(context, requestCode, intent, PendingIntent.FLAG_CANCEL_CURRENT);
    }

    /**
     * notification intent action
     * @param context
     * @param requestCode
     * @param action
     */
    public static PendingIntent onPendingIntentAction(Context context,int requestCode,String action) {
        intent = new Intent(action);
        return onPendingIntent(context, requestCode, intent, PendingIntent.FLAG_CANCEL_CURRENT);
    }

    /**
     * notification intent
     * @param context
     * @param requestCode
     * @param intent
     * @param flags
     * @return
     */
    public static PendingIntent onPendingIntent(Context context,int requestCode,Intent intent,int flags){
        return PendingIntent.getActivity(context, requestCode, intent, flags);
    }
}
