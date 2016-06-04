package com.elson.todo;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.elson.todo.DB.DB;
import com.elson.todo.fragment.Fragments;
import com.elson.todo.model.Task;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class AddTaskActivity extends ActionBarActivity {
    Toolbar toolbar;
    EditText dateTitleET;
    EditText taskTitleET;
    int chosenItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        dateTitleET = (EditText)findViewById(R.id.date_title);
        taskTitleET = (EditText)findViewById(R.id.task_title);
        setSupportActionBar(toolbar);
        DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        if(getIntent().getExtras()!=null)
        {
            chosenItem = getIntent().getExtras().getInt("chosenItem");
            DB db = new DB();
            try {
                Task task = db.selectTaskById(chosenItem, this.getApplicationContext());
                taskTitleET.setText(task.getTaskTitle());
                dateTitleET.setText(dateFormat.format(task.getDate()), TextView.BufferType.EDITABLE);
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void onPostCreate(Bundle bundle)
    {
        super.onPostCreate(bundle);
        if(getIntent().getExtras()!=null)
        {
            setTitle("Edit Task");
        }
        else
            setTitle("New Task");

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_accept, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public void showDatePickerDialog(View v) {
        DialogFragment newFragment = new Fragments.DatePickerFragment();
        newFragment.show(getFragmentManager(), "datePicker");
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        EditText task_titleET = (EditText)findViewById(R.id.task_title);
        EditText dateTitleET = (EditText)findViewById(R.id.date_title);
        if (task_titleET.getText().toString().equals("") ||
                dateTitleET.getText().toString().equals("")) {
            Toast.makeText(this, "Please fill out task", Toast.LENGTH_LONG).show();
            return super.onOptionsItemSelected(item);
        }
        Calendar task_date = null;
        DB db = new DB();
        try {
            task_date = db.stringToCalendar(dateTitleET.getText().toString());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Task task;
        switch (item.getItemId())
        {
            case R.id.accept:
                if (chosenItem != 0)
                    try {
                        task = new Task(chosenItem,task_titleET.getText().toString(), task_date);
                        db.editTask(task, this);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                else {
                    task = new Task(0,task_titleET.getText().toString(), task_date);
                    db.insertTask(task, this);
                }
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void setTitle(CharSequence title) {
        getSupportActionBar().setTitle(title);
    }


}
