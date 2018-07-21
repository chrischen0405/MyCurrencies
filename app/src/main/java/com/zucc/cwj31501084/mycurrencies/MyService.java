package com.zucc.cwj31501084.mycurrencies;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.content.res.AssetManager;
import android.os.AsyncTask;
import android.os.IBinder;
import android.os.SystemClock;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;

import static com.zucc.cwj31501084.mycurrencies.MainActivity.RATES;
import static com.zucc.cwj31501084.mycurrencies.MainActivity.TIMESTAMP;
import static com.zucc.cwj31501084.mycurrencies.MainActivity.URL_BASE;

public class MyService extends Service {

    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                //调用java.util.Data中的方法输出时间
                Log.d("MyService", "executed at" + new Date().toString());
            }
        }).start();
        //获取AlarmManager 实例
        AlarmManager manager = (AlarmManager) getSystemService(ALARM_SERVICE);
        new UpdateRateTask().execute(URL_BASE + getKey());
        int anHour = 60 * 60 * 1000;
        //触发时间
        long triggerAtTime = SystemClock.elapsedRealtime() + anHour;
        Intent i = new Intent(this, AlarmReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, i, 0);
        //ELAPSED_REALTIME_WAKEUP表示定时任务的触发时间从系统开机开始算起，但会唤醒CPU。在10秒钟后就会执行AlarmReceiver中的onReceive方法
        manager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, triggerAtTime, pendingIntent);

        return super.onStartCommand(intent, flags, startId);
    }

    private String getKey() {
        AssetManager assetManager = this.getResources().getAssets();
        Properties properties = new Properties();
        try {
            InputStream inputStream = assetManager.open("keys.properties");
            properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties.getProperty("open_key");
    }

    private class UpdateRateTask extends AsyncTask<String, Void, JSONObject> {
        private long ratetime;
        private String allrate;


        @Override
        protected void onPostExecute(JSONObject jsonObject) {
            try {

                if (jsonObject == null) {
                    throw new JSONException("no data available.");
                }
                JSONObject jsonRates = jsonObject.getJSONObject(RATES);
                allrate = jsonRates.toString();

                Log.i("jsonrates", jsonRates.toString());
                ratetime = jsonObject.getLong(TIMESTAMP);
                Log.i("jsontime", ratetime + "");
            } catch (JSONException e) {
                Log.i("exception: ", e.getMessage());
                e.printStackTrace();
            }

            BeanAllRate data = new BeanAllRate();
            int flag = 0;
            data.setAllrate(allrate);
            data.setRatetime(ratetime);
            MyDatabaseManager dbManager = new MyDatabaseManager(getBaseContext());
            ArrayList<BeanAllRate> all = dbManager.queryAllRate();
            for (BeanAllRate d : all) {
                if (d.getRatetime() == ratetime)
                    flag = 1;
            }
            if (flag == 0)
                dbManager.addData(data);

        }

        @Override
        protected JSONObject doInBackground(String... strings) {
            return new JSONParser().getJSONFromUrl(strings[0]);
        }
    }

}
