package com.frontierdevelopers.session8assign2;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final int SETTINGS_RESULT = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        android.view.View.OnClickListener settingsClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), AppPreferences.class);
                startActivity(intent);


            }
        };

        /** Defining a click  listener */
        android.view.View.OnClickListener showClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ShowActivity.class);
                startActivity(intent);
            }
        };

        /** Getting a reference to button object of the main layout */
        Button btnSettings = (Button) findViewById(R.id.btn_settings);
        btnSettings.setOnClickListener(settingsClickListener);

        /** Getting a reference to button object of the main layout */
        Button btnShow = (Button) findViewById(R.id.btn_show);
        btnShow.setOnClickListener(showClickListener);
    }

    }





