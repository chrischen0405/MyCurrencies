package com.zucc.cwj31501084.mycurrencies;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by chenwenjie on 2018/7/18.
 */

public class MyDatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "currenciesdb.db";
    private static final int DATABASE_VERSION = 1;

    public MyDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //创建表
        db.execSQL("CREATE TABLE IF NOT EXISTS record" +
                "(id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "forCode TEXT, " +
                "forAmount TEXT," +
                "homCode TEXT," +
                "homAmount TEXT," +
                "time TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // db.execSQL("ALTER TABLE note ADD COLUMN other STRING");
        db.execSQL("drop table if exists record");
    }
}
