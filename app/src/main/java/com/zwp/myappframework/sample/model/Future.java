package com.zwp.myappframework.sample.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by zwp on 2017/5/24.
 * 未来几天天气
 */

public class Future implements Parcelable {
    public String temperature;

    public String weather;

    public Weather_id weather_id;

    public String wind;

    public String week;

    public String date;

    @Override
    public String toString() {
        return "Future{" +
                "temperature='" + temperature + '\'' +
                ", weather='" + weather + '\'' +
                ", weather_id=" + weather_id +
                ", wind='" + wind + '\'' +
                ", week='" + week + '\'' +
                ", date='" + date + '\'' +
                '}';
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getWeather() {
        return weather;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }

    public Weather_id getWeather_id() {
        return weather_id;
    }

    public void setWeather_id(Weather_id weather_id) {
        this.weather_id = weather_id;
    }

    public String getWind() {
        return wind;
    }

    public void setWind(String wind) {
        this.wind = wind;
    }

    public String getWeek() {
        return week;
    }

    public void setWeek(String week) {
        this.week = week;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.temperature);
        dest.writeString(this.weather);
        dest.writeParcelable(this.weather_id, flags);
        dest.writeString(this.wind);
        dest.writeString(this.week);
        dest.writeString(this.date);
    }

    public Future() {
    }

    protected Future(Parcel in) {
        this.temperature = in.readString();
        this.weather = in.readString();
        this.weather_id = in.readParcelable(Weather_id.class.getClassLoader());
        this.wind = in.readString();
        this.week = in.readString();
        this.date = in.readString();
    }

    public static final Parcelable.Creator<Future> CREATOR = new Parcelable.Creator<Future>() {
        @Override
        public Future createFromParcel(Parcel source) {
            return new Future(source);
        }

        @Override
        public Future[] newArray(int size) {
            return new Future[size];
        }
    };
}