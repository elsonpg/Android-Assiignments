package com.frontierdevelopers.session4assign1;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

public class MainActivity extends Activity implements OnClickListener {


    ListView list;
    ArrayAdapter<String> adapter;

    Button ASCEN;
    Button DESCE;

    String[] months={"Januvary","Februvary","March","April","May"};



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        list=(ListView) findViewById(R.id.listView);

        ASCEN= (Button)findViewById(R.id.ASC);
        DESCE= (Button)findViewById(R.id.DESC);

        adapter=new ArrayAdapter<String>(this,android.R.layout.simple_dropdown_item_1line,months);


        list.setAdapter(adapter);

        ASCEN.setOnClickListener(this);
        DESCE.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub

        if (v == ASCEN) {
            Arrays.sort(months);
            adapter.notifyDataSetChanged();

        }

        if (v == DESCE) {
            Arrays.sort(months);
            List<String> list = Arrays.asList(months);
            Collections.reverse(list);
            adapter.notifyDataSetChanged();

        }

    }
}
