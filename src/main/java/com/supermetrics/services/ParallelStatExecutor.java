package com.supermetrics.services;

import com.supermetrics.client.Client;
import java.util.concurrent.RecursiveAction;
import java.util.stream.IntStream;

/**
 * ParallelStatExecutor - create subtask for fork/join framework
 *
 * @author Mehdi Shahdoost
 */
public class ParallelStatExecutor extends RecursiveAction {

    private int pageNo;
    private String token;
    private Client client;

    public ParallelStatExecutor(int pageNo, String token, Client client) {
        this.pageNo = pageNo;
        this.token = token;
        this.client = client;
    }

    @Override
    protected void compute() {
        if (pageNo > 1) {
            IntStream.range(1, pageNo + 1).forEach(a -> {
                ParallelSubTask parallelSubTask = new ParallelSubTask(token, a, client);
                parallelSubTask.invoke();
            });
        } else {
            ParallelSubTask parallelSubTask = new ParallelSubTask(token, 1, client);
            parallelSubTask.invoke();
        }
    }
}
