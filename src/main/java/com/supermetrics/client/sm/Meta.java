package com.supermetrics.client.sm;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author Mehdi Shahdoost
 */
public class Meta {

    @JsonProperty("request_id")
    private String requestId;

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }
}
