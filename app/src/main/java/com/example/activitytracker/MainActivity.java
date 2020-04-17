package com.example.activitytracker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CalendarView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends FragmentActivity {
    private CalendarView mCalendarView;
    DatabaseHelper mDatabaseHelper;
    eventFragment optionsFrag = new eventFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mCalendarView = (CalendarView) findViewById(R.id.calendarView);
        mDatabaseHelper = new DatabaseHelper(this);
        ListView listView = (ListView) findViewById(R.id.listView);
        //populate an ArrayList<String> from the database and then view it
        ArrayList<String> theList = new ArrayList<>();
        Cursor data = mDatabaseHelper.getListContents();
        if(data.getCount() == 0){
            Toast.makeText(this, "The Database was empty.", Toast.LENGTH_LONG).show();
        }
        else{
            while(data.moveToNext()){
                theList.add(data.getString(1) + "/" + data.getString(2) + "/" + data.getString(3) + ": " + data.getString(4));
                ListAdapter listAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, theList);
                listView.setAdapter(listAdapter);
            }
        }
        mCalendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
                public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                month = month + 1;
                optionsFrag.newEventFragment(year, month, dayOfMonth);
                //setContentView(R.layout.fragment_event);
                Toast t = Toast.makeText(getApplicationContext(), month + " " + dayOfMonth + " " + year, Toast.LENGTH_LONG);
                t.setGravity(Gravity.BOTTOM | Gravity.RIGHT, 0, 0);
                t.show();
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.add(R.id.container,optionsFrag);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });
    }
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.option1:
                Toast.makeText(this, "Button 1 Clicked", Toast.LENGTH_SHORT).show();
                TextView txt = (TextView) findViewById(R.id.selectedD);
                txt.setText("Something: " + eventFragment.year);
                String newEntry = "Running";
                mDatabaseHelper.addData(eventFragment.month, eventFragment.day, eventFragment.year, newEntry);
                FragmentManager manager = getSupportFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                transaction.remove(optionsFrag);
                transaction.addToBackStack(null);
                transaction.commit();

                ListView listView = (ListView) findViewById(R.id.listView);
                ArrayList<String> theList = new ArrayList<>();
                Cursor data = mDatabaseHelper.getListContents();
                if(data.getCount() == 0){
                    Toast.makeText(this, "The Database was empty.", Toast.LENGTH_LONG).show();
                }
                else {
                    while (data.moveToNext()) {
                        theList.add(data.getString(1) + "/" + data.getString(2) + "/" + data.getString(3) + ": " + data.getString(4));
                        ListAdapter listAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, theList);
                        listView.setAdapter(listAdapter);
                    }
                }
                break;
        }
    }
}