package com.example.rongcloud.rcdemo.utils;

import android.content.Context;
import android.content.SharedPreferences;

import io.rong.imlib.model.Conversation;

/**
 * Created by rongcloud on 2017/7/26.
 */

public class SPUtils {

    private static final String currentConversationType = "currentConversationType";

    public static void updateCurrentConversationType(Context context, String type) {
        SharedPreferences conversationtype = context.getSharedPreferences(currentConversationType, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = conversationtype.edit();
        editor.putString("type", type);
        editor.apply();
    }

    public static Conversation.ConversationType getCurrentConversationType(Context context) {
        SharedPreferences sharedPreferences
                = context.getSharedPreferences(currentConversationType, Context.MODE_PRIVATE);
        Conversation.ConversationType conversationType
                = Conversation.ConversationType.valueOf(sharedPreferences.getString("type", ""));
        return conversationType;
    }
}
