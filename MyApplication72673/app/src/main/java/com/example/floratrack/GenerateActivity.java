package com.example.floratrack;

        import androidx.appcompat.app.AppCompatActivity;

        import android.app.DatePickerDialog;
        import android.content.ContentValues;
        import android.content.Context;
        import android.content.Intent;
        import android.database.sqlite.SQLiteDatabase;
        import android.graphics.Bitmap;
        import android.icu.util.Calendar;
        import android.os.Bundle;
        import android.view.View;
        import android.view.inputmethod.InputMethodManager;
        import android.widget.Button;
        import android.widget.DatePicker;
        import android.widget.EditText;
        import android.widget.ImageView;
        import android.widget.TextView;
        import android.widget.Toast;

        import com.google.zxing.BarcodeFormat;
        import com.google.zxing.MultiFormatWriter;
        import com.google.zxing.WriterException;
        import com.google.zxing.common.BitMatrix;
        import com.journeyapps.barcodescanner.BarcodeEncoder;

public class GenerateActivity extends AppCompatActivity {
    Button btnGenerate;
    Button dateButton;
    TextView datetextView;
    private DBHelper dbHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gen);
        //Button for generating QR code
        btnGenerate = findViewById(R.id.btnGenerate);
        dateButton = findViewById(R.id.dateButton);
        datetextView = findViewById(R.id.datetextView);

        dateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // on below line we are getting
                // the instance of our calendar.
                final Calendar c = Calendar.getInstance();

                // on below line we are getting
                // our day, month and year.
                int year = c.get(Calendar.YEAR);
                int month = c.get(Calendar.MONTH);
                int day = c.get(Calendar.DAY_OF_MONTH);

                // on below line we are creating a variable for date picker dialog.
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        // on below line we are passing context.
                        GenerateActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                // on below line we are setting date to our text view.
                                datetextView.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

                            }
                        },
                        // on below line we are passing year,
                        // month and day for selected date in our date picker.
                        year, month, day);
                // at last we are calling show to
                // display our date picker dialog.
                datePickerDialog.show();
            }
        });
        //Text will be entered here to generate QR code
        //ImageView for generated QR code
        EditText etText1 = findViewById(R.id.etText1);
        EditText etText2 = findViewById(R.id.etText2);
        EditText etText3 = findViewById(R.id.etText3);
        EditText etText4 = findViewById(R.id.etText4);
        EditText etText5 = findViewById(R.id.etText5);
//        EditText etText6 = findViewById(R.id.etText6);



//        btnGenerate.setOnClickListener(v -> {
            //getting text from input text field.

//            String str1 = etText1.getText().toString().trim();
//            String str2 = etText2.getText().toString().trim();
//            String str3 = etText3.getText().toString().trim();
//            String str4 = etText4.getText().toString().trim();
//            String str5 = etText5.getText().toString().trim();
//            String str6 = etText6.getText().toString().trim();
//
//            if(str1.isEmpty()||str2.isEmpty()||str3.isEmpty()||str4.isEmpty()||str5.isEmpty()||str6.isEmpty()){
//
//                Toast.makeText(this, "All Fields are Compulsory!!", Toast.LENGTH_SHORT).show();
//
//            }
//            else {
//
//                String myText = str1 + "," + str2 + "," + str3 + "," + str4 + "," + str5 + "," + str6;
//                Toast.makeText(this, myText, Toast.LENGTH_SHORT).show();
//
//
//                Intent intent = new Intent(GenerateActivity.this,GenerateActivity2.class);
//
//
//
////                Intent intent = new Intent(GenerateActivity.this, GenerateActivity2.class);
////                intent.putExtra("qr_in_progress", myText);
////                startActivity(intent);
//
//            }
            //initializing MultiFormatWriter for QR code
//            MultiFormatWriter mWriter = new MultiFormatWriter();
//            try {
//                //BitMatrix class to encode entered text and set Width & Height
//                BitMatrix mMatrix = mWriter.encode(myText, BarcodeFormat.QR_CODE, 400,400);
//                BarcodeEncoder mEncoder = new BarcodeEncoder();
//                Bitmap mBitmap = mEncoder.createBitmap(mMatrix);//creating bitmap of code
//                imageCode.setImageBitmap(mBitmap);//Setting generated QR code to imageView
//                // to hide the keyboard
//                InputMethodManager manager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
//                manager.hideSoftInputFromWindow(etText.getApplicationWindowToken(), 0);
//            } catch (WriterException e) {
//                e.printStackTrace();
//            }
 //       });
        btnGenerate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String str1 = etText1.getText().toString().trim();
            String str2 = etText2.getText().toString().trim();
            String str3 = etText3.getText().toString().trim();
            String str4 = etText4.getText().toString().trim();
            String str5 = etText5.getText().toString().trim();
//            String str6 = etText6.getText().toString().trim();
                String str6 =datetextView.getText().toString().trim();
            String myText = "Info:"+str1 + "," + str2 + "," + str3 + "," + str4 + "," + str5 + "," + str6;



            if(str1.isEmpty()||str2.isEmpty()||str3.isEmpty()||str4.isEmpty()||str5.isEmpty()||str6.isEmpty()){

                Toast.makeText(getBaseContext(), "All Fields are Compulsory!!", Toast.LENGTH_SHORT).show();

            }
            else {
                dbHelper = new DBHelper(getBaseContext());
                boolean hi = dbHelper.checkIfCodeExists(str1);

                if(hi){

                    Toast.makeText(getBaseContext(),"ID Already Exists",Toast.LENGTH_SHORT).show();



                }
                else {
                    dbHelper.addData(str1,str2,str3,str4,str5,str6);





                    Intent intent = new Intent(getBaseContext(), GenerateActivity2.class);
                    intent.putExtra("qr_in_progress", myText);
                    startActivity(intent);
                }
                }
            }
        });

    }




//
//
//
//    public void history(View v){
//       Intent i = new Intent(getBaseContext(), HistoryActivity.class);
//       startActivity(i);
//
//
//    }





}