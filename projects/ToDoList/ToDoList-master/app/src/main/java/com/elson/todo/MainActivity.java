package com.elson.todo;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.content.res.Configuration;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.*;
import android.widget.*;
import com.elson.todo.DB.DB;
import com.elson.todo.model.Task;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


public class MainActivity extends ActionBarActivity {
    private DrawerLayout drawerLayout;
    private ListView listView;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private Toolbar toolbar;

    private String[] navigationDrawerItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        navigationDrawerItems = getResources().getStringArray(R.array.navigation_drawer_items);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        listView = (ListView) findViewById(R.id.left_drawer);

        drawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);
        listView.setAdapter(new ArrayAdapter<String>(this, R.layout.drawer_list_item, navigationDrawerItems));
        listView.setOnItemClickListener(new DrawerItemClickListener());

        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.app_name, R.string.app_name);
        drawerLayout.setDrawerListener(actionBarDrawerToggle);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);


        if (savedInstanceState == null) {
            selectItem(0);
        }

    }

    private class DrawerItemClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            selectItem(position);
        }
    }

    private void selectItem(int position) {
        Fragment fragment = new DummyFragment();
        Bundle args = new Bundle();
        args.putInt(DummyFragment.ARG_MENU_INDEX, position);
        fragment.setArguments(args);
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();
        listView.setItemChecked(position, true);
        setTitle(navigationDrawerItems[position]);
        drawerLayout.closeDrawer(listView);
    }

    @Override
    public void setTitle(CharSequence title) {
        getSupportActionBar().setTitle(title);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        actionBarDrawerToggle.syncState();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case R.id.add:
                Intent intent = new Intent(this, AddTaskActivity.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        actionBarDrawerToggle.onConfigurationChanged(newConfig);
    }

    public static class DummyFragment extends Fragment {
        public static final String ARG_MENU_INDEX = "index";
        final int MENU_ITEM_ID_EDIT = 1;
        final int MENU_ITEM_ID_DELETE = 2;
        private int chosenItem;
        public DummyFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

            View rootView = inflater.inflate(R.layout.dummy_fragment, container, false);
            int index = getArguments().getInt(ARG_MENU_INDEX);
            DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
            DB db = new DB();
            List<Task> taskList = new ArrayList<Task>();
            List<String> values = new ArrayList<String>();
            final List<Integer> taskIds = new ArrayList<Integer>();
            try {
                taskList = db.selectAllTasks(getActivity().getApplicationContext());
            } catch (ParseException e) {
                e.printStackTrace();
            }
            final ListView listView = (ListView)rootView.findViewById(R.id.dummy_list);

            switch (index)
            {
                case 0:
                        for (Task i:taskList)
                        {
                            values.add(dateFormat.format(i.getDate().getTime())+": "+ i.getTaskTitle());
                            taskIds.add(i.getId());
                        }
                    ;
                    break;
                case 1:
                    for (Task i:taskList)
                    {
                        if (dateFormat.format(i.getDate().getTime()).equals(dateFormat.format(Calendar.getInstance().getTime())))
                        values.add(dateFormat.format(i.getDate().getTime())+": "+ i.getTaskTitle());
                        taskIds.add(i.getId());
                    }
                    break;
                case 2:
                    for (Task i:taskList)
                    {
                        Date tomorrowDate = new Date();
                        Calendar c = Calendar.getInstance();
                        c.setTime(tomorrowDate);
                        c.add(Calendar.DATE, 1);
                        tomorrowDate = c.getTime();
                        if (dateFormat.format(i.getDate().getTime()).equals(dateFormat.format(tomorrowDate)))
                            values.add(dateFormat.format(i.getDate().getTime())+": "+ i.getTaskTitle());
                        taskIds.add(i.getId());
                    }
                    break;
                default:
                    break;
            }
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity().getApplicationContext(),
                    R.layout.list_item, values);
            listView.setAdapter(adapter);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Log.d("Tap on", String.valueOf(taskIds.get(((int) id))));
                    chosenItem = taskIds.get(((int) id));
                    Intent intent = new Intent(getActivity().getApplicationContext(),TaskInfoActivity.class);
                    intent.putExtra("chosenItem", chosenItem);
                    startActivity(intent);
                }
            });

            return rootView;
        }

    }



}