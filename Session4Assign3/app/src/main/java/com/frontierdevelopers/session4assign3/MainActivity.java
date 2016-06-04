package com.frontierdevelopers.session4assign3;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GridView gridview= (GridView)findViewById(R.id.grid_view);
        gridview.setAdapter(new ImageAdapter(this));

        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                Toast.makeText(MainActivity.this, "" + position,
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

            }
        /*After the activity_main.xml layout is set for the content view, the GridView is captured from the
        layout with findViewById(int). The setAdapter() method then sets a custom adapter (ImageAdapter)
        as the source for all items to be displayed in the grid. The ImageAdapter is created in the next step.

         To do something when an item in the grid is clicked, the setOnItemClickListener() method
         is passed a new AdapterView.OnItemClickListener. This anonymous instance defines the
         onItemClick() callback method to show a Toast that displays the index position (zero-based)
         of the selected item (in a real world scenario, the position could be used to get the full sized
          image for some other task).*/



