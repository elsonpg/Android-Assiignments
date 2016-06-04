package com.frontierdevelopers.session6assign4;

import android.app.Activity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import java.util.ArrayList;

public class MainActivity extends Activity {

    private static final int MENU_ID_01 = 100;
    private static final int MENU_ID_02 = 101;


    String[] names = new String[]{"Dipti","Shiva","Venkat"};
    String [] number = new String[]{"9847820441","9847820442","9847820443"};

    ListView listView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

             ViewAdapter adapter=new ViewAdapter(MainActivity.this, names, number);

        listView = (ListView)findViewById(R.id.listView);


        listView.setAdapter(adapter);

        // Register the ListView  for Context menu
        registerForContextMenu(listView);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo)
    {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.setHeaderTitle("Action");
        menu.add(0, MENU_ID_01, 2, "Send SMS");//groupId, itemId, order, title
        menu.add(0, MENU_ID_02, 1, "Call");
    }

    @Override
    public boolean onContextItemSelected(MenuItem item){
        if(item.getItemId()==MENU_ID_01 && item.getGroupId()==0){
            Toast.makeText(getApplicationContext(),"Clicked on " +item.getGroupId()+"..."+item.getItemId(),Toast.LENGTH_LONG).show();
        }
        else if(item.getItemId()==MENU_ID_02 && item.getGroupId()==0){
            Toast.makeText(getApplicationContext(),"Clicked on " +item.getGroupId()+"..."+item.getItemId(),Toast.LENGTH_LONG).show();
        }
        else{
            return false;
        }
        return true;
    }
}