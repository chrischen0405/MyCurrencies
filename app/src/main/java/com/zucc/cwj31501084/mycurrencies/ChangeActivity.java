package com.zucc.cwj31501084.mycurrencies;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class ChangeActivity extends Activity {
    private Button chart;
    private RateDatabaseManager dbManager;
    private ListView listview;
    private List<BeanRate> datas = new ArrayList<>();
    private RateAdapter adapter;
    private String[] mCurrencies;
    ArrayList<Integer> id = new ArrayList<Integer>();
    ArrayList<String> date = new ArrayList<String>();
    ArrayList<Float> rate = new ArrayList<Float>();
    Handler myhandler = new Handler();

    private String mKey;
    public static final String RATES = "rates";
    public static final String URL_BASE =
            "http://openexchangerates.org/api/latest.json?app_id=";
    private static final DecimalFormat DECIMAL_FORMAT = new
            DecimalFormat("#,##0.00000");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dbManager = new RateDatabaseManager(getBaseContext());
        setContentView(R.layout.activity_change);
        initDatas();

        adapter = new RateAdapter(this, R.layout.rate_list, datas);
        adapter.notifyDataSetChanged();//数据动态更新
        listview = (ListView) findViewById(R.id.listview2);
        listview.setAdapter(adapter);
        listview.setTextFilterEnabled(true);
        ArrayList<String> arrayList = ((ArrayList<String>)
                getIntent().getSerializableExtra(MainActivity.KEY_LIST));
        mCurrencies = arrayList.toArray(new String[arrayList.size()]);
        Log.i("111111", mCurrencies.toString());

        chart = (Button) findViewById(R.id.chart);

        chart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChangeActivity.this, ChartActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initDatas() {
        datas = dbManager.queryAllContent();
        for (BeanRate d : datas) {
            if (d != null) {
                id.add(d.getId());
                date.add(d.getDate());
                rate.add(d.getRate());
            }
        }
    }

    private void loadAllRate() {

    }

}
