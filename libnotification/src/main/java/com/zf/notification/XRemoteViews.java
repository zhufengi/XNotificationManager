package com.zf.notification;

import android.annotation.SuppressLint;
import android.os.Parcel;
import android.widget.RemoteViews;

public class XRemoteViews extends RemoteViews {

    public XRemoteViews(String packageName, int layoutId) {
        super(packageName, layoutId);
    }


    @SuppressLint("NewApi")
    public XRemoteViews(RemoteViews src) {
        super(src);
    }

}
