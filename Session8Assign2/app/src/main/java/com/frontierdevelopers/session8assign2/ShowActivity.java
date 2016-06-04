package com.frontierdevelopers.session8assign2;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.TextView;

/**
 * Created by Elson on 5/18/2016.
 */
public class ShowActivity extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main);

        /** Getting the references to the textview object of the show layout */
        TextView tv_edittext_preference1 = (TextView) findViewById(R.id.tv_edittext_preference1);
        TextView tv_checkbox_preference1 = (TextView) findViewById(R.id.tv_checkbox_preference1);
        TextView tv_list_preference1 = (TextView) findViewById(R.id.tv_list_preference1);

        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(getBaseContext());

        /** Getting the values stored in the shared object via preference activity */
        boolean cb1 = sp.getBoolean("screenlock", false);
        String edit1 = sp.getString("password", "No text data");
        String list = sp.getString("Reminder for Updation","None Selected");

        tv_edittext_preference1.setText("EditText_Preference 1"+edit1);
        tv_checkbox_preference1.setText("CheckBox_Preference 1"+Boolean.toString(cb1));
        tv_list_preference1.setText("List_Preference 1"+list);
}}
