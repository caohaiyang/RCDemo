package com.example.rongcloud.rcdemo;

import android.app.Application;
import android.content.Context;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;

import com.example.rongcloud.rcdemo.message.CustomMessage;
import com.example.rongcloud.rcdemo.message.CustomMessageProvider;
import com.example.rongcloud.rcdemo.plugin.CustomExtensionModule;

import java.util.ArrayList;
import java.util.List;

import io.rong.callkit.RongCallKit;
import io.rong.calllib.IRongReceivedCallListener;
import io.rong.calllib.RongCallClient;
import io.rong.calllib.RongCallSession;
import io.rong.imkit.DefaultExtensionModule;
import io.rong.imkit.IExtensionModule;
import io.rong.imkit.RongExtensionManager;
import io.rong.imkit.RongIM;
import io.rong.imkit.fragment.ConversationListFragment;
import io.rong.imkit.mention.MemberMentionedActivity;
import io.rong.imkit.model.GroupUserInfo;
import io.rong.imkit.model.UIConversation;
import io.rong.imkit.plugin.CombineLocationPlugin;
import io.rong.imkit.userInfoCache.RongUserInfoManager;
import io.rong.imkit.widget.adapter.ConversationListAdapter;
import io.rong.imkit.widget.provider.InfoNotificationMsgItemProvider;
import io.rong.imkit.widget.provider.LocationPlugin;
import io.rong.imlib.IRongCallback;
import io.rong.imlib.RongIMClient;
import io.rong.imlib.model.Conversation;
import io.rong.imlib.model.Group;
import io.rong.imlib.model.Message;
import io.rong.imlib.model.MessageContent;
import io.rong.imlib.model.UserInfo;
import io.rong.message.ContactNotificationMessage;

/**
 * Created by rongcloud on 2017/7/24.
 */

public class App extends Application {

    private static final String TAG = "RCDemo";

    @Override
    public void onCreate() {
        super.onCreate();
        RongIM.init(this, "8luwapkv8s2ql");

        setProviders();
        setExtensionModule();

        RongIM.registerMessageType(CustomMessage.class);
        RongIM.registerMessageTemplate(new CustomMessageProvider());
        RongIM.setConversationListBehaviorListener(new RongIM.ConversationListBehaviorListener() {
            @Override
            public boolean onConversationPortraitClick(Context context, Conversation.ConversationType conversationType, String s) {
                return false;
            }

            @Override
            public boolean onConversationPortraitLongClick(Context context, Conversation.ConversationType conversationType, String s) {
                return false;
            }

            @Override
            public boolean onConversationLongClick(Context context, View view, UIConversation uiConversation) {
                return false;
            }

            @Override
            public boolean onConversationClick(Context context, View view, UIConversation uiConversation) {
                MessageContent messageContent = uiConversation.getMessageContent();

                if (messageContent instanceof ContactNotificationMessage) {
                    ContactNotificationMessage message = (ContactNotificationMessage) messageContent;
                    Log.i(TAG, "onConversationClick: Contact");
                    return false;
                }
                return false;
            }
        });
        RongIM.setConnectionStatusListener(new RongIMClient.ConnectionStatusListener() {
            @Override
            public void onChanged(ConnectionStatus connectionStatus) {

            }
        });

//        RongIM.getInstance().setSendMessageListener(new RongIM.OnSendMessageListener() {
//            @Override
//            public Message onSend(Message message) {
//                return null;
//            }
//
//            @Override
//            public boolean onSent(Message message, RongIM.SentMessageErrorCode sentMessageErrorCode) {
//                return false;
//            }
//        });
        RongIM.setConversationListBehaviorListener(new RongIM.ConversationListBehaviorListener() {
            @Override
            public boolean onConversationPortraitClick(Context context, Conversation.ConversationType conversationType, String s) {
                return false;
            }

            @Override
            public boolean onConversationPortraitLongClick(Context context, Conversation.ConversationType conversationType, String s) {
                return false;
            }

            @Override
            public boolean onConversationLongClick(Context context, View view, UIConversation uiConversation) {
                return false;
            }

            @Override
            public boolean onConversationClick(Context context, View view, UIConversation uiConversation) {
                return false;
            }
        });

        RongIM.setOnReceiveMessageListener(new RongIMClient.OnReceiveMessageListener() {
            @Override
            public boolean onReceived(Message message, int i) {
                Log.i(TAG, "onReceived: messageId=" + message.getMessageId());
                return false;
            }
        });
        RongIM.getInstance().setSendMessageListener(new RongIM.OnSendMessageListener() {
            @Override
            public Message onSend(Message message) {
                Log.i(TAG, "onSend: messageId=" + message.getMessageId());

                return message;
            }

            @Override
            public boolean onSent(Message message, RongIM.SentMessageErrorCode sentMessageErrorCode) {
                return false;
            }
        });
        RongIM.setConnectionStatusListener(new RongIMClient.ConnectionStatusListener() {
            @Override
            public void onChanged(ConnectionStatus connectionStatus) {
                Log.i(TAG, "onChanged: connectionStatus=" + connectionStatus.getMessage());
            }
        });
    }

