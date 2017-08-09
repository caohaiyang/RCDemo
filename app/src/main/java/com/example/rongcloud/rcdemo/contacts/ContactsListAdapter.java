package com.example.rongcloud.rcdemo.contacts;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.rongcloud.rcdemo.R;
import com.example.rongcloud.rcdemo.UserDao;
import com.squareup.picasso.Picasso;

import java.util.List;

import io.rong.imkit.RongIM;
import io.rong.imlib.model.Conversation;

/**
 * Created by rongcloud on 2017/7/24.
 */

public class ContactsListAdapter extends BaseAdapter {

    private Context mContext;
    private List<UserDao.User> users;

    public ContactsListAdapter(Context context) {
        this.mContext = context;
        users = UserDao.getUsers();
    }

    @Override
    public int getCount() {
        return users.size();
    }

    @Override
    public Object getItem(int i) {
        return users.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        ViewHolder holder = null;
        if (view == null) {
            view = LayoutInflater.from(mContext).inflate(R.layout.item_contacts, null);
            holder = new ViewHolder(view, mContext);
            view.setTag(holder);
        }
        holder = (ViewHolder) view.getTag();
        holder.bindData(mContext, users.get(i));
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RongIM.getInstance()
                        .startConversation(mContext, Conversation.ConversationType.PRIVATE,
                                users.get(i).userId, users.get(i).userName);
            }
        });
        return view;
    }

    private static class ViewHolder {
        private ImageView portraitImg;
        private TextView nameText;

        public ViewHolder(View view, Context context) {
            portraitImg = view.findViewById(R.id.portrait);
            nameText = view.findViewById(R.id.name);
        }

        public void bindData(Context context, UserDao.User user) {
            Picasso.with(context).load(user.portraitUri).into(portraitImg);
            nameText.setText(user.userName);
        }
    }
}
