package com.zwp.myappframework.sample.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by zwp on 2017/5/24.
 * 天气父类
 */

public class WeatherResult implements Parcelable {
    public Sk sk;

    public Today today;

    public List<Future> future ;

    public Sk getSk() {
        return sk;
    }

    @Override
    public String toString() {
        return "WeatherResult{" +
                "sk=" + sk +
                ", today=" + today +
                ", future=" + future +
                '}';
    }

    public void setSk(Sk sk) {
        this.sk = sk;
    }

    public Today getToday() {
        return today;
    }

    public void setToday(Today today) {
        this.today = today;
    }

    public List<Future> getFuture() {
        return future;
    }

    public void setFuture(List<Future> future) {
        this.future = future;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this.sk, flags);
        dest.writeParcelable(this.today, flags);
        dest.writeTypedList(this.future);
    }

    public WeatherResult() {
    }

    protected WeatherResult(Parcel in) {
        this.sk = in.readParcelable(Sk.class.getClassLoader());
        this.today = in.readParcelable(Today.class.getClassLoader());
        this.future = in.createTypedArrayList(Future.CREATOR);
    }

    public static final Parcelable.Creator<WeatherResult> CREATOR = new Parcelable.Creator<WeatherResult>() {
        @Override
        public WeatherResult createFromParcel(Parcel source) {
            return new WeatherResult(source);
        }

        @Override
        public WeatherResult[] newArray(int size) {
            return new WeatherResult[size];
        }
    };
}

