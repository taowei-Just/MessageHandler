package com.tao.messagehandler.message;

import android.content.Context;
import android.os.Message;

/**
 * Created by Tao on 2018/8/22 0022.
 */

public class MessageReceiverWapper extends MessageReceiver {

    public MessageReceiverWapper(Context context, int tag )throws Exception {
        super(context, tag );
    }

    public MessageReceiverWapper(Context context) {
        super(context);
    }

    public MessageReceiverWapper(Context context, boolean receiveTag) {
        super(context, receiveTag);
    }

    @Override
    public void receiverMessage(Message message) {
    }
}
