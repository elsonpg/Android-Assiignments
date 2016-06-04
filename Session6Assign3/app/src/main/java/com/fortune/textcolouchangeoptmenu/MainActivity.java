package com.fortune.textcolouchangeoptmenu;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item){
        TextView txt=(TextView) findViewById(R.id.textView);
        //View someView = findViewById(R.id.screen);
        //View root = someView.getRootView();
        switch(item.getItemId())
        {
            case R.id.menu11:
                txt.setTextColor(new Color().parseColor("#FF4081"));
                //root.setBackgroundColor(Color.parseColor("#FFDFDB1A"));
                return true;
            case R.id.menu12:
                txt.setTextColor(new Color().parseColor("#FF2AF031"));
                //root.setBackgroundColor(Color.parseColor("#FF4081"));
                return true;
            case R.id.menu13:
                txt.setTextColor(new Color().parseColor("#FFDFDB1A"));
                //root.setBackgroundColor(Color.parseColor("#FF2AF031"));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

}
