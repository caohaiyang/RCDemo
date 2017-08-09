package com.example.rongcloud.rcdemo.message;

import android.content.Context;
import android.text.Spannable;
import android.text.SpannableString;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.rongcloud.rcdemo.R;

import io.rong.imkit.RongContext;
import io.rong.imkit.emoticon.AndroidEmoji;
import io.rong.imkit.model.ProviderTag;
import io.rong.imkit.model.UIMessage;
import io.rong.imkit.widget.provider.IContainerItemProvider;
import io.rong.imlib.model.Message;
import io.rong.imlib.model.MessageContent;

/**
 * Created by rongcloud on 2017/7/26.
 */

@ProviderTag(messageContent = CustomMessage.class)
public class CustomMessageProvider extends IContainerItemProvider.MessageProvider<CustomMessage> {

    @Override
    public void bindView(View view, int i, CustomMessage customMessage, UIMessage uiMessage) {
        ViewHolder holder = (ViewHolder) view.getTag();
        holder.message.setText(customMessage.getContent());
    }

    @Override
    public Spannable getContentSummary(CustomMessage customMessage) {
        return new SpannableString(customMessage.getContent());
    }

    @Override
    public void onItemClick(View view, int i, CustomMessage customMessage, UIMessage uiMessage) {

    }

    class ViewHolder {
        TextView message;
    }
    

    @Override
    public View newView(Context context, ViewGroup viewGroup) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_custom_message, null);
        ViewHolder holder = new ViewHolder();
        holder.message = view.findViewById(R.id.message);
        Log.i("aaaaa", "newView: messageTextView=null?" + (holder.message == null));
        view.setTag(holder);
        return view;
    }
}
