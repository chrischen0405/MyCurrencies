package com.zucc.cwj31501084.mycurrencies;

/**
 * Created by chenwenjie on 2018/7/20.
 */

public class BeanAllRate {
    private long ratetime;
    private String allrate;

    public BeanAllRate() {

    }

    public BeanAllRate(long ratetime, String allrate) {
        this.ratetime = ratetime;
        this.allrate = allrate;
    }

    public String getAllrate() {
        return allrate;
    }

    public void setAllrate(String allrate) {
        this.allrate = allrate;
    }

    public long getRatetime() {
        return ratetime;
    }

    public void setRatetime(long ratetime) {
        this.ratetime = ratetime;
    }
}
