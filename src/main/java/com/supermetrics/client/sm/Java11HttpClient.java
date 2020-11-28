package com.supermetrics.client.sm;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.supermetrics.client.Params;
import com.supermetrics.client.PostResult;
import com.supermetrics.client.TokenRequest;
import com.supermetrics.exception.ServerException;
import com.supermetrics.client.HttpClient;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

/**
 * @author Mehdi Shahdoost
 */
public class Java11HttpClient extends HttpClient {

    private java.net.http.HttpClient httpClient = java.net.http.HttpClient.newBuilder().
            version(java.net.http.HttpClient.Version.HTTP_2).build();

    public Java11HttpClient(String url) {
        super(url);
    }

    @Override
    public SmToken post(TokenRequest t, String path) throws IOException, InterruptedException, ServerException {
        ObjectMapper objectMapper = new ObjectMapper();
        String requestObject = objectMapper.writeValueAsString(t);
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(getUrl() + path)).POST(
                HttpRequest.BodyPublishers.ofString(requestObject)
        ).header("Content-Type", "application/json").build();
        HttpResponse<String> result = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        if (result.statusCode() == 500) {
            throw new ServerException("Server Exception");
        }
        return objectMapper.readValue(result.body(), SmToken.class);
    }

    @Override
    public PostResult get(Params params, String path) throws IOException, InterruptedException, ServerException {
        SmParams smParams = (SmParams)params;
        ObjectMapper objectMapper = new ObjectMapper();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(getUrl() + path + "?sl_token=" +
                smParams.getToken() + "&page=" + smParams.getPage())).GET().
                header("Content-Type", "application/json").build();
        HttpResponse<String> result = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        if (result.statusCode() == 500) {
            throw new ServerException("Server Exception");
        }
        return objectMapper.readValue(result.body(), SmPostResult.class);
    }


}
