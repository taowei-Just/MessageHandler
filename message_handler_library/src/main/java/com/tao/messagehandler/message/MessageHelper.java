package com.tao.messagehandler.message;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.content.LocalBroadcastManager;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by Tao on 2018/8/22 0022.
 */

public class MessageHelper implements MessageInterFace {

    private LocalBroadcastManager localBroad;
    private Handler handler;
    Context context;
    private long defaultTime = 50;
    boolean waittingDely = false;
    static MessageHelper messageHelper;

    public static MessageHelper getinstatnce(Context context) {
        if (messageHelper == null)
            messageHelper = new MessageHelper(context);
        return messageHelper;
    }


    private MessageHelper(Context context) {
        this.context = context;
        localBroad = LocalBroadcastManager.getInstance(context);
        handler = new Handler(context.getMainLooper());
    }


    public void releaseAll() {
        releaseBuyTag(-1);
    }

    @Override
    public void sendMessageWhat(int what) {
        sendMessageWhatBuyTag(what, -1);
    }

    @Override
    public void sendMessageWhatDely(int what, long time) {

        sendMessageWhatDelyBuyTag(what, time,-1);
    }

    @Override
    public void sendMessage(Message message) throws Exception {

        sendMessageBuyTag(message, -1);
        
        
    }

    @Override
    public void sendMessageDely(Message message, long time) {
        sendMessageDelyBuyTag(message, time,-1);
    }

    @Override
    public void sendMessageWhatBuyTag(int what, int tag) {
        final Intent intent = new Intent();
        intent.setAction(MessageReceiver.ACTION_MESSAGE_WHAT);
        intent.putExtra(MessageReceiver.message_what, what);
        Bundle bundle = new Bundle();
        bundle.putInt(MessageReceiver.message_tag, tag);
        intent.putExtras(bundle);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                localBroad.sendBroadcast(intent);
            }
        }, defaultTime);
    }

    @Override
    public void sendMessageWhatDelyBuyTag(int what, long time, int tag) {
        final Intent intent = new Intent();
        intent.setAction(MessageReceiver.ACTION_MESSAGE_WHAT_DELY);
        Bundle bundle = new Bundle();
        bundle.putInt(MessageReceiver.message_tag, tag);
        intent.putExtras(bundle);
        intent.putExtra(MessageReceiver.message_what, what);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                localBroad.sendBroadcast(intent);
            }
        }, time);
    }

    @Override
    public void sendMessageBuyTag(Message message, int tag) throws Exception {

        final Intent intent = new Intent();
        intent.setAction(MessageReceiver.ACTION_MESSAGE);
        intent.putExtra(MessageReceiver.message_what, message.what);
        message.getData().putInt(MessageReceiver.message_tag, tag);
        intent.putExtras(message.getData());
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                localBroad.sendBroadcast(intent);
            }
        }, defaultTime);
    }

    @Override
    public void sendMessageDelyBuyTag(Message message, long time, int tag) {
        final Intent intent = new Intent();
        intent.setAction(MessageReceiver.ACTION_MEESSAGE_DELY);
        intent.putExtra(MessageReceiver.message_what, message.what);
        message.getData().putInt(MessageReceiver.message_tag, tag);
        intent.putExtras(message.getData());
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                localBroad.sendBroadcast(intent);

            }
        }, time);
    }

    @Override
    public void releaseBuyTag(int tag) {
        final Intent intent = new Intent();
        intent.setAction(MessageReceiver.ACTION_UNREGISTER);
        Bundle bundle = new Bundle();
        bundle.putInt(MessageReceiver.message_tag, tag);
        intent.putExtras(bundle);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                localBroad.sendBroadcast(intent);
            }
        }, defaultTime);
    }


    public Message obtainStringMessage(int what, HashMap<String, String> map) {

        Message message = new Message();
        message.what = what;
        Bundle bundle = new Bundle();

        Set<String> keySet = map.keySet();
        Iterator<String> iterator = keySet.iterator();

        while (iterator.hasNext()) {
            String next = iterator.next();
            bundle.putString(next, map.get(next));
        }

        message.setData(bundle);
        return message;
    }

    public Message obtainIntgerMessage(int what, HashMap<String, Integer> map) {

        Message message = new Message();
        message.what = what;
        Bundle bundle = new Bundle();

        Set<String> keySet = map.keySet();
        Iterator<String> iterator = keySet.iterator();

        while (iterator.hasNext()) {
            String next = iterator.next();
            bundle.putInt(next, map.get(next));
        }
        message.setData(bundle);
        return message;
    }

}