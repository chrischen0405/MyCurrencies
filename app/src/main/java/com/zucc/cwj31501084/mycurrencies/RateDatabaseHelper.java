package com.zucc.cwj31501084.mycurrencies;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by chenwenjie on 2018/7/19.
 */

public class RateDatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "ratedb.db";
    private static final int DATABASE_VERSION = 1;
    public RateDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS rate" +
                "(id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "date TEXT, " +
                "rate FLOAT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists rate");
    }
}
