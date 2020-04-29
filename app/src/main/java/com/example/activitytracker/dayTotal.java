package com.example.activitytracker;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import java.util.ArrayList;

public class dayTotal extends AppCompatActivity {
    public TableLayout stk;
    private TableRow tbrow0;
    private TableRow tbrow;
    private TextView calDate;
    private TextView titleNumActivity;
    private TextView titleActivityName;
    private TextView titleHours;
    private TextView NumActivity;
    private TextView ActivityName;
    private TextView Hours;
    private Button returnButton;
    DatabaseHelper m2DatabaseHelper;
    AreYouSure yesOrNo = new AreYouSure();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day_total);
        calDate = (TextView) findViewById(R.id.dateId);
        calDate.setText(eventFragment.month + " " + eventFragment.day + " " + eventFragment.year);
        //init();
        returnButton = (Button) findViewById(R.id.homeButton);
        TextView stats = (TextView) findViewById(R.id.Stats);
        m2DatabaseHelper = new DatabaseHelper(this);
        ListView listView = (ListView) findViewById(R.id.daylist);
        ArrayList<String> theList = new ArrayList<>();
        final ArrayList<String> ids = new ArrayList<String>();
        Cursor data = m2DatabaseHelper.getDay();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                MainActivity.ID = ids.get(position);
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                TextView stats = (TextView) findViewById(R.id.Stats);

                if (MainActivity.AreYouSure == false) {
                    MainActivity.AreYouSure = true;
                    fragmentTransaction.add(R.id.Checkbox, yesOrNo);
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.commit();
                } else {
                    MainActivity.AreYouSure = false;
                    fragmentTransaction.remove(yesOrNo);
                    fragmentTransaction.commit();
                }
            }
        });

        if (data.getCount() == 0) {
            Toast.makeText(dayTotal.this, "The Database was empty.", Toast.LENGTH_LONG).show();
        } else {
            while (data.moveToNext()) {
                theList.add(data.getString(1) + "/" + data.getString(2) + "/" + data.getString(3) + ": " + data.getString(4));
                ListAdapter listAdapter = new ArrayAdapter(dayTotal.this, android.R.layout.simple_list_item_1, theList);
                listView.setAdapter(listAdapter);
                ids.add(data.getString(0));
            }

        }

        returnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(dayTotal.this, MainActivity.class);
                startActivity(intent);
            }
        });

        stats.setText("Total Events: " + ids.size());
    }

    /*public void init() {
        stk = (TableLayout) findViewById(R.id.table_main);
        tbrow0 = new TableRow(this);
        titleNumActivity = new TextView(this);
        titleNumActivity.setText("# of Activities");
        tbrow0.addView(titleNumActivity);
        titleActivityName = new TextView(this);
        titleActivityName.setText("Activity");
        tbrow0.addView(titleActivityName);
        titleHours = new TextView(this);
        tbrow0.addView(titleHours);
    }*/
    /*public void addRow(){
        //needs a look somewhere for this i
        //method needs to be put in main activity in each case
            tbrow = new TableRow(this);
            NumActivity = new TextView(this);
            NumActivity.setText(""+ i);
            NumActivity.setGravity(Gravity.CENTER);
            ActivityName = new TextView(this);
            ActivityName.setText();  //button selected?
            ActivityName.setGravity(Gravity.CENTER);
            Hours = new TextView(this);
            Hours.setText("" + 1);
            Hours.setGravity(Gravity.CENTER);
            stk.addView(tbrow);

    }*/
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.yes:
                Toast.makeText(this, "Row Removed", Toast.LENGTH_SHORT).show();

                m2DatabaseHelper.deleteRow(MainActivity.ID);
                FragmentManager manager5 = getSupportFragmentManager();
                FragmentTransaction transaction5 = manager5.beginTransaction();
                transaction5.remove(yesOrNo);
                transaction5.addToBackStack(null);
                transaction5.commit();

                ListView listView5 = (ListView) findViewById(R.id.daylist);
                ArrayList<String> theList5 = new ArrayList<>();
                Cursor data5 = m2DatabaseHelper.getDay();
                if (data5.getCount() == 0) {
                    Toast.makeText(this, "The Database was empty.", Toast.LENGTH_LONG).show();
                } else {
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

                ListView listView6 = (ListView) findViewById(R.id.daylist);
                ArrayList<String> theList6 = new ArrayList<>();
                Cursor data6 = m2DatabaseHelper.getDay();
                if (data6.getCount() == 0) {
                    Toast.makeText(this, "The Database was empty.", Toast.LENGTH_LONG).show();
                } else {
                    while (data6.moveToNext()) {
                        theList6.add(data6.getString(1) + "/" + data6.getString(2) + "/" + data6.getString(3) + ": " + data6.getString(4));
                        ListAdapter listAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, theList6);
                        listView6.setAdapter(listAdapter);
                    }
                }
                break;

        }
    }
}
