package com.frontierdevelopers.session7assign3;

/**
 * Created by Elson on 5/13/2016.
 */

import android.graphics.Bitmap;

public class Employee {
    private Bitmap bmp;
    private String name;
    private int age;

    public Employee(Bitmap b, String n, int k) {
        bmp = b;
        name = n;
        age = k;
    }

    public Bitmap getBitmap() {
        return bmp;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

}
