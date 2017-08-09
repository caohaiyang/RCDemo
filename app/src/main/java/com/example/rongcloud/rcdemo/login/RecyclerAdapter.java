package com.example.rongcloud.rcdemo.login;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rongcloud.rcdemo.home.MainActivity;
import com.example.rongcloud.rcdemo.R;
import com.example.rongcloud.rcdemo.UserDao;
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.rong.imkit.RongIM;
import io.rong.imlib.RongIMClient;
import io.rong.imlib.model.Conversation;
import io.rong.imlib.model.UserInfo;

/**
 * Created by rongcloud on 2017/7/24.
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final String TAG = "RecyclerAdapter";

    private List<UserDao.User> users;
    private Context mContext;

    public RecyclerAdapter(Context context) {
        this.mContext = context;
        users = UserDao.getUsers();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_login, null);
        RecyclerView.ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        ((ViewHolder) holder).bindData(users.get(position), mContext);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RongIM.connect(users.get(position).token, new RongIMClient.ConnectCallback() {
                    @Override
                    public void onTokenIncorrect() {
                        Log.d(TAG, "onTokenIncorrect: ");
                        Toast.makeText(mContext, "incorrect token", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onSuccess(String s) {
                        Log.i(TAG, "onSuccess: ");
                        Map<String, Boolean> types = new HashMap<>();
                        types.put(Conversation.ConversationType.PRIVATE.getName(), true);
                        types.put(Conversation.ConversationType.GROUP.getName(), true);
                        types.put(Conversation.ConversationType.DISCUSSION.getName(), false);
                        types.put(Conversation.ConversationType.SYSTEM.getName(), false);
                        RongIM.getInstance().startConversationList(mContext,types);
                    }

                    @Override
                    public void onError(RongIMClient.ErrorCode errorCode) {
                        Log.e(TAG, "onError: ");
                        Toast.makeText(mContext, "error", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    private static class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView portraitImg;
        private TextView nameText;

        public ViewHolder(View itemView) {
            super(itemView);
            portraitImg = itemView.findViewById(R.id.portrait);
            nameText = itemView.findViewById(R.id.nametext);
        }

        public void bindData(UserDao.User user, Context context) {
            Picasso.with(context).load(user.portraitUri).into(portraitImg);
            nameText.setText(user.userName);
        }
    }
}
