package com.supermetrics.client;

import com.supermetrics.exception.ServerException;

import java.io.IOException;

/**
 * @author Mehdi Shahdoost
 */
public abstract class Client {

    private HttpClient httpClient;

    public Client(HttpClient httpClient) {
        this.httpClient = httpClient;
    }

    public abstract Token register(TokenRequest tokenRequest) throws IOException, InterruptedException, ServerException;
    public abstract PostResult getPosts(Params params) throws InterruptedException, ServerException, IOException;

    public HttpClient getHttpClient() {
        return this.httpClient;
    }
}
