package com.zucc.cwj31501084.mycurrencies;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;


public class SplashActivity extends Activity {

    //url to currency codes used in this application
    public static final String URL_CODES = "http://openexchangerates.org/api/currencies.json";
    public static final String KEY_ARRAYLIST = "key_arraylist";
    public static final String KEY_COUNTRYLIST = "key_countrylist";
    //ArrayList of currencies that will be fetched and passed into MainActivity
    private ArrayList<String> mCurrencies;
    private ArrayList<String> mCountry;

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
                Log.i("aaaaaaaaaaaaaa111  ", jsonObject.toString());
                if (jsonObject == null) {
                    throw new JSONException("no data available.");
                }
                Iterator iterator = jsonObject.keys();
                String key = "";
                mCurrencies = new ArrayList<String>();
                mCountry = new ArrayList<>();
                while (iterator.hasNext()) {
                    key = (String) iterator.next();
                    mCurrencies.add(key + " | " + jsonObject.getString(key));
                    mCountry.add(key);
                }
                Intent mainIntent = new Intent(SplashActivity.this, MainActivity.class);
                mainIntent.putExtra(KEY_ARRAYLIST, mCurrencies);
                mainIntent.putExtra(KEY_COUNTRYLIST,mCountry);
                startActivity(mainIntent);

                finish();
            } catch (JSONException e) {
                Toast.makeText(
                        SplashActivity.this,
                        "There's been a JSON exception: " + e.getMessage(),
                        Toast.LENGTH_LONG
                ).show();
                e.printStackTrace();
                finish();
            }

        }

        @Override
        protected JSONObject doInBackground(String... params) {
            return new JSONParser().getJSONFromUrl(params[0]);
        }
    }


}
