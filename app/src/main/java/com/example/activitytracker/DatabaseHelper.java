package com.example.activitytracker;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String TAG = "DatabaseHelper";

    private static final String TABLE_NAME = "event_table";
    private static final String COL1 = "ID";
    private static final String COL2 = "month";
    private static final String COL3 = "day";
    private static final String COL4 = "year";
    private static final String COL5 = "events";

    public DatabaseHelper(Context context){
        super(context, TABLE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL2 + " INTEGER, " + COL3 + " INTEGER, " + COL4 + " INTEGER, " + COL5 + " TEXT)";
        db.execSQL(createTable);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void onUpgrade(SQLiteDatabase db, int i, int i1, int i2, int i3, int i4){
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean addData(int item1, int item2, int item3, String item4){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL2, item1);
        contentValues.put(COL3, item2);
        contentValues.put(COL4, item3);
        contentValues.put(COL5, item4);

        Log.d(TAG, "addData: Adding " + item1 + " " + item2 + " " + item3 + " " + item4 + " to " + TABLE_NAME);

        long result = db.insert(TABLE_NAME, null, contentValues);

        if(result == -1){
            return false;
        }
        else {
            return true;
        }
    }

    public Cursor getListContents(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor data = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        return data;
    }
}
