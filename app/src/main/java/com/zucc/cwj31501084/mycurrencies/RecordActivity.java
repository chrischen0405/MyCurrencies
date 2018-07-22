package com.zucc.cwj31501084.mycurrencies;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chenwenjie on 2018/7/17.
 */

public class RecordActivity extends Activity implements AdapterView.OnItemClickListener {
    private MyDatabaseManager dbManager;
    private ListView listview;
    private List<BeanRecord> datas = new ArrayList<>();
    EditText edtSearch;
    ImageView ivDeleteText;
    private RecordAdapter adapter;
    ArrayList<String> forCode = new ArrayList<String>();
    ArrayList<String> forAmount = new ArrayList<String>();
    ArrayList<String> homCode = new ArrayList<String>();
    ArrayList<String> homAmount = new ArrayList<String>();
    ArrayList<String> time = new ArrayList<String>();
    Handler myhandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dbManager = new MyDatabaseManager(getBaseContext());
        setContentView(R.layout.activity_record);
        initDatas();
        set_edtSearch_TextChanged();//设置edtSearch搜索框的文本改变时监听器

        set_ivDeleteText_OnClick();//设置叉叉的监听器

        adapter = new RecordAdapter(this, R.layout.record_list, datas);
        adapter.notifyDataSetChanged();//数据动态更新
        listview = (ListView) findViewById(R.id.listview);
        listview.setAdapter(adapter);
        listview.setTextFilterEnabled(true);
        listview.setOnItemClickListener(this);

    }

    private void set_edtSearch_TextChanged() {
        edtSearch = (EditText) findViewById(R.id.etSearch);

        edtSearch.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
                // TODO Auto-generated method stub
                //这个应该是在改变的时候会做的动作，具体还没用到过。
            }

            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
                                          int arg3) {
                // TODO Auto-generated method stub
                //这是文本框改变之前会执行的动作
            }

            @Override
            public void afterTextChanged(Editable s) {
                // TODO Auto-generated method stub
                /**这是文本框改变之后 会执行的动作
                 * 要做的就是，在文本框改变的同时，listview的数据也进行相应的变动，并且如一的显示在界面上
                 * 所以这里就需要加上数据的修改的动作
                 */
                if (s.length() == 0) {
                    ivDeleteText.setVisibility(View.GONE);//当文本框为空时，则叉叉消失
                } else {
                    ivDeleteText.setVisibility(View.VISIBLE);//当文本框不为空时，出现叉叉
                }

                myhandler.post(new Runnable() {
                    @Override
                    public void run() {
                        /**
                         *界面UI的更新，使用Handler，通过Post一个Runnable去更新，Runnable会去根据搜索框的文本对datas里的数据进行更新。
                         */
                        // TODO Auto-generated method stub
                        String data = edtSearch.getText().toString();

                        datas.clear();//先要清空，不然会叠加

                        getmDataSub(datas, data);//获取更新数据

                        adapter.notifyDataSetChanged();//更新
                    }
                });
            }
        });

    }

    /**
     * 获得根据搜索框的数据data来从元数据筛选，筛选出来的数据放入datas里
     */

    private void getmDataSub(List<BeanRecord> datas, String data) {
        int length = homCode.size();
        for (int i = 0; i < length; ++i) {
            if (time.get(i).contains(data) || forCode.get(i).contains(data) || forAmount.get(i).contains(data) || homCode.get(i).contains(data) || homAmount.get(i).contains(data)) {
                BeanRecord item = new BeanRecord();
                item.setTime(time.get(i));
                item.setForCode(forCode.get(i));
                item.setForAmount(forAmount.get(i));
                item.setHomCode(homCode.get(i));
                item.setHomAmount(homAmount.get(i));
                datas.add(item);
            }
        }
    }


    /**
     * 设置叉叉的点击事件，即清空功能
     */

    private void set_ivDeleteText_OnClick() {
        ivDeleteText = (ImageView) findViewById(R.id.ivDeleteText);
        ivDeleteText.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                edtSearch.setText("");
            }
        });
    }


    private void initDatas() {
        datas = dbManager.queryAllContent();
        for (BeanRecord d : datas) {
            if (d != null) {
                time.add(d.getTime());
                forCode.add(d.getForCode());
                forAmount.add(d.getForAmount());
                homCode.add(d.getHomCode());
                homAmount.add(d.getHomAmount());
            }
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String text = (String) ((TextView) view.findViewById(R.id.time)).getText();
        //大多数情况下，position和id相同，并且都从0开始
        showDialog(text);
        String showText = "点击第" + position + "项，文本内容为：" + text + "，ID为：" + id;

        Toast.makeText(this, showText, Toast.LENGTH_LONG).show();
    }

    private void showDialog(final String time) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("删除");
        builder.setMessage("是否删除" + time + "的记录");
        builder.setPositiveButton("确定",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        MyDatabaseManager myDatabaseManager = new MyDatabaseManager(getApplicationContext());
                        myDatabaseManager.deleteRecord(time);
                        refresh();

                    }
                });
        builder.setNegativeButton("取消",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        dialog.dismiss();
                    }
                });
        AlertDialog dialog = builder.create();
        dialog.show();

    }

    private void refresh() {
        onCreate(null);
    }
}
