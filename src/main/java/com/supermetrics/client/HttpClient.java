package com.supermetrics.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.supermetrics.client.Params;
import com.supermetrics.client.PostResult;
import com.supermetrics.client.Token;
import com.supermetrics.client.TokenRequest;
import com.supermetrics.exception.ServerException;

import java.io.IOException;

/**
 * @author Mehdi Shahdoost
 */
public abstract class HttpClient{

    private String baseUrl;

    public HttpClient(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public abstract Token post(TokenRequest tokenRequest, String path) throws IOException, InterruptedException, ServerException;

    public abstract PostResult get(Params params, String path) throws IOException, InterruptedException, ServerException;

    public String getUrl() {
        return this.baseUrl;
    }
}
