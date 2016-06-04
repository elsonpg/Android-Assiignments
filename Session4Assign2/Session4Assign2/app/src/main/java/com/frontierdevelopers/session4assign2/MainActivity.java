package com.frontierdevelopers.session4assign2;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends Activity
{
    /** Called when the activity is first created. */

    ListView lview;
    ListViewAdapter lviewAdapter;

    private final static String name[] = {"A","B","C"};

    private final static String number[] = {"1213", "2213","3112"};

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lview = (ListView) findViewById(R.id.listView1);
        lviewAdapter = new ListViewAdapter(this, name, number);

        //System.out.println("adapter => "+lviewAdapter.getCount());

        lview.setAdapter(lviewAdapter);

        //lview.setOnItemClickListener(this);
    }

   // public void onItemClick(AdapterView<?> arg0, View arg1, int position, long id) {
        // TODO Auto-generated method stub
     //   Toast.makeText(this,"Title => "+name[position]+"=> n Description"+number[position], Toast.LENGTH_SHORT).show();
    //}
}