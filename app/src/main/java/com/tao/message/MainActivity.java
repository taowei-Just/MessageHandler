package com.tao.message;

import android.content.Context;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.tao.messagehandler.message.MessageHelper;
import com.tao.messagehandler.message.MessageReceiverWapper;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {
    String tag = getClass().getSimpleName();
    private MessageHelper messageHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        messageHelper = MessageHelper.getinstatnce(getApplicationContext());
        Message01 message01 = new Message01(getApplicationContext(), false);
        try {
            Message02 message02 = new Message02(getApplicationContext(), 2);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            Message03 message03 = new Message03(getApplicationContext(), 3);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
 

    class Message01 extends MessageReceiverWapper {

        public Message01(Context context, boolean receiveTag) {
            super(context, receiveTag);
        }

        public Message01(Context context, int tag) throws Exception {
            super(context, tag);
        }

        public Message01(Context context) {
            super(context);
        }

        @Override
        public void receiverMessage(Message message) {
            super.receiverMessage(message);

            Log.e(tag, " Message01 receiverMessage : " + (message == null ? null : message.toString()));
        }

        @Override
        public void release(Context context) throws Exception {
            super.release(context);
        }
    }

    class Message02 extends MessageReceiverWapper {


        public Message02(Context context, int tag) throws Exception {
            super(context, tag);
        }

        public Message02(Context context) {
            super(context);
        }

        @Override
        public void receiverMessage(Message message) {
            super.receiverMessage(message);
            Log.e(tag, "Message02 receiverMessage :" + (message == null ? null : message.toString()));
        }

        @Override
        public void release(Context context) throws Exception {
            super.release(context);
        }
    }

    class Message03 extends MessageReceiverWapper {


        public Message03(Context context, int tag) throws Exception {
            super(context, tag);
        }

        public Message03(Context context) {
            super(context);
        }

        @Override
        public void receiverMessage(Message message) {
            super.receiverMessage(message);
            Log.e(tag, "Message03 receiverMessage :" + (message == null ? null : message.toString()));
        }

        @Override
        public void release(Context context) throws Exception {
            super.release(context);
        }
    }

    @OnClick(R.id.sendMessage)
    public void sendMessage() {
        Message message = new Message();
        message.what = 1;
        Bundle bundle = new Bundle();
        bundle.putString("str", "str");
        bundle.putSerializable("serial", "serial");
        message.setData(bundle);
        Log.e(tag, "  sendMessage ");
        try {
            messageHelper.sendMessage(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @OnClick(R.id.sendMessageBuyTag)
    public void sendMessageBuyTag() {

        Message message = new Message();
        message.what = 1;
        Bundle bundle = new Bundle();
        bundle.putString("str", "str");
        bundle.putSerializable("serial", "serial");
        message.setData(bundle);
        Log.e(tag, "  sendMessageBuyTag ");
        try {
            messageHelper.sendMessageBuyTag(message, 2);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @OnClick(R.id.sendMessageDely)
    public void sendMessageDely() {
        Message message = new Message();
        message.what = 1;
        Bundle bundle = new Bundle();
        bundle.putString("str", "str");
        bundle.putSerializable("serial", "serial");

        message.setData(bundle);
        Log.e(tag, "  sendMessageDely ");
        try {
            messageHelper.sendMessageDely(message, 5 * 1000);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @OnClick(R.id.sendMessageDelyBuyTag)
    public void sendMessageDelyBuyTag() {
        Message message = new Message();
        message.what = 1;
        Bundle bundle = new Bundle();
        bundle.putString("str", "str");
        bundle.putSerializable("serial", "serial");

        message.setData(bundle);
        Log.e(tag, "  sendMessageDelyBuyTag ");
        try {
            messageHelper.sendMessageDelyBuyTag(message, 5 * 1000, 2);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @OnClick(R.id.sendMessageWhat)
    public void sendMessageWhat() {
        Log.e(tag, "  sendMessageWhat");
        messageHelper.sendMessageWhat(1);
    }

    @OnClick(R.id.sendMessageWhatBuyTag)
    public void sendMessageWhatBuyTag() {
        Log.e(tag, "  sendMessageWhatBuyTag");
        messageHelper.sendMessageWhatBuyTag(1, 2);
    }

    @OnClick(R.id.sendMessageWhatDely)
    public void sendMessageWhatDely() {
        Log.e(tag, "  sendMessageWhatBuyTag");
        messageHelper.sendMessageWhatDely(1, 2000);
    } @OnClick(R.id.sendMessageWhatDelyBuyTag)
    public void sendMessageWhatDelyBuyTag() {
        Log.e(tag, "  sendMessageWhatDelyBuyTag");
        messageHelper.sendMessageWhatDelyBuyTag(1, 2000 ,2);
    }

    @OnClick(R.id.releaseAll)
    public void releaseAll() {
        Log.e(tag, "  releaseAll");
        messageHelper.releaseAll();
    }

    @OnClick(R.id.releaseBuyTag)
    public void releaseBuyTag() {
        messageHelper.releaseBuyTag(2);
    }

}
