package com.supermetrics.model;

/**
 * @author Mehdi Shahdoost
 */
public class MonthlyStatData {

    private int month;
    private double averagePostLength;
    private int longestPost;
    private double averagePostPerUser;

    public double getAveragePostPerUser() {
        return averagePostPerUser;
    }

    public void setAveragePostPerUser(double averagePostPerUser) {
        this.averagePostPerUser = averagePostPerUser;
    }

    public int getLongestPost() {
        return longestPost;
    }

    public void setLongestPost(int longestPost) {
        this.longestPost = longestPost;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public double getAveragePostLength() {
        return averagePostLength;
    }

    public void setAveragePostLength(double averagePostLength) {
        this.averagePostLength = averagePostLength;
    }
}
