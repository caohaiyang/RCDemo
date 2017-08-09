package com.example.rongcloud.rcdemo.user;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.rongcloud.rcdemo.R;
import com.example.rongcloud.rcdemo.UserDao;
import com.jrmf360.rylib.JrmfClient;
import com.squareup.picasso.Picasso;

import java.net.URL;

import io.rong.imkit.RongContext;
import io.rong.imkit.RongIM;
import io.rong.imkit.activity.PicturePagerActivity;
import io.rong.imkit.userInfoCache.RongUserInfoManager;
import io.rong.imkit.widget.provider.ImageMessageItemProvider;
import io.rong.imlib.RongIMClient;
import io.rong.imlib.model.Conversation;
import io.rong.imlib.model.Message;
import io.rong.imlib.model.UserInfo;

/**
 * Created by rongcloud on 2017/7/24.
 */

public class UserInfoFrgament extends Fragment {

    private Button redPacket;
    private Button joinChatRoom;
    private ImageView portrait;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_user, null);
        redPacket = view.findViewById(R.id.redpacket);
        redPacket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                JrmfClient.intentWallet(getActivity());
            }
        });

        portrait = view.findViewById(R.id.portrait);
        String userId = RongIM.getInstance().getCurrentUserId();
        UserDao.User user = UserDao.findById(userId);
        UserInfo userInfo = new UserInfo(user.userId, user.userName, Uri.parse(user.portraitUri));
        Picasso.with(getContext()).load(userInfo.getPortraitUri()).into(portrait);

        joinChatRoom = view.findViewById(R.id.join_chat_room);
        joinChatRoom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RongIM.getInstance().startChatRoomChat(getContext(), "001", true);
            }

        });
        return view;
    }
}
