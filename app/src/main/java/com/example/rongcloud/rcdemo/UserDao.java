package com.example.rongcloud.rcdemo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by rongcloud on 2017/7/24.
 */

public class UserDao {

    private static List<User> users = new ArrayList<>();
    private static Map<String, User> userMap = new HashMap<>();

    static {
        User user1 = new User(
                "001",
                "用户1",
                "o3MiUX9B5UdM9WKX4bhWx32djOlKdyJcYtByN4ERVqEyVep8+zPYZmzv5NGSXewoWUiIJmxgmDG5Z0BWXl38Vw==",
                "http://img5.imgtn.bdimg.com/it/u=3717344562,4136166237&fm=26&gp=0.jpg");
        User user2 = new User(
                "002",
                "用户2",
                "k3AEEHFV3nIEI/crJEkI6JBE6LaO7RzQWkRtEyM7Bz1TeBHV0brw9WzNgH7FahYGd1qh4C9o28I=",
                "http://img5.imgtn.bdimg.com/it/u=3035715378,1205208329&fm=26&gp=0.jpg");

        users.add(user1);
        users.add(user2);
        userMap.put("001", user1);
        userMap.put("002", user2);

    }

    public static List<User> getUsers() {
        return users;
    }

    public static User findById(String userId) {
        return userMap.get(userId);
    }

    public static class User {

        public User(String userId, String userName, String token, String portraitUri) {
            this.userId = userId;
            this.userName = userName;
            this.token = token;
            this.portraitUri = portraitUri;
        }

        public String userId;
        public String userName;
        public String token;
        public String portraitUri;
    }
}
