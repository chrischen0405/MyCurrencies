package com.zucc.cwj31501084.mycurrencies;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ChartActivity extends Activity {
    private LineChart lineChart;
    MyDatabaseManager myDatabaseManager;
    private ArrayList<BeanAllRate> allRates;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart);
        lineChart = (LineChart) findViewById(R.id.linechart);

        myDatabaseManager = new MyDatabaseManager(getBaseContext());
        allRates = new ArrayList<BeanAllRate>();
        allRates = myDatabaseManager.queryAllRate();
        List<Entry> entries = new ArrayList<>();

        if (allRates.size() >= 10) {
            int length = allRates.size();
            float[] data = new float[10];
            for (int i = 9; i >= 0; i--) {
                try {
                    data[i] = caculateRate(allRates.get(length + i - 10));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            for (int i = 1; i < 10; i++) {
                entries.add(new Entry(i, data[i - 1]));
            }
        } else {
            int length = allRates.size();
            float[] data = new float[10];
            for (int i = 0; i < length; i++) {
                try {
                    data[i] = caculateRate(allRates.get(i));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            for (int i = 11 - length; i <= 10; i++) {
                entries.add(new Entry(i, data[i + length - 11]));
            }
        }

        LineDataSet set = new LineDataSet(entries, "BarDataSet");
        set.setColor(getResources().getColor(R.color.black));         //设置线条颜色
        set.setDrawValues(true);                                      //设置显示数据点值
        set.setValueTextColor(getResources().getColor(R.color.black));//设置显示值的字体颜色
        set.setValueTextSize(16);
        set.setLineWidth(3);
        set.setCircleColor(R.color.black);
        set.setCircleRadius(5f);

        LineData lineData = new LineData(set);
        lineChart.setData(lineData);
        lineChart.invalidate();                                                //刷新显示绘图
        lineChart.setBackgroundColor(getResources().getColor(R.color.white));  //设置LineChart的背景颜色

        lineChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {//设置折线图中每个数据点的选中监听
            @Override
            public void onValueSelected(Entry e, Highlight h) {

            }

            @Override
            public void onNothingSelected() {
            }
        });

        YAxis leftAxis = lineChart.getAxisLeft();
        leftAxis.setTextColor(R.color.black);
        leftAxis.setDrawAxisLine(true);
        leftAxis.setDrawGridLines(false);
        leftAxis.setTextSize(16f);
        leftAxis.setAxisLineWidth(3);
        leftAxis.setAxisMinValue(0);
        leftAxis.setAxisMaxValue(10);
        leftAxis.setAxisLineColor(getResources().getColor(R.color.black));
        lineChart.getAxisRight().setEnabled(false); // 设置不显示右y轴  默认会显示右y轴

        XAxis xAxis = lineChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setTextSize(16f);
        xAxis.setAxisLineWidth(3);
        xAxis.setAxisMinValue(0);
        xAxis.setLabelCount(10);
        xAxis.setAxisMaxValue(10);
        xAxis.setAxisLineColor(getResources().getColor(R.color.black));
        xAxis.setTextColor(R.color.black);
        xAxis.setDrawAxisLine(true);
        xAxis.setDrawGridLines(false);
    }

    private float caculateRate(BeanAllRate allRate) throws JSONException {
        JSONObject jsonObject = new JSONObject(allRate.getAllrate());
        float rate = (float) jsonObject.getDouble("CNY");
        return rate;
    }
}
