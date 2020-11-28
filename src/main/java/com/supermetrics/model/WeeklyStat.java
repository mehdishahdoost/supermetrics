package com.supermetrics.model;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Mehdi Shahdoost
 */
public class WeeklyStat {
    private HashMap<Integer, Integer> postPerWeek = new HashMap<>();

    public HashMap<Integer, Integer> getPostPerWeek() {
        return postPerWeek;
    }

    public void putPostPerWeek(Integer week, Integer post) {
        postPerWeek.put(week, post);
    }

    public void putAll(Map<Integer, Integer> map) {
        postPerWeek.putAll(map);
    }
}
