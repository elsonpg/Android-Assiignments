package com.elson.todo.fragment;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import com.elson.todo.AddTaskActivity;
import com.elson.todo.R;
import com.elson.todo.model.Task;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;


public class Fragments {
    public static class DatePickerFragment extends DialogFragment
            implements DatePickerDialog.OnDateSetListener {
        View addView;

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);
            return new DatePickerDialog(getActivity(), this, year, month, day);
        }


        public void onDateSet(DatePicker view, int year, int month, int day) {

            EditText v = (EditText)getActivity().findViewById(R.id.date_title);
            Calendar calendar = new GregorianCalendar(year, month, day);
            DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
            v.setText(dateFormat.format(calendar.getTime()), TextView.BufferType.EDITABLE);
        }
    }
}
