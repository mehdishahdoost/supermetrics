package com.supermetrics.client.sm;

import com.supermetrics.client.PostResult;

/**
 * @author Mehdi Shahdoost
 */
public class SmPostResult implements PostResult {
    private Meta meta;
    private PostData data;

    public Meta getMeta() {
        return meta;
    }

    public void setMeta(Meta meta) {
        this.meta = meta;
    }

    public PostData getData() {
        return data;
    }

    public void setData(PostData data) {
        this.data = data;
    }
}
