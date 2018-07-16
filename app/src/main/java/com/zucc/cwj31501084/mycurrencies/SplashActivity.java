package com.zucc.cwj31501084.mycurrencies;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Window;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;

public class SplashActivity extends Activity {
    //url to currency codes used in this application
    public static final String URL_CODES =
            "http://openexchangerates.org/api/currencies.json";
    //ArrayList of currencies that will be fetched and passed into MainActivity
    private ArrayList<String> mCurrencies;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_splash);
        new FetchCodesTask().execute(URL_CODES);
    }

    private class FetchCodesTask extends AsyncTask<String, Void, JSONObject> {
        @Override
        protected void onPostExecute(JSONObject jsonObject) {
            try {
                if (jsonObject == null) {
                    throw new JSONException("no data available.");
                }
                Iterator iterator = jsonObject.keys();
                String key = "";
                mCurrencies = new ArrayList<String>();
                while (iterator.hasNext()) {
                    key = (String) iterator.next();
                    mCurrencies.add(key + " | " + jsonObject.getString(key));
                }
                finish();
            } catch (JSONException e) {
                Toast. makeText(
                        SplashActivity. this,
                        "There's been a JSON exception: " + e.getMessage(),
                        Toast. LENGTH_LONG
                ).show();
                e.printStackTrace();
                finish();
            }
        }

        @Override
        protected JSONObject doInBackground(String... strings) {
            return new JSONParser().getJSONFromUrl(strings[0]);
        }
    }
}
