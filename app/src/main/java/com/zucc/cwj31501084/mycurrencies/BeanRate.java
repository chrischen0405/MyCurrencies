package com.zucc.cwj31501084.mycurrencies;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by chenwenjie on 2018/7/19.
 */

public class BeanRate implements Parcelable {
    private String country;
    private float countryrate;

    public BeanRate() {
    }

    public BeanRate(String country, float countryrate) {
        this.country=country;
        this.countryrate=countryrate;
    }

    protected BeanRate(Parcel in) {
        country = in.readString();
        countryrate = in.readFloat();
    }

    public static final Creator<BeanRate> CREATOR = new Creator<BeanRate>() {
        @Override
        public BeanRate createFromParcel(Parcel in) {
            return new BeanRate(in);
        }

        @Override
        public BeanRate[] newArray(int size) {
            return new BeanRate[size];
        }
    };

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public float getCountryrate() {
        return countryrate;
    }

    public void setCountryrate(float countryrate) {
        this.countryrate = countryrate;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(country);
        dest.writeFloat(countryrate);
    }
}
