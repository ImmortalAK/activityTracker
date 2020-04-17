package com.example.activitytracker;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class eventFragment extends Fragment{


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;
    private Button btn1;
    private Button btn2;
    private Button btn3;
    private Button btn4;
    private Button btn5;
    public static int month;
    public static int day;
    public static int year;


    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_event, null);
        btn1 = (Button) v.findViewById(R.id.option1);
        btn2 = (Button) v.findViewById(R.id.option2);
        btn3 = (Button) v.findViewById(R.id.option3);
        btn4 = (Button) v.findViewById(R.id.option4);
        btn5 = (Button) v.findViewById(R.id.optionWriteIn);
        return v;
    }
    public void newEventFragment(int year, int month, int dayOfMonth) {
        this.year = year;
        this.month = month;
        this.day = dayOfMonth;
    }
}

