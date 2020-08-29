package com.neo.gadsrankerapp.models;

public class TopLearnerHours {
    private String userName;
    private int Time;

    public TopLearnerHours(String userName, int time) {
        this.userName = userName;
        Time = time;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getTime() {
        return Time;
    }

    public void setTime(int time) {
        Time = time;
    }
}
