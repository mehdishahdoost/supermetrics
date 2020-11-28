package com.supermetrics.client.sm;

import com.supermetrics.client.Params;

/**
 * @author Mehdi Shahdoost
 */
public class SmParams implements Params {

    private String token;
    private int page;

    public SmParams(String token, int page) {
        this.token = token;
        this.page = page;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }
}
