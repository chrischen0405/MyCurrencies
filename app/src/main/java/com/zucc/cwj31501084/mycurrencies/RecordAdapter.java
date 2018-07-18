package com.zucc.cwj31501084.mycurrencies;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by chenwenjie on 2018/7/18.
 */

public class RecordAdapter extends ArrayAdapter<BeanRecord> {
    private int resourceid;

    public RecordAdapter(Context context, int id, List<BeanRecord> objects) {
        super(context, id, objects);
        resourceid = id;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        BeanRecord data = getItem(position);
        View view;
        if (convertView == null) {
            view = LayoutInflater.from(getContext()).inflate(resourceid, null);
        } else {
            view = convertView;
        }
        TextView forCode = (TextView) view.findViewById(R.id.forCode);
        TextView forAmount = (TextView) view.findViewById(R.id.forAmount);
        TextView homCode = (TextView) view.findViewById(R.id.homCode);
        TextView homAmount = (TextView) view.findViewById(R.id.homAmount);
        TextView time = (TextView) view.findViewById(R.id.time);
        forCode.setText(data.getForCode());
        forAmount.setText(data.getForAmount());
        homCode.setText(data.getHomCode());
        homAmount.setText(data.getHomAmount());
        time.setText(data.getTime());
        return view;
    }
}
