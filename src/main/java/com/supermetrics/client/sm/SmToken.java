package com.supermetrics.client.sm;

import com.supermetrics.client.Token;

/**
 * @author Mehdi Shahdoost
 */
public class SmToken implements Token {

    private Data data;
    private Meta meta;

    public Meta getMeta() {
        return meta;
    }

    public void setMeta(Meta meta) {
        this.meta = meta;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }
}
