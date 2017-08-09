package com.example.rongcloud.rcdemo.contacts;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.rongcloud.rcdemo.R;

import java.util.Arrays;

import io.rong.imkit.RongIM;

/**
 * Created by rongcloud on 2017/7/24.
 */

public class ContactsFragment extends Fragment implements View.OnClickListener{

    private ListView contactsListView;
    private ListView featureListView;
    private ContactsListAdapter mAdapter;
    private static final String GROUPID = "0001";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_contacts, null, false);
        contactsListView = view.findViewById(R.id.contactslist);
        contactsListView.setAdapter(mAdapter = new ContactsListAdapter(getContext()));

        featureListView = view.findViewById(R.id.featurelist);

        view.findViewById(R.id.creategroupchat).setOnClickListener(this);
        view.findViewById(R.id.creatediscussionchat).setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()) {
            case R.id.creategroupchat: {
                RongIM.getInstance().startGroupChat(getActivity(), GROUPID, "群0001");
                break;
            }
            case R.id.creatediscussionchat: {
                RongIM.getInstance().createDiscussionChat(getActivity(), Arrays.asList("001", "002"), "讨论组001");
                break;
            }
        }
    }
}
