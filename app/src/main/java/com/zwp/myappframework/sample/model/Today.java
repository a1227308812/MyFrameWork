package com.zwp.myappframework.sample.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by zwp on 2017/5/24.
 * 今日天气
 */

public class Today implements Parcelable {
    public String temperature;/*今日温度*/

    public String weather;/*今日天气*/

    public Weather_id weather_id;/*天气唯一标识*/

    public String wind;

    public String week;

    public String city;

    public String date_y;//"2014年03月21日",

    public String dressing_index;/*穿衣指数*/

    public String dressing_advice;/*穿衣建议*/

    public String uv_index;	/*紫外线强度*/

    public String comfort_index;/*舒适度指数*/

    public String wash_index;/*洗车指数*/

    public String travel_index;/*旅游指数*/

    public String exercise_index;/*晨练指数*/

    public String drying_index;/*干燥指数*/

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

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDate_y() {
        return date_y;
    }

    public void setDate_y(String date_y) {
        this.date_y = date_y;
    }

    public String getDressing_index() {
        return dressing_index;
    }

    public void setDressing_index(String dressing_index) {
        this.dressing_index = dressing_index;
    }

    public String getDressing_advice() {
        return dressing_advice;
    }

    public void setDressing_advice(String dressing_advice) {
        this.dressing_advice = dressing_advice;
    }

    public String getUv_index() {
        return uv_index;
    }

    public void setUv_index(String uv_index) {
        this.uv_index = uv_index;
    }

    public String getComfort_index() {
        return comfort_index;
    }

    public void setComfort_index(String comfort_index) {
        this.comfort_index = comfort_index;
    }

    public String getWash_index() {
        return wash_index;
    }

    public void setWash_index(String wash_index) {
        this.wash_index = wash_index;
    }

    public String getTravel_index() {
        return travel_index;
    }

    public void setTravel_index(String travel_index) {
        this.travel_index = travel_index;
    }

    public String getExercise_index() {
        return exercise_index;
    }

    public void setExercise_index(String exercise_index) {
        this.exercise_index = exercise_index;
    }

    public String getDrying_index() {
        return drying_index;
    }

    public void setDrying_index(String drying_index) {
        this.drying_index = drying_index;
    }

    @Override
    public String toString() {
        return "Today{" +
                "temperature='" + temperature + '\'' +
                ", weather='" + weather + '\'' +
                ", weather_id=" + weather_id +
                ", wind='" + wind + '\'' +
                ", week='" + week + '\'' +
                ", city='" + city + '\'' +
                ", date_y='" + date_y + '\'' +
                ", dressing_index='" + dressing_index + '\'' +
                ", dressing_advice='" + dressing_advice + '\'' +
                ", uv_index='" + uv_index + '\'' +
                ", comfort_index='" + comfort_index + '\'' +
                ", wash_index='" + wash_index + '\'' +
                ", travel_index='" + travel_index + '\'' +
                ", exercise_index='" + exercise_index + '\'' +
                ", drying_index='" + drying_index + '\'' +
                '}';
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
        dest.writeString(this.city);
        dest.writeString(this.date_y);
        dest.writeString(this.dressing_index);
        dest.writeString(this.dressing_advice);
        dest.writeString(this.uv_index);
        dest.writeString(this.comfort_index);
        dest.writeString(this.wash_index);
        dest.writeString(this.travel_index);
        dest.writeString(this.exercise_index);
        dest.writeString(this.drying_index);
    }

    public Today() {
    }

    protected Today(Parcel in) {
        this.temperature = in.readString();
        this.weather = in.readString();
        this.weather_id = in.readParcelable(Weather_id.class.getClassLoader());
        this.wind = in.readString();
        this.week = in.readString();
        this.city = in.readString();
        this.date_y = in.readString();
        this.dressing_index = in.readString();
        this.dressing_advice = in.readString();
        this.uv_index = in.readString();
        this.comfort_index = in.readString();
        this.wash_index = in.readString();
        this.travel_index = in.readString();
        this.exercise_index = in.readString();
        this.drying_index = in.readString();
    }

    public static final Parcelable.Creator<Today> CREATOR = new Parcelable.Creator<Today>() {
        @Override
        public Today createFromParcel(Parcel source) {
            return new Today(source);
        }

        @Override
        public Today[] newArray(int size) {
            return new Today[size];
        }
    };
}
