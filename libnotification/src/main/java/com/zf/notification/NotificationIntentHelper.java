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
    public PendingIntent onPendingIntentAction(String action, Context context,int requestCode, Class<?> cls) {
        Intent intent = new Intent(action);
        return onPendingIntent(context, requestCode, intent, PendingIntent.FLAG_CANCEL_CURRENT);
    }

    /**
     *
     * @param context
     * @param requestCode
     * @param intent
     * @param flags
     * @return
     */
    public PendingIntent onPendingIntent(Context context,int requestCode,Intent intent,int flags){
        return PendingIntent.getActivity(context, requestCode, intent, flags);
    }
}
