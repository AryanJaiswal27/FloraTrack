package com.example.floratrack;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "qr_data";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "data";
    private static final String COLUMN_CODE = "code";
    private static final String COLUMN_STRING1 = "string1";
    private static final String COLUMN_STRING2 = "string2";
    private static final String COLUMN_STRING3 = "string3";
    private static final String COLUMN_STRING4 = "string4";
    private static final String COLUMN_STRING5 = "string5";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableQuery = "CREATE TABLE " + TABLE_NAME + " ("
                + COLUMN_CODE + " TEXT PRIMARY KEY,"
                + COLUMN_STRING1 + " TEXT,"
                + COLUMN_STRING2 + " TEXT,"
                + COLUMN_STRING3 + " TEXT,"
                + COLUMN_STRING4 + " TEXT,"
                + COLUMN_STRING5 + " TEXT"
                + ")";
        db.execSQL(createTableQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public void deleteRow(String valueToDelete) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, COLUMN_CODE + " = ?", new String[]{valueToDelete});
        db.close();
    }



    public boolean checkDataExists() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME,null);
        boolean exists = (cursor.getCount() > 0);
        cursor.close();
        return exists;
    }

    // Method to add data to the database
    public boolean addData(String code, String string1, String string2, String string3, String string4, String string5) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_CODE, code);
        contentValues.put(COLUMN_STRING1, string1);
        contentValues.put(COLUMN_STRING2, string2);
        contentValues.put(COLUMN_STRING3, string3);
        contentValues.put(COLUMN_STRING4, string4);
        contentValues.put(COLUMN_STRING5, string5);

        long result = db.insertWithOnConflict(TABLE_NAME, null, contentValues, SQLiteDatabase.CONFLICT_IGNORE);
        return result != -1; // If result is -1, insertion failed
    }

    // Method to check if a code exists in the database
    public boolean checkIfCodeExists(String code) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE " + COLUMN_CODE + "=?", new String[]{code});
        boolean exists = cursor.getCount() > 0;
        cursor.close();
        return exists;
    }


    public List<List<String>> getAllData() {
        List<List<String>> dataList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);

        if (cursor.moveToFirst()) {
            do {
                List<String> rowData = new ArrayList<>();
                rowData.add(cursor.getString(0));
                rowData.add(cursor.getString(1));
                rowData.add(cursor.getString(2));
                rowData.add(cursor.getString(3));
                rowData.add(cursor.getString(4));
                rowData.add(cursor.getString(5));

                dataList.add(rowData);
            } while (cursor.moveToNext());
        }

        cursor.close();
        return dataList;
    }







}
