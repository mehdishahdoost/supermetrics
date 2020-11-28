package com.supermetrics.repository;

import com.supermetrics.model.UserStat;

import java.time.Month;
import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;

/**
 * StatRepository - Holds monthly stats
 *
 * @author Mehdi Shahdoost
 */
public class StatRepository {

    private ConcurrentHashMap<Integer, ArrayList<UserStat>> monthUserStat =
            new ConcurrentHashMap<>();

    public ArrayList<UserStat> getMonth(Month month) {
        return monthUserStat.get(month.getValue());
    }

    public void updateUserStat(String username, String userId, Month month, int postLength, String post) {
        ArrayList<UserStat> userStats = monthUserStat.get(month.getValue());
        if (userStats != null) {
            long count = monthUserStat.get(month.getValue()).stream().
                    filter(u -> u.getUsername().equals(username)).count();
            if (count > 0) {
                monthUserStat.get(month.getValue()).stream().
                        filter(u -> u.getUsername().equals(username)).forEach(s -> s.addPostLength(postLength));
            } else {
                UserStat userStat = new UserStat();
                userStat.setUserId(userId);
                userStat.setUsername(username);
                userStat.addPostLength(postLength);
                monthUserStat.get(month.getValue()).add(userStat);
            }
        } else {
            UserStat userStat = new UserStat();
            userStat.setUserId(userId);
            userStat.setUsername(username);
            userStat.addPostLength(postLength);
            ArrayList<UserStat> userStats1 = new ArrayList<>();
            userStats1.add(userStat);
            monthUserStat.put(month.getValue(), userStats1);
        }
    }

    public ArrayList<Integer> getMonthList() {
        return new ArrayList<Integer>(monthUserStat.keySet());
    }

    public void clear() {
        monthUserStat.clear();
    }
}
