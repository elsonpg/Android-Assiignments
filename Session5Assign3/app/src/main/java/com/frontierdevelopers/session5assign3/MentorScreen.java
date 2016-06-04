package com.frontierdevelopers.session5assign3;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

/**
 * Created by Elson on 5/7/2016.
 */
public class MentorScreen extends Activity {

    TextView mentor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.mentor_screen);

        mentor=(TextView)findViewById(R.id.tvMentorName);

        Bundle bundle=getIntent().getExtras();

        if(bundle!=null){
            String course=bundle.getString(MainActivity. BUNDLE_KEY_COURSE);

            if(course.equalsIgnoreCase("Android course")){
                mentor.setText("Preetika, Abhinandan");
            }
            else if(course.equalsIgnoreCase("Java training")){
                mentor.setText("Sumit, Jinto");
            }
        }
    }
}
