package com.elson.todo.DB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import com.elson.todo.AddTaskActivity;
import com.elson.todo.model.Task;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class DB {
    DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");

    public void insertTask(Task task, Context context)
    {
        String tag = "Insert task";
        DBHelper dbHelper = new DBHelper(context);
        ContentValues cv = new ContentValues();
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Log.d("Insert task", "Tasks:");
        cv.put("title", task.getTaskTitle());
        cv.put("task_date", dateFormat.format(task.getDate().getTime()));
        long rowID = db.insert("tasks", null, cv);
        Log.d(tag, "row inserted, ID = " + rowID);
        dbHelper.close();

    }

    public List<Task> selectAllTasks(Context context) throws ParseException {
        DBHelper dbHelper = new DBHelper(context);
        List<Task> taskList = new ArrayList<Task>();
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor c = db.query("tasks", null, null, null, null, null, null);
        if (c.moveToFirst()) {
            int idColIndex = c.getColumnIndex("id");
            int titleColIndex = c.getColumnIndex("title");
            int taskDateColIndex = c.getColumnIndex("task_date");
            do {
                Task task = new Task(c.getInt(idColIndex), c.getString(titleColIndex), this.stringToCalendar(c.getString(taskDateColIndex)));
                taskList.add(task);
            } while (c.moveToNext());
        }
        c.close();
        dbHelper.close();
        return taskList;
    }


    public Task selectTaskById(int id, Context context) throws ParseException {
        DBHelper dbHelper = new DBHelper(context);

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor c = db.query("tasks", null, null, null, null, null, null);
        if (c.moveToFirst()) {
            int idColIndex = c.getColumnIndex("id");
            int titleColIndex = c.getColumnIndex("title");
            int taskDateColIndex = c.getColumnIndex("task_date");
            do {
                Task task = new Task(c.getInt(idColIndex), c.getString(titleColIndex), this.stringToCalendar(c.getString(taskDateColIndex)));
                if (task.getId()==id)
                    return task;
            } while (c.moveToNext());
        } else
        c.close();
        dbHelper.close();
        return null;
    }

    public void editTask(Task task, Context context) throws ParseException {
        ContentValues cv = new ContentValues();
        DBHelper dbHelper = new DBHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
                cv.put("title", task.getTaskTitle());
                cv.put("task_date", dateFormat.format(task.getDate().getTime()));
                db.update("tasks", cv , "id = "+task.getId(),null);
        dbHelper.close();
    }

    public void deleteTask(int id, Context context) throws ParseException {
        DBHelper dbHelper = new DBHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor c = db.query("tasks", null, null, null, null, null, null);
        if (c.moveToFirst()) {
            do {
                db.delete("tasks","id="+id,null);
            } while (c.moveToNext());
        }
        c.close();
        dbHelper.close();
    }

    public static class DBHelper extends SQLiteOpenHelper {

        public DBHelper(Context context) {
            super(context, "tasks", null, 1);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL("create table tasks ("
                    + "id integer primary key autoincrement,"
                    + "title text,"
                    + "task_date date" + ");");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        }
    }

    public Calendar stringToCalendar(String strDate) throws ParseException {
        String FORMAT_DATETIME = "dd.MM.yyyy";
        SimpleDateFormat sdf = new SimpleDateFormat(FORMAT_DATETIME);
        Date date = sdf.parse(strDate);
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal;
    }


}
