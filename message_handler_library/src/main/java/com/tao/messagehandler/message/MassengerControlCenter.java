package com.tao.messagehandler.message;

import android.content.Context;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Tao on 2018/10/13.
 */

public class MassengerControlCenter {

    public static HashMap<Integer, List<MessageReceiver>> msgerMap = new HashMap<>();

    public static void register(int tag, MessageReceiver msger) {
        if (msger == null)
            return;
        if (msgerMap.containsKey(tag) && msgerMap.get(tag) != null) {
            if (!msgerMap.get(tag).contains(msger))
                msgerMap.get(tag).add(msger);
        } else {
            ArrayList<MessageReceiver> value = new ArrayList<>();
            value.add(msger);
            msgerMap.put(tag, value);
        }
    }

    public static void unRegister(int tag) {
        msgerMap.remove(tag);
    }

    public static void unRegister(MessageReceiver msger) {
        if (msger == null)
            return;
        if (msgerMap.containsValue(msger)) {
            Iterator<Integer> iterator = msgerMap.keySet().iterator();
            while (iterator.hasNext()) {
                Integer next = iterator.next();
                List<MessageReceiver> messageReceivers = msgerMap.get(next);
                if (messageReceivers.contains(msger)) {
                    messageReceivers.remove(msger);
                    if (messageReceivers.size() <= 0)
                        msgerMap.remove(next);
                    return;
                }
            }
        }
    }

    public static boolean mather(int tag, MessageReceiver msger) {
        if (msgerMap.containsKey(tag)) {
            if (msgerMap.get(tag).contains(msger)) {
                return true;
            }
        }
        return false;
    }

    public static void registerReceiver(Context context, MessageReceiver receiver, IntentFilter filter, int tag) throws Exception {
        if (tag <= 0)
            throw new Exception(" tag must be >0");
        LocalBroadcastManager.getInstance(context).registerReceiver(receiver, filter);
        register(tag, receiver);
    }


    public static void registerReceiver(Context context, MessageReceiver receiver, IntentFilter filter) {
        LocalBroadcastManager.getInstance(context).registerReceiver(receiver, filter);
    }

    public static void release(Context context, MessageReceiver messageReceiver) {
        unRegister(messageReceiver);
        LocalBroadcastManager.getInstance(context).unregisterReceiver(messageReceiver);
    }
}
