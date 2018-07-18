package com.zucc.cwj31501084.mycurrencies;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ChangeActivity extends AppCompatActivity {
    private Button chart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change);
        chart = (Button) findViewById(R.id.chart);

        chart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChangeActivity.this,ChartActivity.class);
                startActivity(intent);
            }
        });
    }

}