    private void setProviders() {
        RongIM.setUserInfoProvider(new RongIM.UserInfoProvider() {
            @Override
            public UserInfo getUserInfo(String s) {
                final UserDao.User user = UserDao.findById(s);
                if (user == null) {
                    Log.e(TAG, "getUserInfo: s = " + s + ";user is null");
                    return null;
                }
                final UserInfo userInfo = new UserInfo(user.userId, "dadada", Uri.parse(user.portraitUri));
                Log.i(TAG, "getUserInfo");
                new Thread() {
                    @Override
                    public void run() {
                        RongIM.getInstance().refreshUserInfoCache(userInfo);
                    }
                }.start();
                return null;
            }
        }, true);

        RongIM.setGroupInfoProvider(new RongIM.GroupInfoProvider() {
            @Override
            public Group getGroupInfo(String s) {
                Log.i(TAG, "getGroupInfoProvider: " + "s=" + s);
                Group group = new Group("0001", "群0001", null);
                return group;
            }
        }, true);

        RongIM.setGroupUserInfoProvider(new RongIM.GroupUserInfoProvider() {
            @Override
            public GroupUserInfo getGroupUserInfo(String s, String s1) {
                Log.i(TAG, "getGroupUserInfo: s=" + s + ";s1=" + s1);
                if (TextUtils.isEmpty(s1)) {
                    return null;
                }
                GroupUserInfo userInfo = new GroupUserInfo(s, s1, UserDao.findById(s1).userName);
                return userInfo;
            }
        }, true);

//        RongIM.getInstance().setGroupMembersProvider(new RongIM.IGroupMembersProvider() {
//            @Override
//            public void getGroupMembers(String s, RongIM.IGroupMemberCallback iGroupMemberCallback) {
//                List<UserInfo> infos = new ArrayList<UserInfo>();
//                for (UserDao.User user : UserDao.getUsers()) {
//                    UserInfo info = new UserInfo(user.userId, user.userName, Uri.parse(user.portraitUri));
//                    infos.add(info);
//                }
//                iGroupMemberCallback.onGetGroupMembersResult(infos);
//            }
//        });

    }

    private void setExtensionModule() {
        List<IExtensionModule> moduleList = RongExtensionManager.getInstance().getExtensionModules();
        IExtensionModule defaultModule = null;

        if (moduleList != null) {
            for (IExtensionModule module : moduleList) {
                if (module instanceof DefaultExtensionModule) {
                    defaultModule = module;
                    break;
                }
            }
            if (defaultModule != null) {
                RongExtensionManager.getInstance().unregisterExtensionModule(defaultModule);
                RongExtensionManager.getInstance().registerExtensionModule(new CustomExtensionModule());
            }
        }
    }


}
