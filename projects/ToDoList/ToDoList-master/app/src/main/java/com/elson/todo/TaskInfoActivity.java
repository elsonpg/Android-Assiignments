package com.elson.todo;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.elson.todo.DB.DB;
import com.elson.todo.model.Task;

import java.text.ParseException;
import java.text.SimpleDateFormat;


public class TaskInfoActivity extends ActionBarActivity {
    int chosenItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_info);
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        TextView textView = (TextView)findViewById(R.id.textView);
        Button buttonEdit = (Button)findViewById(R.id.editButton);
        Button buttonDelete = (Button) findViewById(R.id.deleteButton);
        chosenItem = getIntent().getExtras().getInt("chosenItem");
        DB db = new DB();
        try {
            Task task = db.selectTaskById(chosenItem, this.getApplicationContext());
            textView.setText("Task Name:\n"+task.getTaskTitle()+"\n\nDate:\n"+sdf.format(task.getDate().getTime()).toString());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        View.OnClickListener oclEdit = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),AddTaskActivity.class);
                intent.putExtra("chosenItem", chosenItem);
                startActivity(intent);
            }
        };
        View.OnClickListener oclDelete = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DB db = new DB();
                try {
                    db.deleteTask(chosenItem, getApplicationContext());
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        };
        buttonEdit.setOnClickListener(oclEdit);
        buttonDelete.setOnClickListener(oclDelete);
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_task_info, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
