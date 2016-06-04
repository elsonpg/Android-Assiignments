package com.fortune.textinsd;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {
    EditText etPath;
    EditText etContent;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /** Getting preference data stored with the key "fpath"
         * If no such key exists, then the default path /sdcard/wpta_files1 will be returned
         * */
        String path = getPreferences(MODE_PRIVATE).getString("fpath", "/sdcard/wpta_file1");

        /** Getting reference to edittext et_path */
        etPath = (EditText) findViewById(R.id.et_path);

        /** Setting previously saved file's path in the edittext */
        etPath.setText(path);

        /** Getting reference to edittext et_content */
        etContent = (EditText) findViewById(R.id.et_content);

        /** Defining click listener event for the button btn_save
         * of the layout activity_main
         * */
        OnClickListener saveClickListener = new OnClickListener() {
            @Override
            public void onClick(View v) {
                File file = new File(etPath.getText().toString());
                FileWriter writer=null;
                try {
                    writer = new FileWriter(file);

                    /** Saving the contents to the file*/
                    writer.write(etContent.getText().toString());

                    /** Closing the writer object */
                    writer.close();

                    /** Getting sharedpreferences object to store the path of last saved file */
                    SharedPreferences.Editor editor = getPreferences(MODE_PRIVATE).edit();

                    /** Setting the last saved file's path in the shared preference */
                    editor.putString("fpath", file.getPath());

                    /** Save the path to the shared preference */
                    editor.commit();

                    Toast.makeText(getBaseContext(), "Successfully saved", Toast.LENGTH_SHORT).show();

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };

        /** Defining click listener event for the button btn_save
         * of the layout activity_main
         * */
        OnClickListener readClickListener = new OnClickListener() {
            @Override
            public void onClick(View v) {
                File file = new File(etPath.getText().toString());

                String strLine="";
                StringBuilder text = new StringBuilder();

                try {
                    FileReader fReader = new FileReader(file);
                    BufferedReader bReader = new BufferedReader(fReader);

                    /** Reading the contents of the file , line by line */
                    while( (strLine=bReader.readLine()) != null  ){
                        text.append(strLine+"\n");
                    }

                    Toast.makeText(getBaseContext(), "Successfully loaded", Toast.LENGTH_SHORT).show();

                } catch (IOException e) {
                    e.printStackTrace();
                }

                etContent.setText(text);
            }
        };

        /** Getting reference to the button btn_save */
        Button btnSave = (Button) findViewById(R.id.btn_save);

        /** Setting click event listener for the save button */
        btnSave.setOnClickListener(saveClickListener);

        /** Getting reference to the button btn_read */
        Button btnRead = (Button) findViewById(R.id.btn_read);

        /** Setting click event listener for the read button */
        btnRead.setOnClickListener(readClickListener);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
}