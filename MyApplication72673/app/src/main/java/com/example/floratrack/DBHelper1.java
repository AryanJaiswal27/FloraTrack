package com.example.floratrack;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class DBHelper1 extends SQLiteOpenHelper{

    private static final String DATABASE_NAME = "qr_scan_data";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "scan_data";
    private static final String COLUMN_CODE = "code";
    private static final String COLUMN_STRING1 = "string1";
    private static final String COLUMN_STRING2 = "string2";
    private static final String COLUMN_STRING3 = "string3";
    private static final String COLUMN_STRING4 = "string4";
    private static final String COLUMN_STRING5 = "string5";
//EXTRaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa
    private static final String COLUMN_STRING6 = "string6";

    private static final String COLUMN_STRING7 = "string7";

    private static final String COLUMN_STRING8 = "string8";

    private static final String COLUMN_STRING9 = "string9";

    private static final String COLUMN_STRING10 = "string10";






    public DBHelper1(Context context) {
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
                + COLUMN_STRING5 + " TEXT,"
                + COLUMN_STRING6 + " TEXT,"
                + COLUMN_STRING7 + " TEXT,"
                + COLUMN_STRING8 + " TEXT,"
                + COLUMN_STRING9 + " TEXT,"
                + COLUMN_STRING10 + " TEXT"

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
    public boolean addData(String code, String string1, String string2, String string3, String string4, String string5, String string6, String string7, String string8, String string9,String string10) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_CODE, code);
        contentValues.put(COLUMN_STRING1, string1);
        contentValues.put(COLUMN_STRING2, string2);
        contentValues.put(COLUMN_STRING3, string3);
        contentValues.put(COLUMN_STRING4, string4);
        contentValues.put(COLUMN_STRING5, string5);
        contentValues.put(COLUMN_STRING6, string6);
        contentValues.put(COLUMN_STRING7, string7);
        contentValues.put(COLUMN_STRING8, string8);
        contentValues.put(COLUMN_STRING9, string9);
        contentValues.put(COLUMN_STRING10, string10);


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
                rowData.add(cursor.getString(6));
                rowData.add(cursor.getString(7));
                rowData.add(cursor.getString(8));
                rowData.add(cursor.getString(9));
                rowData.add(cursor.getString(10));


                dataList.add(rowData);
            } while (cursor.moveToNext());
        }

        cursor.close();
        return dataList;
    }

    public void updatePlant(String plantCode, String string1, String string2, String string3, String string4, String string5, String string6) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_STRING1, string1);
        values.put(COLUMN_STRING2, string2);
        values.put(COLUMN_STRING3, string3);
        values.put(COLUMN_STRING4, string4);
        values.put(COLUMN_STRING5, string5);
        values.put(COLUMN_STRING6, string6);


        // Update the plant if it already exists, otherwise insert a new plant
        int rowsAffected = db.update(TABLE_NAME, values, COLUMN_CODE + " = ?", new String[]{plantCode});

        if (rowsAffected == 0) {
            // Plant code doesn't exist, so insert a new plant
            values.put(COLUMN_CODE, plantCode);
            db.insert(TABLE_NAME, null, values);
        }

        db.close();
    }

    public void updateWateringDate(String plantCode, String newValue1) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_STRING7, newValue1);


        // Update the values for the given plant code
        int rowsAffected = db.update(TABLE_NAME, values, COLUMN_CODE + " = ?", new String[]{plantCode});

        // Check if the plant code exists in the database
        if (rowsAffected == 0) {

        }

        db.close();
    }
    public void updateWaterDuration(String plantCode, String newValue1) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(COLUMN_STRING8, newValue1);

        // Update the values for the given plant code
        int rowsAffected = db.update(TABLE_NAME, values, COLUMN_CODE + " = ?", new String[]{plantCode});

        // Check if the plant code exists in the database
        if (rowsAffected == 0) {

        }

        db.close();
    }

    public void updateFertilizerDate(String plantCode, String newValue1) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_STRING9, newValue1);


        // Update the values for the given plant code
        int rowsAffected = db.update(TABLE_NAME, values, COLUMN_CODE + " = ?", new String[]{plantCode});

        // Check if the plant code exists in the database
        if (rowsAffected == 0) {

        }

        db.close();
    }
    public void updateFertilizerDuration(String plantCode, String newValue1) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(COLUMN_STRING10, newValue1);

        // Update the values for the given plant code
        int rowsAffected = db.update(TABLE_NAME, values, COLUMN_CODE + " = ?", new String[]{plantCode});

        // Check if the plant code exists in the database
        if (rowsAffected == 0) {

        }

        db.close();
    }


    public String getValueForPlant(String plantCode, String columnName) {
        SQLiteDatabase db = this.getReadableDatabase();

        String value = null;

        // Query the database to get the value for the given plant code and column name
        Cursor cursor = db.query(TABLE_NAME, new String[]{columnName}, COLUMN_CODE + " = ?", new String[]{plantCode}, null, null, null);

        // Check if the cursor has data
        if (cursor.moveToFirst()) {
            // Retrieve the value from the cursor
            value = cursor.getString(Math.abs(cursor.getColumnIndex(columnName)));
        }

        // Close the cursor and database
        cursor.close();
        db.close();

        return value;
    }





    public List<String> getAllDataForColumn(String columnName) {
        List<String> columnData = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        // Query the database to get all data for the specified column
        Cursor cursor = db.query(TABLE_NAME, new String[]{columnName}, null, null, null, null, null);

        // Iterate through the cursor to retrieve data from the specified column
        while (cursor.moveToNext()) {
            String data = cursor.getString(Math.abs(cursor.getColumnIndex(columnName)));
            columnData.add(data);
        }

        // Close the cursor and database
        cursor.close();
        db.close();

        return columnData;
    }






}
