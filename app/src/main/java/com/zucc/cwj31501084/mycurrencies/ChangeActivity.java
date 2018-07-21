package com.zucc.cwj31501084.mycurrencies;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ChangeActivity extends Activity {
    private Button chart;
    private MyDatabaseManager dbManager;
    private ListView listview;
    private List<BeanRate> datas = new ArrayList<>();
    private RateAdapter adapter;
    ArrayList<String> country = new ArrayList<String>();
    ArrayList<Float> countryrate = new ArrayList<Float>();
    Handler myhandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dbManager = new MyDatabaseManager(getBaseContext());
        setContentView(R.layout.activity_change);
        initDatas();
        datas = ((ArrayList<BeanRate>)
                getIntent().getSerializableExtra(MainActivity.KEY_LIST));

        adapter = new RateAdapter(this, R.layout.rate_list, datas);
        adapter.notifyDataSetChanged();//数据动态更新
        listview = (ListView) findViewById(R.id.listview2);
        listview.setAdapter(adapter);
        listview.setTextFilterEnabled(true);
        Log.i("datassize", datas.size() + "");

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
        for (BeanRate d : datas) {
            if (d != null) {
                country.add(d.getCountry());
                countryrate.add(d.getCountryrate());
            }
        }
    }

    private void loadAllRate() {

    }

}
