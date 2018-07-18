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

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ChartActivity extends Activity {
    private LineChart lineChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart);
        lineChart = (LineChart) findViewById(R.id.linechart);

        List<Entry> entries = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            entries.add(new Entry(i + 1, random.nextInt(240) + 30));
        }

        LineDataSet set = new LineDataSet(entries, "BarDataSet");
        set.setColor(getResources().getColor(R.color.black));         //设置线条颜色
        set.setDrawValues(true);                                      //设置显示数据点值
        set.setValueTextColor(getResources().getColor(R.color.black));//设置显示值的字体颜色
        set.setValueTextSize(12);

        LineData lineData = new LineData(set);
        lineChart.setData(lineData);
        lineChart.invalidate();                                                //刷新显示绘图
        lineChart.setBackgroundColor(getResources().getColor(R.color.white));  //设置LineChart的背景颜色
//设置折线图中每个数据点的选中监听
        lineChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
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
        leftAxis.setTextSize(12f);
        leftAxis.setAxisLineWidth(2);
        leftAxis.setAxisMinValue(0);
        leftAxis.setAxisMaxValue(280);
        leftAxis.setAxisLineColor(getResources().getColor(R.color.black));
        lineChart.getAxisRight().setEnabled(false); // 设置不显示右y轴  默认会显示右y轴

        XAxis xAxis = lineChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setTextSize(12f);
        xAxis.setAxisLineWidth(2);
        xAxis.setAxisMinValue(0);
        xAxis.setLabelCount(10);
        xAxis.setAxisMaxValue(10);
        xAxis.setAxisLineColor(getResources().getColor(R.color.black));
        xAxis.setTextColor(R.color.black);
        xAxis.setDrawAxisLine(true);
        xAxis.setDrawGridLines(false);
    }
}
