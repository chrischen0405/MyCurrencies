package com.zucc.cwj31501084.mycurrencies;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by chenwenjie on 2018/7/16.
 */

public class PrefsMgr {
    private static SharedPreferences sSharedPreferences;

    public static void setString(Context context, String locale, String
            code) {
        sSharedPreferences =
                PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sSharedPreferences.edit();
        editor.putString(locale, code);
        editor.commit();
    }

    public static String getString(Context context, String locale) {
        sSharedPreferences =
                PreferenceManager.getDefaultSharedPreferences(context);
        return sSharedPreferences.getString(locale, null);
    }
}
