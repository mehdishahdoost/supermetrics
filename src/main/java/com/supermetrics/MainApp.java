package com.supermetrics;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.supermetrics.client.*;
import com.supermetrics.client.sm.*;
import com.supermetrics.exception.ServerException;
import com.supermetrics.client.HttpClient;
import com.supermetrics.model.MonthlyStat;
import com.supermetrics.model.WeeklyStat;
import com.supermetrics.services.ReportGenerator;
import com.supermetrics.utils.Configs;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.IOException;

/**
 * Show stats on the following:
 *
 * a. - Average character length of posts per month
 * b. - Longest post by character length per month
 * c. - Total posts split by week number
 * d. - Average number of posts per user per month
 *
 * @author Mehdi Shahdoost
 */
public class MainApp {

    private final static Logger LOG = LoggerFactory.getLogger(MainApp.class);
    private static Client client;
    private static String token;

    public static void main(String[] args) {
        try {
            injectClient();
            generateReport(10);
        } catch (InterruptedException e) {
            LOG.error(e.getMessage());
        } catch (ServerException e) {
            LOG.error(e.getMessage());
        } catch (IOException exception) {
            LOG.error(exception.getMessage());
        }
    }

    public static void injectClient() throws InterruptedException, ServerException, IOException {
        String url = Configs.getInstance().getUrl();
        String clientId = Configs.getInstance().getClientId();
        String email = Configs.getInstance().getEmail();
        String name = Configs.getInstance().getName();
        HttpClient httpClient = new Java11HttpClient(url);
        Client client = new SMClient(httpClient);
        TokenRequest tokenRequest = new SmTokenRequest(clientId, email, name);
        SmToken token = (SmToken) client.register(tokenRequest);
        MainApp.client = client;
        MainApp.token = token.getData().getToken();
    }

    private static void generateReport(int pageNo) throws InterruptedException, ServerException, IOException {
        ReportGenerator reportGenerator = new ReportGenerator(MainApp.token, MainApp.client, pageNo);
        reportGenerator.generate();
        MonthlyStat monthlyStat = reportGenerator.getMonthlyStat();
        WeeklyStat weeklyStat = reportGenerator.getWeeklyStat();
        ObjectMapper objectMapper = new ObjectMapper();
        System.out.println("**************** Reports *********************");
        LOG.info("- Average character length of posts per month: " +
                        "- Longest post by character length per month" +
                        "Average number of posts per user per month " +
                        "{}",
                objectMapper.writeValueAsString(monthlyStat));
        System.out.println("*************************************");
        LOG.info("Total posts split by week number: {}",
                objectMapper.writeValueAsString(weeklyStat));
    }
}
