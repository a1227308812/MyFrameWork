package com.zwp.myappframework.sample.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by zwp on 2017/5/24.
 */

public class Weather_id implements Parcelable {
    public String fa;/*天气标识00：晴*/

    public String fb;/*天气标识53：霾 如果fa不等于fb，说明是组合天气*/

    public String getFa() {
        return fa;
    }

    public void setFa(String fa) {
        this.fa = fa;
    }

    public String getFb() {
        return fb;
    }

    public void setFb(String fb) {
        this.fb = fb;
    }

    @Override
    public String toString() {
        return "Weather_id{" +
                "fa='" + fa + '\'' +
                ", fb='" + fb + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.fa);
        dest.writeString(this.fb);
    }

    public Weather_id() {
    }

    protected Weather_id(Parcel in) {
        this.fa = in.readString();
        this.fb = in.readString();
    }

    public static final Parcelable.Creator<Weather_id> CREATOR = new Parcelable.Creator<Weather_id>() {
        @Override
        public Weather_id createFromParcel(Parcel source) {
            return new Weather_id(source);
        }

        @Override
        public Weather_id[] newArray(int size) {
            return new Weather_id[size];
        }
    };
}

