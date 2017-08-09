package com.example.rongcloud.rcdemo.message;

import android.os.Parcel;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

import io.rong.imlib.MessageTag;
import io.rong.imlib.model.MentionedInfo;
import io.rong.imlib.model.MessageContent;
import io.rong.imlib.model.UserInfo;

import static io.rong.imlib.MessageTag.ISCOUNTED;
import static io.rong.imlib.MessageTag.ISPERSISTED;

/**
 * Created by rongcloud on 2017/7/25.
 */

@MessageTag(value = "Demo:CusMsg", flag = ISCOUNTED | ISPERSISTED)
public class CustomMessage extends MessageContent {

    private String content;

    public CustomMessage(String content) {
        this.setContent(content);
    }

    public CustomMessage(byte[] data) {
        String jsonStr = null;
        try {
            jsonStr = new String(data, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        try {
            JSONObject jsonObject = new JSONObject(jsonStr);
            if (jsonObject.has("content")) {
                content = jsonObject.getString("content");
            }
            if (jsonObject.has("user")) {
                setUserInfo(parseJsonToUserInfo(jsonObject.getJSONObject("user")));
            }

            if (jsonObject.has("mentionedInfo")) {
                setMentionedInfo(parseJsonToMentionInfo(jsonObject.getJSONObject("mentionedInfo")));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public static CustomMessage obtain(String text) {
        CustomMessage msg = new CustomMessage(text);
        return msg;
    }

    @Override
    public byte[] encode() {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("content", getContent());

            if (getJSONUserInfo() != null) {
                jsonObject.putOpt("user", getJSONUserInfo());
            }

            if (getJsonMentionInfo() != null) {
                jsonObject.putOpt("mentionedInfo", getJsonMentionInfo());
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            return jsonObject.toString().getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(getContent());
        parcel.writeParcelable(getUserInfo(), 0);
        parcel.writeParcelable(getMentionedInfo(), 0);
    }

    public CustomMessage(Parcel in) {
        setContent(in.readString());
        setUserInfo((UserInfo) in.readParcelable(UserInfo.class.getClassLoader()));
        setMentionedInfo((MentionedInfo) in.readParcelable(MentionedInfo.class.getClassLoader()));
    }

    public static final Creator<CustomMessage> CREATOR = new Creator<CustomMessage>() {
        @Override
        public CustomMessage createFromParcel(Parcel parcel) {
            return new CustomMessage(parcel);
        }

        @Override
        public CustomMessage[] newArray(int i) {
            return new CustomMessage[i];
        }
    };
}
