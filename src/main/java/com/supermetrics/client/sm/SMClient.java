package com.supermetrics.client.sm;

import com.supermetrics.client.*;
import com.supermetrics.exception.ServerException;
import com.supermetrics.client.HttpClient;

import java.io.IOException;

/**
 * @author Mehdi Shahdoost
 */
public class SMClient extends Client {

    public SMClient(HttpClient httpClient) {
        super(httpClient);
    }

    public Token register(TokenRequest tokenRequest) throws IOException, InterruptedException, ServerException {
        return this.getHttpClient().post(tokenRequest, "register");
    }

    public PostResult getPosts(Params params) throws InterruptedException, ServerException, IOException {
        return this.getHttpClient().get(params, "posts");
    }
}
