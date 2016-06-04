package com.frontierdevelopers.session8assign2;

import android.os.Bundle;
import android.preference.PreferenceActivity;

/**
 * Created by Elson on 5/18/2016.
 */
public class AppPreferences extends PreferenceActivity {

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferences);
    }
}
