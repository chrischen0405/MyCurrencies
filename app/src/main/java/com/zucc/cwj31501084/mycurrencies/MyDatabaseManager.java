package com.zucc.cwj31501084.mycurrencies;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

/**
 * Created by chenwenjie on 2018/7/18.
 */

public class MyDatabaseManager {
    private MyDatabaseHelper helper;
    private SQLiteDatabase db;

    public MyDatabaseManager(Context context) {
        helper = new MyDatabaseHelper(context);
        //因为getWritableDatabase内部调用了mContext.openOrCreateDatabase(mName, 0, mFactory);
        //所以要确保context已初始化,我们可以把实例化DBManager的步骤放在Activity的onCreate里
        db = helper.getWritableDatabase();
    }

    public void addData(BeanRecord data) {
        addContent(data);
    }

    public void addData(BeanAllRate data) {
        addContent(data);
    }

    private void addContent(BeanRecord data) {
        ContentValues values = new ContentValues();
        values.put("homCode", data.getHomCode());
        values.put("homAmount", data.getHomAmount());
        values.put("forCode", data.getForCode());
        values.put("forAmount", data.getForAmount());
        values.put("time", data.getTime());
        db.insert("record", null, values);
    }

    private void addContent(BeanAllRate data) {
        ContentValues values = new ContentValues();
        values.put("ratetime", data.getRatetime());
        values.put("allrate", data.getAllrate());
        db.insert("allrate", null, values);
    }

    public ArrayList<BeanRecord> queryAllContent() {
        ArrayList<BeanRecord> datas = new ArrayList<>();
        Cursor c = db.query("record", null, null, null, null, null, null);
        while (c.moveToNext()) {

            BeanRecord data = null;
            //填充数据信息

            String forCode = c.getString(c.getColumnIndex("forCode"));
            String forAmount = c.getString(c.getColumnIndex("forAmount"));
            String homCode = c.getString(c.getColumnIndex("homCode"));
            String homAmount = c.getString(c.getColumnIndex("homAmount"));
            String time = c.getString(c.getColumnIndex("time"));
            data = new BeanRecord(forCode, forAmount, homCode, homAmount, time);
            datas.add(data);

        }
        c.close();
        return datas;
    }

    public ArrayList<BeanAllRate> queryAllRate() {
        ArrayList<BeanAllRate> datas = new ArrayList<>();
        Cursor c = db.query("allrate", null, null, null, null, null, null);
        while (c.moveToNext()) {
            BeanAllRate data = null;

            long ratetime = c.getLong(c.getColumnIndex("ratetime"));
            String allrate = c.getString(c.getColumnIndex("allrate"));
            data = new BeanAllRate(ratetime, allrate);
            datas.add(data);

        }
        c.close();
        return datas;
    }

    public Boolean queryJson(long ratetime) {
        ArrayList<BeanAllRate> datas = new ArrayList<>();
        Cursor c = db.rawQuery("select * from allrate where ratetime = " + ratetime, null);
        if (c.moveToNext())
            return true;
        else
            return false;
    }

    public void deleteRecord(String time) {
        db.execSQL("DELETE FROM record WHERE time = '" + time + "'");
    }
}
