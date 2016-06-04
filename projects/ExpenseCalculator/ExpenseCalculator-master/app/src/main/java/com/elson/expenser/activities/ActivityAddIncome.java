package com.elson.expenser.activities;



import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentActivity;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.elson.expenser.adapters.IncomeCategoryAdapter;
import com.elson.expenser.com.elson.expenser.models.IncomeCategoryInfo;

import java.util.ArrayList;
import java.util.List;

import expenser.elson.com.expensecalculator.Database;
import expenser.elson.com.expensecalculator.R;

public class ActivityAddIncome extends AppCompatActivity {
	Toolbar toolbar;
	Window window;
	RecyclerView recyclerView;
    private Database db;
    FloatingActionButton fab;
    IncomeCategoryAdapter ica;
    List<IncomeCategoryInfo> list;
    String[] category_array = {"Salary", "Bonus", "Freelance","Loan","Sales"};
    String[] drawable_array = {"about","calendar_clock","notepad","about","calendar_clock"};
	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.addincome_category);
		toolbar = (Toolbar) findViewById(R.id.tool_bar);
		toolbar.setBackgroundColor(Color.parseColor("#3F51B5"));
	    setSupportActionBar(toolbar);
	    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
	        window = getWindow();
	        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
	        window.setStatusBarColor(Color.parseColor("#303F9F"));
	    }
            list = new ArrayList<IncomeCategoryInfo>();
            db= new Database(this);
        System.out.println("Resource id is:"+db.countCategories());
        if(db.countCategories() ==0)
            intialcategoryInsert();
        recyclerView = (RecyclerView)findViewById(R.id.cardList);
        fab = (FloatingActionButton)findViewById(R.id.fab);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        list = db.get_allcategories();
        ica = new IncomeCategoryAdapter(list);
        recyclerView.setAdapter(ica);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addCategory();
            }
        });

        ica.SetOnItemClickListener(new IncomeCategoryAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(ActivityAddIncome.this, AddIncomeForm.class);
                intent.putExtra("category_name", list.get(position).name);
                startActivity(intent);
                System.out.println("Position id is:" +list.get(position).name);
            }
        });
	}

    private void intialcategoryInsert()
    {
        for(int i=0; i<category_array.length;i++)
        {
            Resources res = getResources();
            int resId = res.getIdentifier(drawable_array[i], "mipmap", getPackageName());
            db.add_category(category_array[i],"income",resId,null,null,i);
        }
    }

    private void addCategory()
    {
        db.add_category("Test","income",0,null,null,0);
        list = db.get_allcategories();
        ica = new IncomeCategoryAdapter(list);
        recyclerView.setAdapter(ica);
    }


}
