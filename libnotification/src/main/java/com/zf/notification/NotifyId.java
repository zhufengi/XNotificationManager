package com.zf.notification;
/**
 * @author: zhufengi
 * github : https://github.com/zhufengi
 * date   : 2018/12/25
 * description :通知ids
 */
public class NotifyId {

    public int notifyId = 0;
    public NotifyId(int notifyId){
        this.notifyId = notifyId;
    }

    @Override
    public String toString() {
        return "NotifyId{" +
                "notifyId=" + notifyId +
                '}';
    }
}
