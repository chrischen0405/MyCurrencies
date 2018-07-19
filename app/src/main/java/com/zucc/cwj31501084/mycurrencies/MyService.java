package com.zucc.cwj31501084.mycurrencies;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.SystemClock;
import android.util.Log;

import java.util.Date;

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
        int anHour = 10 * 1000;
        //触发时间
        long triggerAtTime = SystemClock.elapsedRealtime() + anHour;
        Intent i = new Intent(this, AlarmReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, i, 0);
        //ELAPSED_REALTIME_WAKEUP表示定时任务的触发时间从系统开机开始算起，但会唤醒CPU。在10秒钟后就会执行AlarmReceiver中的onReceive方法
        manager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, triggerAtTime, pendingIntent);

        return super.onStartCommand(intent, flags, startId);
    }
}
