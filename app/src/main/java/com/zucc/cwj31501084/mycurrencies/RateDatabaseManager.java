package com.zucc.cwj31501084.mycurrencies;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

/**
 * Created by chenwenjie on 2018/7/19.
 */

public class RateDatabaseManager {
    private RateDatabaseHelper helper;
    private SQLiteDatabase db;
    public RateDatabaseManager(Context context) {
        helper = new RateDatabaseHelper(context);
        //因为getWritableDatabase内部调用了mContext.openOrCreateDatabase(mName, 0, mFactory);
        //所以要确保context已初始化,我们可以把实例化DBManager的步骤放在Activity的onCreate里
        db = helper.getWritableDatabase();
    }
    public void addData(BeanRate data){
        addContent(data);
    }
    private void addContent(BeanRate data) {
        ContentValues values = new ContentValues();
        values.put("id", data.getId());
        values.put("date", data.getDate());
        values.put("rate", data.getRate());
        db.insert("rate", null, values);
    }
    public ArrayList<BeanRate> queryAllContent() {
        ArrayList<BeanRate> datas = new ArrayList<>();
        Cursor c = db.query("rate", null, null, null, null, null, null);
        while (c.moveToNext()) {

            BeanRate data = null;
            //填充数据信息

            int id = c.getInt(c.getColumnIndex("id"));
            String date = c.getString(c.getColumnIndex("date"));
            float rate = c.getFloat(c.getColumnIndex("rate"));
            data = new BeanRate(id, date, rate);
            datas.add(data);

        }
        c.close();
        return datas;
    }
}
