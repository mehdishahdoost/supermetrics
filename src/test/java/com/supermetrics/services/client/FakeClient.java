package com.supermetrics.services.client;

import com.supermetrics.client.*;
import com.supermetrics.client.sm.*;
import com.supermetrics.exception.ServerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.stream.IntStream;

/**
 * FakeClient - creates fake client to retrieve data for testing purpose.
 *
 * @author Mehdi Shahdoost
 */
public class FakeClient extends Client {

    private Logger LOG = LoggerFactory.getLogger(FakeClient.class);

    public FakeClient(HttpClient httpClient) {
        super(httpClient);
    }

    @Override
    public Token register(TokenRequest tokenRequest) throws IOException, InterruptedException, ServerException {
        SmToken smToken = new SmToken();
        Data data = new Data();
        data.setToken("fake-token");
        data.setEmail("fake@gmail.com");
        data.setClientId("fake-client-id");
        smToken.setData(data);
        return smToken;
    }

    @Override
    public PostResult getPosts(Params params) throws InterruptedException, ServerException, IOException {
        SmParams smParams = (SmParams) params;
        int page = smParams.getPage();
        SmPostResult smPostResult = new SmPostResult();
        PostData postData = new PostData();
        Post[] posts = generatesPosts(page);
        postData.setPage(page);
        postData.setPosts(posts);
        smPostResult.setData(postData);
        return smPostResult;
    }

    private Post[] generatesPosts(int page) {
        Post[] posts = new Post[10];
        IntStream.range(1, 11).forEach(a -> {
            Post post = new Post();
            post.setFromName("user-" + (a >= 5 ? 1 : 2));
            post.setFromId("user-" + (a >= 5 ? 1 : 2));
            post.setMessage("message-" + a + page);
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
            try {
                post.setCreatedTime(simpleDateFormat.parse("2020-" + (page != 10 ? "0" + page : page)  +
                        "-01T12:00:00+00:00"));
            } catch (ParseException e) {
               LOG.error(e.getMessage());
            }
            posts[a - 1] = post;
        });
        return posts;
    }
}
