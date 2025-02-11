package com.example.floratrack;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class AlertActivity extends AppCompatActivity {
    ListView list;
    private DBHelper1 dbHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alert);



        dbHelper = new DBHelper1(getBaseContext());


        if(dbHelper.checkDataExists()) {





            String arr1[] = listToStringArray(dbHelper.getAllDataForColumn("code"));
            String arr2[] = listToStringArray(dbHelper.getAllDataForColumn("string1"));

            Toast.makeText(this, "History Exists", Toast.LENGTH_SHORT).show();

            int count1 =0;
            int count2 =0;

//            String newarr1[] = new String[999];
//            String newarr2[] = new String[999];

            List<String> filteredList1 = new ArrayList<>();
            List<String> filteredList2 = new ArrayList<>();

            for (int i = 0; i < arr1.length; i++) {
                if (i < arr1.length && i < arr2.length) {
                    if (!dbHelper.getValueForPlant(arr1[i], "string7").equals("")) {

                        String newDate = addDaysToDate(dbHelper.getValueForPlant(arr1[i], "string7"), Integer.parseInt(dbHelper.getValueForPlant(arr1[i], "string8")));
                        if (!isDateYetToCome(newDate)) {
                            filteredList1.add(arr1[i]);
                            filteredList2.add(arr2[i]);
                           count1++;
                           count2++;


                        }


                    }
                }
            }





//            String arr[][] = filterArrays(arr1,arr2);
//
//
//
//            int maxLength = Math.max(arr1.length, arr2.length);
//
//            // Initialize a list to store elements that satisfy the condition
//            List<String> filteredList1 = new ArrayList<>();
//            List<String> filteredList2 = new ArrayList<>();
//
//            // Iterate over the elements of the input arrays and filter based on the condition
//            for (int i = 0; i < maxLength; i++) {
//                if (i < arr1.length && i < arr2.length) {
//                    if (new AlertActivity().test(arr[i], arr[i])) {
//                        filteredList1.add(array1[i]);
//                        filteredList2.add(array2[i]);
//                    }
//                }
//            }



            MyListAdapter2 adapter = new MyListAdapter2(this, filteredList1.toArray(new String[0]), filteredList2.toArray(new String[0]));
            list = (ListView) findViewById(R.id.list);
            list.setAdapter(adapter);
        }
        else{
            Toast.makeText(this, "Data Does not exist", Toast.LENGTH_SHORT).show();
        }

//        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view,int position, long id) {
//                // TODO Auto-generated method stub
//                if(position == 0) {
//                    //code specific to first list item
//                    Toast.makeText(getApplicationContext(),"Place Your First Option Code",Toast.LENGTH_SHORT).show();
//                }
//
//                else if(position == 1) {
//                    //code specific to 2nd list item
//                    Toast.makeText(getApplicationContext(),"Place Your Second Option Code",Toast.LENGTH_SHORT).show();
//                }
//
//                else if(position == 2) {
//
//                    Toast.makeText(getApplicationContext(),"Place Your Third Option Code",Toast.LENGTH_SHORT).show();
//                }
//                else if(position == 3) {
//
//                    Toast.makeText(getApplicationContext(),"Place Your Forth Option Code",Toast.LENGTH_SHORT).show();
//                }
//                else if(position == 4) {
//
//                    Toast.makeText(getApplicationContext(),"Place Your Fifth Option Code",Toast.LENGTH_SHORT).show();
//                }
//
//            }
//        });
    }





    public String[][] convertListToArray(List<List<String>> dataList) {
        int numRows = dataList.size();
        int numCols = 6; // Assuming all inner lists have the same size

        String[][] dataArray = new String[numRows][numCols];

        for (int i = 0; i < numRows; i++) {
            List<String> row = dataList.get(i);
            for (int j = 0; j < numCols; j++) {
                dataArray[i][j] = row.get(j);
            }
        }

        return dataArray;
    }
    public static String[] getElementsByIndex(int index, String[][] array) {
        String[] result = new String[array.length];
        for (int i = 0; i < array.length; i++) {
            if (index >= 0 && index < array[i].length) {
                result[i] = array[i][index];
            } else {
                result[i] = null; // or some default value if index is out of bounds
            }
        }
        return result;
    }
    public static String[] listToStringArray(List<String> list) {
        // Create a new string array with the same size as the list
        String[] array = new String[list.size()];

        // Copy each element from the list to the array
        for (int i = 0; i < list.size(); i++) {
            array[i] = list.get(i);
        }

        return array;
    }

    public String[][] filterArrays(String[] array1, String[] array2) {
        // Determine the maximum length between the two input arrays
        int maxLength = Math.max(array1.length, array2.length);

        // Initialize a list to store elements that satisfy the condition
        List<String> filteredList1 = new ArrayList<>();
        List<String> filteredList2 = new ArrayList<>();

        // Iterate over the elements of the input arrays and filter based on the condition
        for (int i = 0; i < maxLength; i++) {
            if (i < array1.length && i < array2.length) {
                if (new AlertActivity().test(array1[i], array2[i])) {
                    filteredList1.add(array1[i]);
                    filteredList2.add(array2[i]);
                }
            }
        }

        // Convert filtered lists to arrays and return as a two-dimensional array
        return new String[][] { filteredList1.toArray(new String[0]), filteredList2.toArray(new String[0]) };
    }



    public boolean test(String str1, String str2) {

        DBHelper1 db = new DBHelper1(getBaseContext());

        if (!db.getValueForPlant(str1, "string7").equals("")) {

            String newDate = addDaysToDate(db.getValueForPlant(str1, "string7"), Integer.parseInt(db.getValueForPlant(str1, "string8")));
            if (isDateYetToCome(newDate)) {
                  return false;

            } else {
                 return true;

            }


        }


           return false;
    }

    public static String getCurrentDateAsString() {
        // Create a SimpleDateFormat object with desired date format
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());


        // Get the current system date
        Date currentDate = new Date();

        // Format the date as string using SimpleDateFormat
        String formattedDate = sdf.format(currentDate);

        return formattedDate;
    }

    public static String addDaysToDate(String dateString, int daysToAdd) {
        // Define date format for parsing and formatting
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());

        try {
            // Parse the input date string to a Date object
            Date date = sdf.parse(dateString);

            // Create a Calendar instance and set it to the parsed date
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);

            // Add the specified number of days
            calendar.add(Calendar.DAY_OF_MONTH, daysToAdd);

            // Format the resulting date as a string
            String resultDate = sdf.format(calendar.getTime());

            return resultDate;
        } catch (ParseException e) {
            e.printStackTrace();
            return null; // Return null if parsing fails
        }
    }
    public static boolean isDateYetToCome(String dateString) {
        // Define date format for parsing
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());

        try {
            // Parse the input date string to a Date object
            Date inputDate = sdf.parse(dateString);

            // Get the current date
            Date currentDate = new Date();

            // Compare the input date with the current date
            return inputDate.after(currentDate); // Returns true if inputDate is after currentDate
        } catch (ParseException e) {
            e.printStackTrace();
            return false; // Return false if parsing fails
        }
    }






}