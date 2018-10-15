package com.tao.messagehandler.message;

import android.os.Message;

/**
 * Created by Tao on 2018/8/22 0022.
 */
public  interface MessageInterFace {

    void  sendMessageWhat(int what);

    void  sendMessageWhatDely(int what, long time);

    void  sendMessage(Message message) throws Exception;

    void  sendMessageDely(Message message, long time); 
    
    void  sendMessageWhatBuyTag(int what, int tag);

    void  sendMessageWhatDelyBuyTag(int what, long time, int tag);

    void  sendMessageBuyTag(Message message, int tag) throws Exception;

    void  sendMessageDelyBuyTag(Message message, long time, int tag);
    void  releaseBuyTag(int tag);
    void  releaseAll();



}