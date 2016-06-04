package com.frontierdevelopers.session13assign3.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.frontierdevelopers.session13assign3.Item1Fragment;
import com.frontierdevelopers.session13assign3.Item2Fragment;

/**
 * Created by Elson on 6/1/2016.
 */
public class TabsPagerAdapter extends FragmentPagerAdapter {

    public TabsPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int index) {

        switch (index) {
            case 0:
                // Buttons fragment activity
                return new Item1Fragment();
            case 1:
                // Colors fragment activity
                return new Item2Fragment();

        }

        return null;
    }

    @Override
    public int getCount() {
        // get item count - equal to number of tabs
        return 2;
    }

}
