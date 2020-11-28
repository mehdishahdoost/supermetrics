package com.supermetrics.model;

import java.util.ArrayList;

/**
 * @author Mehdi Shahdoost
 */
public class MonthlyStat {

    private ArrayList<MonthlyStatData> data = new ArrayList<>();


    public ArrayList<MonthlyStatData> getData() {
        return data;
    }

    public void addMonthlyData(MonthlyStatData monthlyStatData) {
        data.add(monthlyStatData);
    }

}
