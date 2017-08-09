package com.example.rongcloud.rcdemo.conversationlist;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.rongcloud.rcdemo.R;

import io.rong.imkit.RongIM;
import io.rong.imlib.model.Conversation;

public class SubConversationListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_conversation_list);

        new Thread() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }
}
