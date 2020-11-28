package com.supermetrics.services;

import com.supermetrics.client.Client;
import com.supermetrics.model.MonthlyStat;
import com.supermetrics.model.WeeklyStat;
import com.supermetrics.services.client.FakeClient;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

/**
 * @author Mehdi Shahdoost
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ReportGeneratorTest {

    private Client client;

    @BeforeAll
    public void setUp() {
        client = new FakeClient(null);
    }

    /**
     * simulates 1 month with two user.
     */
    @Test
    public void statics_one_month() {
        ReportGenerator reportGenerator = new ReportGenerator("fake-token",
                client, 1);
        reportGenerator.generate();
        MonthlyStat monthlyStat = reportGenerator.getMonthlyStat();
        WeeklyStat weeklyStat = reportGenerator.getWeeklyStat();
        Assertions.assertEquals(monthlyStat.getData().size(), 1);
        Assertions.assertEquals(monthlyStat.getData().get(0).getMonth(), 1);
        Assertions.assertEquals(monthlyStat.getData().get(0).getAveragePostLength(), 10.1);
        Assertions.assertEquals(monthlyStat.getData().get(0).getLongestPost(), 11);
        Assertions.assertEquals(monthlyStat.getData().get(0).getAveragePostPerUser(), 5.0);
        Assertions.assertEquals(weeklyStat.getPostPerWeek().get(1), 10);
    }

    /**
     * simulates 10 month with two user.
     */
    @Test
    public void statics_ten_month() {
        ReportGenerator reportGenerator = new ReportGenerator("fake-token",
                client, 10);
        reportGenerator.generate();
        MonthlyStat monthlyStat = reportGenerator.getMonthlyStat();
        WeeklyStat weeklyStat = reportGenerator.getWeeklyStat();
        Assertions.assertEquals(monthlyStat.getData().size(), 10);
        Assertions.assertEquals(monthlyStat.getData().get(0).getMonth(), 1);
        Assertions.assertEquals(monthlyStat.getData().get(0).getAveragePostLength(), 10.1);
        Assertions.assertEquals(monthlyStat.getData().get(0).getLongestPost(), 11);
        Assertions.assertEquals(monthlyStat.getData().get(0).getAveragePostPerUser(), 5.0);
        Assertions.assertEquals(weeklyStat.getPostPerWeek().get(1), 10);
        Assertions.assertEquals(weeklyStat.getPostPerWeek().get(5), 10);
    }

}
