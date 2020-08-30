package com.neo.gadsrankerapp.models;

import android.os.Parcel;
import android.os.Parcelable;

public class TopLearnerHours implements Parcelable {
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


    protected TopLearnerHours(Parcel in) {
        name = in.readString();
        hours = in.readInt();
        country = in.readString();
        badgeUrl = in.readString();
    }

    public static final Creator<TopLearnerHours> CREATOR = new Creator<TopLearnerHours>() {
        @Override
        public TopLearnerHours createFromParcel(Parcel in) {
            return new TopLearnerHours(in);
        }

        @Override
        public TopLearnerHours[] newArray(int size) {
            return new TopLearnerHours[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeInt(hours);
        dest.writeString(country);
        dest.writeString(badgeUrl);
    }
}
