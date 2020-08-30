package com.neo.gadsrankerapp.models;

import android.os.Parcel;
import android.os.Parcelable;

public class TopLearnerSkill implements Parcelable {
    private String name;
    private int score;
    private String country;
    private String badgeUrl;

    public TopLearnerSkill(String userName, int score, String country, String badgeUrl) {
        this.name = userName;
        this.score = score;
        this.country = country;
        this.badgeUrl = badgeUrl;
    }

    protected TopLearnerSkill(Parcel in) {
        name = in.readString();
        score = in.readInt();
        country = in.readString();
        badgeUrl = in.readString();
    }

    public static final Creator<TopLearnerSkill> CREATOR = new Creator<TopLearnerSkill>() {
        @Override
        public TopLearnerSkill createFromParcel(Parcel in) {
            return new TopLearnerSkill(in);
        }

        @Override
        public TopLearnerSkill[] newArray(int size) {
            return new TopLearnerSkill[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
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
        return "TopLearnerSkill{" +
                "name='" + name + '\'' +
                ", score=" + score +
                ", country='" + country + '\'' +
                ", badgeUrl='" + badgeUrl + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeInt(score);
        dest.writeString(country);
        dest.writeString(badgeUrl);
    }
}
