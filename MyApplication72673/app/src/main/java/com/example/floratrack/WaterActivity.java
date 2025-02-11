package com.example.floratrack;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class WaterActivity extends AppCompatActivity {

    Button statusButton;
    Button durationButton;
    TextView textView;
    ImageView imageView;
    EditText editText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_water);
        Intent i = getIntent();
        String code = i.getStringExtra("code").toString();

        statusButton = findViewById(R.id.statusButton);
        durationButton = findViewById(R.id.durationButton);
        editText  = findViewById(R.id.editTextNumber);

        textView = findViewById(R.id.textView);
        imageView = findViewById(R.id.imageView);
        DBHelper1 db1 = new DBHelper1(getBaseContext());

        String duration = db1.getValueForPlant(code,"string8");
        editText.setText(duration);

        if(db1.getValueForPlant(code,"string7").equals("")){
            textView.setText("Not Watered");
            imageView.setImageDrawable(ContextCompat.getDrawable(getBaseContext(), R.drawable.baseline_water_drop_black_50));
        } else {

            String newDate = addDaysToDate(db1.getValueForPlant(code,"string7"),Integer.parseInt(db1.getValueForPlant(code,"string8")));
            if(isDateYetToCome(newDate)){

                textView.setText("Watered");
                imageView.setImageDrawable(ContextCompat.getDrawable(getBaseContext(), R.drawable.baseline_water_drop_blue_50));

            }
            else{
                textView.setText("Not Watered");
                imageView.setImageDrawable(ContextCompat.getDrawable(getBaseContext(), R.drawable.baseline_water_drop_black_50));


            }




        }


        durationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 if(editText.getText().toString().trim().equals("")){
                     Toast.makeText(WaterActivity.this, "Please Fill the TextField", Toast.LENGTH_SHORT).show();

                 }
                 else {
                     Toast.makeText(WaterActivity.this, "Duration Updated", Toast.LENGTH_SHORT).show();

                     DBHelper1 db = new DBHelper1(getBaseContext());
                     db.updateWaterDuration(code,editText.getText().toString());

                 }

            }
        });




        statusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBHelper1 db = new DBHelper1(getBaseContext());
                if(db.getValueForPlant(code,"string8").equals("")){
                    Toast.makeText(WaterActivity.this, "Add the Duration!!", Toast.LENGTH_SHORT).show();

                }
                else {

                    if (textView.getText().toString().equals("Watered")) {
                        textView.setText("Not Watered");
                        imageView.setImageDrawable(ContextCompat.getDrawable(getBaseContext(), R.drawable.baseline_water_drop_black_50));
                        db.updateWateringDate(code, "");
                    } else {

                        textView.setText("Watered");
                        imageView.setImageDrawable(ContextCompat.getDrawable(getBaseContext(), R.drawable.baseline_water_drop_blue_50));
                        db.updateWateringDate(code, getCurrentDateAsString());
                    }
                }


            }
        });



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