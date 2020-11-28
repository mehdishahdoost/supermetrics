package com.supermetrics.client.sm;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author Mehdi Shahdoost
 */
public class Data {

    @JsonProperty("sl_token")
    private String token;
    @JsonProperty("client_id")
    private String clientId;
    private String email;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
