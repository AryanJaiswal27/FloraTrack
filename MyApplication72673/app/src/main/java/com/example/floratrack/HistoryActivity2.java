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

import java.util.List;

public class HistoryActivity2 extends AppCompatActivity {
    ListView list;
    private DBHelper1 dbHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);



        dbHelper = new DBHelper1(getBaseContext());


        if(dbHelper.checkDataExists()) {
           List<List<String>> allData = dbHelper.getAllData();


            String[][] dataArray = convertListToArray(allData);

            String arr1[] = getElementsByIndex(0, dataArray);
            String arr2[] = getElementsByIndex(1, dataArray);
            String arr3[] = getElementsByIndex(2, dataArray);
            String arr4[] = getElementsByIndex(3, dataArray);
            String arr5[] = getElementsByIndex(4, dataArray);
            String arr6[] = getElementsByIndex(5, dataArray);
            String arr7[] = getElementsByIndex(6, dataArray);
            String arr8[] = getElementsByIndex(7, dataArray);
            String arr9[] = getElementsByIndex(8, dataArray);
            String arr10[] = getElementsByIndex(9, dataArray);
            String arr11[] = getElementsByIndex(10, dataArray);

            MyListAdapter1 adapter = new MyListAdapter1(this, arr1, arr2, arr3, arr4, arr5, arr6,arr7,arr8,arr9,arr10,arr11);
            list = (ListView) findViewById(R.id.list);
            list.setAdapter(adapter);

//            Toast.makeText(this, "In", Toast.LENGTH_SHORT).show();
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
        int numCols = 11; // Assuming all inner lists have the same size

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

}