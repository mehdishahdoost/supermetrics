package com.supermetrics.services;

import com.supermetrics.client.Client;
import com.supermetrics.model.MonthlyStat;
import com.supermetrics.model.WeeklyStat;
import com.supermetrics.repository.StatRepository;

import java.util.concurrent.ForkJoinPool;

/**
 * ReportGenerator - use fork/join framework to retrieve data from supermetrics API and
 * parse data.
 *
 * @author Mehdi Shahdoost
 */
public class ReportGenerator {

    private MonthlyStat monthlyStat;
    private WeeklyStat weeklyStat;
    private String token;
    private Client client;
    private int pageNo;

    public ReportGenerator(String token, Client client, int pageNo) {
        this.token = token;
        this.client = client;
        this.pageNo = pageNo;
    }

    /**
     * uses fork/join framework to fetch data from supermetrics API and
     * generates monthly and weekly stats
     */
    public void generate() {
        StatService.getInstance().clear();
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        ParallelStatExecutor parallelStatExecutor = new ParallelStatExecutor(pageNo,
                token, client);
        forkJoinPool.invoke(parallelStatExecutor);
        monthlyStat = StatService.getInstance().getMonthlyStat();
        weeklyStat = StatService.getInstance().getWeeklyStat();
    }

    public MonthlyStat getMonthlyStat() {
        return this.monthlyStat;
    }

    public WeeklyStat getWeeklyStat() {
        return this.weeklyStat;
    }

}
