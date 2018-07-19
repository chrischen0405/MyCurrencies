package com.zucc.cwj31501084.mycurrencies;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by chenwenjie on 2018/7/19.
 */

public class RateAdapter extends ArrayAdapter<BeanRate> {
    private int resourceid;

    public RateAdapter(Context context, int id, List<BeanRate> objects) {
        super(context, id, objects);
        resourceid = id;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        BeanRate data = getItem(position);
        View view;
        if (convertView == null) {
            view = LayoutInflater.from(getContext()).inflate(resourceid, null);
        } else {
            view = convertView;
        }
        TextView id = (TextView) view.findViewById(R.id.id);
        TextView date = (TextView) view.findViewById(R.id.date);
        TextView rate = (TextView) view.findViewById(R.id.rate);
        id.setText(data.getId() + "");
        date.setText(data.getDate());
        rate.setText(data.getRate() + "");
        return view;
    }
}
