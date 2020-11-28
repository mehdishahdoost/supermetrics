
package com.supermetrics.repository;

import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

/**
 * WeeklyStatRepository - Holds weekly stats
 *
 * @author Mehdi Shahdoost
 */
public class WeeklyStatRepository {

    private ConcurrentHashMap<Integer, Integer> totalPostPerWeek = new ConcurrentHashMap<>();

    public void addWeekTotalPost(int weekNum, int postNum) {
        if (totalPostPerWeek.get(weekNum) == null) {
            totalPostPerWeek.put(weekNum, postNum);
        } else {
            Integer oldPostNum = totalPostPerWeek.get(weekNum);
            totalPostPerWeek.put(weekNum, oldPostNum + postNum);
        }
    }

    public HashMap<Integer, Integer> getPostPerWeek() {
        HashMap<Integer, Integer> map = new HashMap<>(totalPostPerWeek);
        return map;
    }

    public void clear() {
        totalPostPerWeek.clear();
    }
}
