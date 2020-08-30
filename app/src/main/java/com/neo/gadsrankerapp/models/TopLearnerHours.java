package com.neo.gadsrankerapp.models;

public class TopLearnerHours {
    private String name;
    private int hours;
    private String country;
    private String badgeUrl;

    public TopLearnerHours(String userName, int hours, String country, String badgeUrl) {
        this.name = userName;
        this.hours = hours;
        this.country = country;
        this.badgeUrl = badgeUrl;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getBadgeUrl() {
        return badgeUrl;
    }

    public void setBadgeUrl(String badgeUrl) {
        this.badgeUrl = badgeUrl;
    }

    @Override
    public String toString() {
        return "TopLearnerHours{" +
                "name='" + name + '\'' +
                ", hours=" + hours +
                ", country='" + country + '\'' +
                ", badgeUrl='" + badgeUrl + '\'' +
                '}';
    }
}
