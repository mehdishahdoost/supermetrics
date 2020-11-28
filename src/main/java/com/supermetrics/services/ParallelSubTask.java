package com.supermetrics.services;

import com.supermetrics.client.Client;
import com.supermetrics.client.Params;
import com.supermetrics.client.sm.Post;
import com.supermetrics.client.sm.SmParams;
import com.supermetrics.client.sm.SmPostResult;
import com.supermetrics.exception.ServerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.concurrent.RecursiveAction;

/**
 * ParallelSubTask - use fork/join framework to compute each page of data from
 * supermetrics API.
 *
 * @author Mehdi Shahdoost
 */
public class ParallelSubTask extends RecursiveAction {

    private Logger LOG = LoggerFactory.getLogger(ParallelSubTask.class);
    private String token;
    private int page;
    private Client client;

    public ParallelSubTask(String token, int page, Client client) {
        this.token = token;
        this.page = page;
        this.client = client;
    }

    @Override
    protected void compute() {
        Params params = new SmParams(this.token, this.page);
        try {
            SmPostResult posts = (SmPostResult) client.getPosts(params);
            Post[] posts1 = posts.getData().getPosts();
            StatService.getInstance().generateStats(posts1);
        } catch (InterruptedException e) {
            LOG.error(e.getMessage());
        } catch (ServerException e) {
            LOG.error(e.getMessage());
        } catch (IOException exception) {
            LOG.error(exception.getMessage());
        }
    }
}
