package com.zucc.cwj31501084.mycurrencies;

/**
 * Created by chenwenjie on 2018/7/19.
 */

public class BeanRate {
    private int id;
    private String date;
    private float rate;

    public BeanRate() {
    }

    public BeanRate(int id, String date, float rate) {
        this.id = id;
        this.date = date;
        this.rate = rate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public float getRate() {
        return rate;
    }

    public void setRate(float rate) {
        this.rate = rate;
    }
}
