package com.example.activitytracker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.CalendarView;
import android.widget.Toast;

public class MainActivity extends FragmentActivity {
    private CalendarView mCalendarView;
    private Object eventFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mCalendarView = (CalendarView) findViewById(R.id.calendarView);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        mCalendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
                public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                setContentView(R.layout.fragment_event);
                /*eventFragment newFragment = new eventFragment();
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
// Replace whatever is in the fragment_container view with this fragment,
// and add the transaction to the back stack
                transaction.add(R.id.optionsView, newFragment);
// Commit the transaction
                transaction.commit();*/
                }
        });
    }
}