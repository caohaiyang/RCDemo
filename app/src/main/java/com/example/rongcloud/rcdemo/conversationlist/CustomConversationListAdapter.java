package com.example.rongcloud.rcdemo.conversationlist;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import io.rong.imkit.model.UIConversation;
import io.rong.imkit.widget.adapter.ConversationListAdapter;

/**
 * Created by rongcloud on 2017/7/27.
 */

public class CustomConversationListAdapter extends ConversationListAdapter {
    public CustomConversationListAdapter(Context context) {
        super(context);
    }

    @Override
    protected void bindView(View v, int position, UIConversation data) {
        super.bindView(v, position, data);

    }

    @Override
    protected View newView(Context context, int position, ViewGroup group) {
        return super.newView(context, position, group);
    }
}
