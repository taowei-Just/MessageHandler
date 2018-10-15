package com.tao.messagehandler.message;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Message;
import android.util.Log;

/**
 * Created by Tao on 2018/8/22 0022.
 */

public abstract class MessageReceiver extends BroadcastReceiver {

    public static final String ACTION_MESSAGE_WHAT = "ACTION_MESSAGE_WHAT";
    public static final String ACTION_MESSAGE_WHAT_DELY = "ACTION_MESSAGE_WHAT_DELY";
    public static final String ACTION_MESSAGE = "ACTION_MESSAGE";
    public static final String ACTION_MEESSAGE_DELY = "ACTION_MEESSAGE_DELY";
    public static final String ACTION_UNREGISTER = "ACTION_UNREGISTER";

    public static final String message_what = "message_what";
    public static final String message_msg = "message_msg";
    public static String message_tag = "message_tag";
    private boolean receiveTag = true;
    Context context;
    String TAG = getClass().getSimpleName();
    boolean isMatch = false;
    int thisTag;

    public MessageReceiver() {
    }

    public MessageReceiver(Context context, int tag) throws Exception {
        this.thisTag = tag;
        this.isMatch = true;
        this.context = context;
        IntentFilter filter = new IntentFilter();
        filter.addAction(ACTION_UNREGISTER);
        filter.addAction(ACTION_MESSAGE_WHAT);
        filter.addAction(ACTION_MESSAGE);
        filter.addAction(ACTION_MEESSAGE_DELY);
        filter.addAction(ACTION_MESSAGE_WHAT_DELY);
        MassengerControlCenter.registerReceiver(context, this, filter, thisTag);

    }

    public MessageReceiver(Context context) {
        this.context = context;
        IntentFilter filter = new IntentFilter();
        filter.addAction(ACTION_UNREGISTER);
        filter.addAction(ACTION_MESSAGE_WHAT);
        filter.addAction(ACTION_MESSAGE);
        filter.addAction(ACTION_MEESSAGE_DELY);
        filter.addAction(ACTION_MESSAGE_WHAT_DELY);
        MassengerControlCenter.registerReceiver(context, this, filter);
    }

    public MessageReceiver(Context context, boolean receiveTag) {
        this.context = context;
        this.receiveTag = receiveTag;
        IntentFilter filter = new IntentFilter();
        filter.addAction(ACTION_UNREGISTER);
        filter.addAction(ACTION_MESSAGE_WHAT);
        filter.addAction(ACTION_MESSAGE);
        filter.addAction(ACTION_MEESSAGE_DELY);
        filter.addAction(ACTION_MESSAGE_WHAT_DELY);
        MassengerControlCenter.registerReceiver(context, this, filter);
    }

    public void onReceive(Context context, Intent intent) {

        String action = intent.getAction();
        int what = intent.getIntExtra(MessageReceiver.message_what, -1);
//        Bundle message_msg = intent.getBundleExtra("message_msg");
        Bundle msg = intent.getExtras();
        Message message = new Message();
        message.what = what;
        message.setData(msg);

        switch (action) {
            case ACTION_MESSAGE_WHAT:
                Log.e(TAG, "ACTION_MESSAGE_WHAT：" + what);
                match(message);
                break;
            case ACTION_MESSAGE_WHAT_DELY:
                Log.e(TAG, "ACTION_MESSAGE_WHAT_DELY：" + what);
                match(message);
                break;
            case ACTION_MESSAGE:
                Log.e(TAG, "ACTION_MESSAGE：" + what);
                match(message);
                break;
            case ACTION_MEESSAGE_DELY:
                Log.e(TAG, "ACTION_MEESSAGE_DELY：" + what);
                match(message);
                break;
            case ACTION_UNREGISTER:
                Log.e(TAG, "UNREGISTER：" + what);
                if (    match(message))
                    try {
                        release(context);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                break;
        }
    }

    private boolean match(Message message) {
        if (message == null)
            return false;
        int finalTag = message.getData().getInt(MessageReceiver.message_tag);
        Log.e(TAG, "mach tag:" + finalTag);
        if ((!isMatch && receiveTag) || receiveTag ? MassengerControlCenter.mather(finalTag, this) : finalTag < 0) {
            receiverMessage(message);
            return true;
        }else {
            if (finalTag == 0 )
                return true;
        }
        return false;
    }

    public void release(Context context) throws Exception {
        MassengerControlCenter.release(context, this);

    }

    abstract public void receiverMessage(Message message);


}
