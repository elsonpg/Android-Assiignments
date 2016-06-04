package com.frontierdevelopers.session4assign4;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

public class MainActivity extends Activity {
    GridView grid;
    String[] web = {
            "Cupcake",
            "Donut",
            "Ecliar",
            "Froyo",
            "Gingerbread",
            "Honeycomb",
            "Icecream Sanwich",
            "Jelly Bean",
            "KitKat"
    } ;

    int[] imageId = {
            R.drawable.drawable1,
            R.drawable.drawable2,
            R.drawable.drawable3,
            R.drawable.drawable4,
            R.drawable.drawable5,
            R.drawable.drawable6,
            R.drawable.drawable7,
            R.drawable.drawable8,
            R.drawable.drawable9
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        CustomGrid adapter = new CustomGrid(MainActivity.this, web, imageId);
        grid=(GridView)findViewById(R.id.grid);
        grid.setAdapter(adapter);
        grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Toast.makeText(MainActivity.this, "You Clicked at " +web[+ position], Toast.LENGTH_SHORT).show();

            }
        });

    }



}
