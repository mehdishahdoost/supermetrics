package com.supermetrics.model;

import java.util.ArrayList;

/**
 * @author Mehdi Shahdoost
 */
public class UserStat {

    private String username;
    private String userId;
    private ArrayList<Integer> postLength;


    public UserStat() {
        postLength = new ArrayList<>();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public ArrayList<Integer> getPostLength() {
        return postLength;
    }

    public void addPostLength(int postLength) {
        this.postLength.add(postLength);
    }
}
