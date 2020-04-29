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
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends FragmentActivity {
    private CalendarView mCalendarView;
    DatabaseHelper mDatabaseHelper;
    eventFragment optionsFrag = new eventFragment();
    AreYouSure yesOrNo = new AreYouSure();
    boolean eventFragmentOpen = false;
    static String ID = new String();
    static boolean AreYouSure = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mCalendarView = (CalendarView) findViewById(R.id.calendarView);
        mDatabaseHelper = new DatabaseHelper(this);
        ListView listView = (ListView) findViewById(R.id.listView);
        //populate an ArrayList<String> from the database and then view it
        ArrayList<String> theList = new ArrayList<>();
        final ArrayList<String> ids = new ArrayList<String>();
        Cursor data = mDatabaseHelper.getListContents();
        if(data.getCount() == 0){
            Toast.makeText(this, "The Database was empty.", Toast.LENGTH_LONG).show();
        }
        else{
            while(data.moveToNext()){
                theList.add(data.getString(1) + "/" + data.getString(2) + "/" + data.getString(3) + ": " + data.getString(4));
                ListAdapter listAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, theList);
                listView.setAdapter(listAdapter);
                ids.add(data.getString(0));
            }
        }
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ID = ids.get(position);
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                if(AreYouSure == false){
                    AreYouSure = true;
                    fragmentTransaction.add(R.id.container,yesOrNo);
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.commit();
                }
                else {
                    AreYouSure = false;
                    fragmentTransaction.remove(yesOrNo);
                    fragmentTransaction.commit();
                }
            }
        });
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
                if (eventFragmentOpen == false) {
                    eventFragmentOpen = true;
                    fragmentTransaction.add(R.id.container, optionsFrag);
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.commit();
                } else {
                    eventFragmentOpen = false;
                    fragmentTransaction.remove(optionsFrag);
                    fragmentTransaction.commit();
                }
            }
        });
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
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

            case R.id.option2:
                Toast.makeText(this, "Button 2 Clicked", Toast.LENGTH_SHORT).show();
                TextView txt1 = (TextView) findViewById(R.id.selectedD);
                txt1.setText("Something: " + eventFragment.year);
                String newEntry1 = "Meditating";
                mDatabaseHelper.addData(eventFragment.month, eventFragment.day, eventFragment.year, newEntry1);
                FragmentManager manager1 = getSupportFragmentManager();
                FragmentTransaction transaction1 = manager1.beginTransaction();
                transaction1.remove(optionsFrag);
                transaction1.addToBackStack(null);
                transaction1.commit();

                ListView listView1 = (ListView) findViewById(R.id.listView);
                ArrayList<String> theList1 = new ArrayList<>();
                Cursor data1 = mDatabaseHelper.getListContents();
                if(data1.getCount() == 0){
                    Toast.makeText(this, "The Database was empty.", Toast.LENGTH_LONG).show();
                }
                else {
                    while (data1.moveToNext()) {
                        theList1.add(data1.getString(1) + "/" + data1.getString(2) + "/" + data1.getString(3) + ": " + data1.getString(4));
                        ListAdapter listAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, theList1);
                        listView1.setAdapter(listAdapter);
                    }
                }
                break;
            case R.id.option3:
                Toast.makeText(this, "Button 3 Clicked", Toast.LENGTH_SHORT).show();
                TextView txt2 = (TextView) findViewById(R.id.selectedD);
                txt2.setText("Something: " + eventFragment.year);
                String newEntry2 = "Reading";
                mDatabaseHelper.addData(eventFragment.month, eventFragment.day, eventFragment.year, newEntry2);
                FragmentManager manager2 = getSupportFragmentManager();
                FragmentTransaction transaction2 = manager2.beginTransaction();
                transaction2.remove(optionsFrag);
                transaction2.addToBackStack(null);
                transaction2.commit();

                ListView listView2 = (ListView) findViewById(R.id.listView);
                ArrayList<String> theList2 = new ArrayList<>();
                Cursor data2 = mDatabaseHelper.getListContents();
                if(data2.getCount() == 0){
                    Toast.makeText(this, "The Database was empty.", Toast.LENGTH_LONG).show();
                }
                else {
                    while (data2.moveToNext()) {
                        theList2.add(data2.getString(1) + "/" + data2.getString(2) + "/" + data2.getString(3) + ": " + data2.getString(4));
                        ListAdapter listAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, theList2);
                        listView2.setAdapter(listAdapter);
                    }
                }
                break;
            case R.id.option4:
                Toast.makeText(this, "Button 4 Clicked", Toast.LENGTH_SHORT).show();
                TextView txt3 = (TextView) findViewById(R.id.selectedD);
                txt3.setText("Something: " + eventFragment.year);
                String newEntry3 = "Studying";
                mDatabaseHelper.addData(eventFragment.month, eventFragment.day, eventFragment.year, newEntry3);
                FragmentManager manager3 = getSupportFragmentManager();
                FragmentTransaction transaction3 = manager3.beginTransaction();
                transaction3.remove(optionsFrag);
                transaction3.addToBackStack(null);
                transaction3.commit();

                ListView listView3 = (ListView) findViewById(R.id.listView);
                ArrayList<String> theList3 = new ArrayList<>();
                Cursor data3 = mDatabaseHelper.getListContents();
                if(data3.getCount() == 0){
                    Toast.makeText(this, "The Database was empty.", Toast.LENGTH_LONG).show();
                }
                else {
                    while (data3.moveToNext()) {
                        theList3.add(data3.getString(1) + "/" + data3.getString(2) + "/" + data3.getString(3) + ": " + data3.getString(4));
                        ListAdapter listAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, theList3);
                        listView3.setAdapter(listAdapter);
                    }
                }
                break;
            case R.id.optionWriteIn:
                EditText editText = (EditText) findViewById(R.id.editTextWriteIn);
                String value = editText.getText().toString();
                Toast.makeText(this, "Button 5 Clicked", Toast.LENGTH_SHORT).show();
                TextView txt4 = (TextView) findViewById(R.id.selectedD);
                txt4.setText("Something: " + eventFragment.year);
                String newEntry4 = value;
                mDatabaseHelper.addData(eventFragment.month, eventFragment.day, eventFragment.year, newEntry4);
                FragmentManager manager4 = getSupportFragmentManager();
                FragmentTransaction transaction4 = manager4.beginTransaction();
                transaction4.remove(optionsFrag);
                transaction4.addToBackStack(null);
                transaction4.commit();

                ListView listView4 = (ListView) findViewById(R.id.listView);
                ArrayList<String> theList4 = new ArrayList<>();
                Cursor data4 = mDatabaseHelper.getListContents();
                if(data4.getCount() == 0){
                    Toast.makeText(this, "The Database was empty.", Toast.LENGTH_LONG).show();
                }
                else {
                    while (data4.moveToNext()) {
                        theList4.add(data4.getString(1) + "/" + data4.getString(2) + "/" + data4.getString(3) + ": " + data4.getString(4));
                        ListAdapter listAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, theList4);
                        listView4.setAdapter(listAdapter);
                    }
                }
                break;
            case R.id.About:
                Intent intent = new Intent(this, about.class);
                startActivity(intent);
                break;
            case R.id.yes:
                Toast.makeText(this, "Row Removed", Toast.LENGTH_SHORT).show();

                mDatabaseHelper.deleteRow(ID);
                FragmentManager manager5 = getSupportFragmentManager();
                FragmentTransaction transaction5 = manager5.beginTransaction();
                transaction5.remove(yesOrNo);
                transaction5.addToBackStack(null);
                transaction5.commit();

                ListView listView5 = (ListView) findViewById(R.id.listView);
                ArrayList<String> theList5 = new ArrayList<>();
                Cursor data5 = mDatabaseHelper.getListContents();
                if(data5.getCount() == 0){
                    Toast.makeText(this, "The Database was empty.", Toast.LENGTH_LONG).show();
                }
                else {
                    while (data5.moveToNext()) {
                        theList5.add(data5.getString(1) + "/" + data5.getString(2) + "/" + data5.getString(3) + ": " + data5.getString(4));
                        ListAdapter listAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, theList5);
                        listView5.setAdapter(listAdapter);
                    }
                }
                break;
            case R.id.no:
                Toast.makeText(this, "Cancelled", Toast.LENGTH_SHORT).show();

                FragmentManager manager6 = getSupportFragmentManager();
                FragmentTransaction transaction6 = manager6.beginTransaction();
                transaction6.remove(yesOrNo);
                transaction6.addToBackStack(null);
                transaction6.commit();

                ListView listView6 = (ListView) findViewById(R.id.listView);
                ArrayList<String> theList6 = new ArrayList<>();
                Cursor data6 = mDatabaseHelper.getListContents();
                if(data6.getCount() == 0){
                    Toast.makeText(this, "The Database was empty.", Toast.LENGTH_LONG).show();
                }
                else {
                    while (data6.moveToNext()) {
                        theList6.add(data6.getString(1) + "/" + data6.getString(2) + "/" + data6.getString(3) + ": " + data6.getString(4));
                        ListAdapter listAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, theList6);
                        listView6.setAdapter(listAdapter);
                    }
                }
                break;
            case R.id.ShowByDay:
                if(optionsFrag.month != 0) {
                    intent = new Intent(this, dayTotal.class);
                    startActivity(intent);
                }
                else
                    Toast.makeText(this, "Please Selected a Day", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}