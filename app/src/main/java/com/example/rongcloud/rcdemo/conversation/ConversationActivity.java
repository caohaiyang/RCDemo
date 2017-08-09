package com.example.rongcloud.rcdemo.conversation;

import android.net.Uri;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.rongcloud.rcdemo.R;
import com.example.rongcloud.rcdemo.utils.SPUtils;

import java.util.Locale;

import io.rong.imkit.RongIM;
import io.rong.imlib.IRongCallback;
import io.rong.imlib.RongIMClient;
import io.rong.imlib.model.Conversation;
import io.rong.imlib.model.Message;
import io.rong.imlib.model.UserInfo;
import io.rong.message.InformationNotificationMessage;
import io.rong.message.TextMessage;

public class ConversationActivity extends AppCompatActivity {

    private Toolbar toolBar;
    private Conversation.ConversationType mConversationType;
    private String mTargetId;
    private String TAG = "RCDemo";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conversation);

        mConversationType = Conversation.ConversationType
                .valueOf(getIntent().getData().getLastPathSegment().toUpperCase(Locale.US));
        mTargetId = getIntent().getData().getQueryParameter("targetId");
        String title = getIntent().getData().getQueryParameter("title");

        Log.i(TAG, "onCreate: conversationType=" + mConversationType + ";targetId=" + mTargetId + ";title=" + title);
        RongIM.getInstance().refreshUserInfoCache(new UserInfo("002", "用户333", Uri.parse("http://img5.imgtn.bdimg.com/it/u=3035715378,1205208329&fm=26&gp=0.jpg")));

        toolBar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolBar);
        ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);
//        Message msg = Message.obtain(RongIM.getInstance().getCurrentUserId(), mConversationType, informationNotificationMessage);
//        RongIM.getInstance().sendMessage(msg, "1", "2", new IRongCallback.ISendMessageCallback() {
//            @Override
//            public void onAttached(Message message) {
//
//            }
//
//            @Override
//            public void onSuccess(Message message) {
//
//            }
//
//            @Override
//            public void onError(Message message, RongIMClient.ErrorCode errorCode) {
//
//            }
//        });
        SPUtils.updateCurrentConversationType(this, mConversationType.getName());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (mConversationType == Conversation.ConversationType.DISCUSSION) {
            getMenuInflater().inflate(R.menu.menu_discussion, menu);
        } else if (mConversationType == Conversation.ConversationType.PRIVATE) {
            getMenuInflater().inflate(R.menu.menu_excludediscussion, menu);
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.menu_exitdiscussion: {
                final String discussionId = getIntent().getData().getQueryParameter("targetId");
                RongIM.getInstance().quitDiscussion(discussionId, new RongIMClient.OperationCallback() {
                    @Override
                    public void onSuccess() {
                        RongIM.getInstance().removeConversation(Conversation.ConversationType.DISCUSSION, discussionId, new RongIMClient.ResultCallback<Boolean>() {
                            @Override
                            public void onSuccess(Boolean aBoolean) {
                                Toast.makeText(ConversationActivity.this, "成功退出讨论组", Toast.LENGTH_SHORT).show();
                                finish();
                            }

                            @Override
                            public void onError(RongIMClient.ErrorCode errorCode) {

                            }
                        });
                    }

                    @Override
                    public void onError(RongIMClient.ErrorCode errorCode) {

                    }
                });
                break;
            }
            case R.id.menu_sendinfomsg: {
                InformationNotificationMessage infoMsg
                        = new InformationNotificationMessage("This is a info msg");
                RongIM.getInstance().insertIncomingMessage(mConversationType, mTargetId, "",
                        new Message.ReceivedStatus(1), infoMsg, new RongIMClient.ResultCallback<Message>() {
                    @Override
                    public void onSuccess(Message message) {

                    }

                    @Override
                    public void onError(RongIMClient.ErrorCode errorCode) {

                    }
                });
            }
        }
        return super.onOptionsItemSelected(item);
    }
}
