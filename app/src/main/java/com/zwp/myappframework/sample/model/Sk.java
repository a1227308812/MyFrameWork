package com.zwp.myappframework.sample.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by zwp on 2017/5/24.
 * 当前实况天气
 */

public class Sk implements Parcelable {
    public String temp;/*当前温度*/

    public String wind_direction;/*当前风向*/

    public String wind_strength;/*当前风力*/

    public String humidity;/*当前湿度*/

    public String time;/*更新时间*/

    public String getTemp() {
        return temp;
    }

    public void setTemp(String temp) {
        this.temp = temp;
    }

    public String getWind_direction() {
        return wind_direction;
    }

    public void setWind_direction(String wind_direction) {
        this.wind_direction = wind_direction;
    }

    public String getWind_strength() {
        return wind_strength;
    }

    public void setWind_strength(String wind_strength) {
        this.wind_strength = wind_strength;
    }

    public String getHumidity() {
        return humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "Sk{" +
                "temp='" + temp + '\'' +
                ", wind_direction='" + wind_direction + '\'' +
                ", wind_strength='" + wind_strength + '\'' +
                ", humidity='" + humidity + '\'' +
                ", time='" + time + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.temp);
        dest.writeString(this.wind_direction);
        dest.writeString(this.wind_strength);
        dest.writeString(this.humidity);
        dest.writeString(this.time);
    }

    public Sk() {
    }

    protected Sk(Parcel in) {
        this.temp = in.readString();
        this.wind_direction = in.readString();
        this.wind_strength = in.readString();
        this.humidity = in.readString();
        this.time = in.readString();
    }

    public static final Parcelable.Creator<Sk> CREATOR = new Parcelable.Creator<Sk>() {
        @Override
        public Sk createFromParcel(Parcel source) {
            return new Sk(source);
        }

        @Override
        public Sk[] newArray(int size) {
            return new Sk[size];
        }
    };
}
