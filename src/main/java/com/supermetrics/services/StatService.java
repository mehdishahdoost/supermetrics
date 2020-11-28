package com.supermetrics.services;

import com.supermetrics.client.sm.Post;
import com.supermetrics.model.MonthlyStat;
import com.supermetrics.model.MonthlyStatData;
import com.supermetrics.model.WeeklyStat;
import com.supermetrics.repository.WeeklyStatRepository;
import com.supermetrics.repository.StatRepository;

import java.time.Month;
import java.util.Arrays;
import java.util.Calendar;
import java.util.IntSummaryStatistics;

/**
 * StatService - process raw data to generate stats
 *
 * @author Mehdi Shahdoost
 */
public final class StatService {

    private static StatService instance;
    private StatRepository stats = new StatRepository();
    private WeeklyStatRepository weeklyStatRepository = new WeeklyStatRepository();

    private StatService() {
    }

    public synchronized static StatService getInstance() {
        if (instance == null)
            return instance = new StatService();
        return instance;
    }

    /**
     * process each {@link Post} and generate monthly and weekly stat
     *
     * @param posts list of {@link Post}
     */
    public void generateStats(Post[] posts) {
        Arrays.stream(posts).forEach(p -> {
            stats.updateUserStat(p.getFromName(), p.getFromId(),
                    Month.of(p.getCreatedTime().getMonth() + 1), p.getMessage().length(), p.getMessage());
            Calendar instance = Calendar.getInstance();
            instance.setTime(p.getCreatedTime());
            int week_of_year = instance.get(Calendar.WEEK_OF_YEAR);
            weeklyStatRepository.addWeekTotalPost(week_of_year, 1);
        });
    }

    /**
     * Returns monthly stats
     *
     * @return {@link MonthlyStat}
     */
    public MonthlyStat getMonthlyStat() {
        MonthlyStat monthlyStat = new MonthlyStat();
        stats.getMonthList().stream().forEach(m -> {
            IntSummaryStatistics intSummaryStatistics = stats.getMonth(Month.of(m)).parallelStream().map(s -> s.getPostLength()).
                    flatMap(f -> f.stream()).mapToInt(Integer::intValue).summaryStatistics();
            long userNumber = stats.getMonth(Month.of(m)).stream().count();
            int sumPost = stats.getMonth(Month.of(m)).stream().mapToInt(s -> s.getPostLength().size()).sum();
            double average = intSummaryStatistics.getAverage();
            int max = intSummaryStatistics.getMax();
            MonthlyStatData monthlyStatData = new MonthlyStatData();
            monthlyStatData.setMonth(m);
            monthlyStatData.setAveragePostLength(average);
            monthlyStatData.setLongestPost(max);
            monthlyStatData.setAveragePostPerUser(sumPost / userNumber);
            monthlyStat.addMonthlyData(monthlyStatData);
        });
        return monthlyStat;
    }

    /**
     * Returns weekly stats.
     *
     * @return {@link WeeklyStat}
     */
    public WeeklyStat getWeeklyStat() {
        WeeklyStat weeklyStat = new WeeklyStat();
        weeklyStat.putAll(weeklyStatRepository.getPostPerWeek());
        return weeklyStat;
    }

    public void clear() {
        stats.clear();
        weeklyStatRepository.clear();
    }
}
