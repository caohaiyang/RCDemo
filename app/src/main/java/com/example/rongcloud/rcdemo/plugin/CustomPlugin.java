package com.example.rongcloud.rcdemo.plugin;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.util.Log;

import com.example.rongcloud.rcdemo.R;
import com.example.rongcloud.rcdemo.message.CustomMessage;

import io.rong.imkit.RongExtension;
import io.rong.imkit.RongIM;
import io.rong.imkit.plugin.IPluginModule;
import io.rong.imlib.IRongCallback;
import io.rong.imlib.RongIMClient;
import io.rong.imlib.model.Conversation;
import io.rong.imlib.model.Message;
import io.rong.message.InformationNotificationMessage;

/**
 * Created by rongcloud on 2017/7/25.
 */

public class CustomPlugin implements IPluginModule {
    String TAG = "RCDemo";
    @Override
    public Drawable obtainDrawable(Context context) {
        return context.getResources().getDrawable(R.mipmap.ic_launcher);
    }

    @Override
    public String obtainTitle(Context context) {
        return "自定义消息";
    }



    @Override
    public void onClick(Fragment fragment, RongExtension rongExtension) {

        InformationNotificationMessage informationNotificationMessage
                = new InformationNotificationMessage("this is a piece of info msg");
        RongIM.getInstance().insertIncomingMessage(
                rongExtension.getConversationType(),
                rongExtension.getTargetId(),
                "",null,
                informationNotificationMessage,null);
//        CustomMessage message = CustomMessage.obtain("自定义消息\n自定义消息\n<a href=\"www.baidu.com\">自定义消息</a>");
//        Message msg = Message.obtain(rongExtension.getTargetId(), rongExtension.getConversationType(), message);
//        RongIM.getInstance().sendMessage(msg, message.getContent(), null, new IRongCallback.ISendMessageCallback() {
//            @Override
//            public void onAttached(Message message) {
//
//            }
//
//            @Override
//            public void onSuccess(Message message) {
//                Log.i(TAG, "onSuccess: custom msg");
//            }
//
//            @Override
//            public void onError(Message message, RongIMClient.ErrorCode errorCode) {
//
//            }
//        });
    }

    @Override
    public void onActivityResult(int i, int i1, Intent intent) {

    }
}
