package com.supermetrics.client.sm;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.supermetrics.client.TokenRequest;

/**
 * @author Mehdi Shahdoost
 */
public class SmTokenRequest implements TokenRequest {

    private String clientId;
    private String email;
    private String name;

    public SmTokenRequest(String clientId, String email, String name) {
        this.clientId = clientId;
        this.email = email;
        this.name = name;
    }

    @JsonProperty("client_id")
    public String getClientId() {
        return clientId;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }
}
