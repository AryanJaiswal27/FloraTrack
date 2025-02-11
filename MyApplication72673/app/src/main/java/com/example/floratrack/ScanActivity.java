package com.example.floratrack;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;

import android.content.Intent;
import android.text.util.Linkify;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

// implements onClickListener for the onclick behaviour of button
public class ScanActivity extends AppCompatActivity implements View.OnClickListener {
    Button scanBtn;
    LinearLayout btnArr;

   private Button searchButton;
    private Button wikiButton;
    private Button waterButton;
    private Button fertillizerButton;
//    TextView messageText, messageFormat;


    TextView txt1;
    TextView txt2;
    TextView txt3;
    TextView txt4;
    TextView txt5;
    TextView txt6;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan);

        scanBtn = findViewById(R.id.scanBtn);
        btnArr = findViewById(R.id.btnArr);

        //messageText = findViewById(R.id.textContent);
//        messageFormat = findViewById(R.id.textFormat);

        scanBtn.setOnClickListener(this);
       searchButton = findViewById(R.id.searchBtn);
       wikiButton = findViewById(R.id.wikiBtn);
        waterButton = findViewById(R.id.waterBtn);
        fertillizerButton = findViewById(R.id.fertilizerBtn);
        txt1 = findViewById(R.id.txt1);
        txt2 = findViewById(R.id.txt2);
        txt3 = findViewById(R.id.txt3);
        txt4 = findViewById(R.id.txt4);
        txt5 = findViewById(R.id.txt5);
        txt6 = findViewById(R.id.txt6);



    }

    @Override
    public void onClick(View v) {
        // we need to create the object
        // of IntentIntegrator class
        // which is the class of QR library
        IntentIntegrator intentIntegrator = new IntentIntegrator(this);
        intentIntegrator.setPrompt("Scan QR Code");

        intentIntegrator.setOrientationLocked(false);
        intentIntegrator.initiateScan();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        IntentResult intentResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        // if the intentResult is null then
        // toast a message as "cancelled"
        if (intentResult != null) {
            if (intentResult.getContents() == null) {
                Toast.makeText(getBaseContext(), "Cancelled", Toast.LENGTH_SHORT).show();
            } else {
                // if the intentResult is not null we'll set
                // the content and format of scan message
//                Toast.makeText(this, "Woore!!!", Toast.LENGTH_SHORT).show();
                String msg = intentResult.getContents().toString();

                if(msg.startsWith("Info:")){


                    msg= msg.substring(5);
                    String[] arrOfStr = msg.split(",", 6);
                    waterButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent i = new Intent(getBaseContext(),WaterActivity.class);
                            i.putExtra("code",arrOfStr[0]);
                            startActivity(i);
                        }
                    });
                    fertillizerButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent i = new Intent(getBaseContext(),FertilizerActivity.class);
                            i.putExtra("code",arrOfStr[0]);
                            startActivity(i);
                        }
                    });



                    searchButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            String plantName = arrOfStr[1]; // Replace this with the plant name entered by the user
                            searchPlantBenefits(plantName);
                        }
                    });

                    wikiButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            String plantName = arrOfStr[1]; // Replace this with the plant name entered by the user
                            searchPlantBenefits1(plantName);
                        }
                    });



                    String[] arr = {"Plant Id : ","Plant Name : ","Plant Scientific Name : ","Plant Family : ","Plant Genus : ","Plantation Date : "};
//                    String newMsg="";
//                    for (int i = 0; i < arrOfStr.length; i++) {
//                       newMsg=newMsg +arr[i]+ arrOfStr[i]+".";
//                    }
//                    LinearLayout m_ll = (LinearLayout) findViewById(R.id.group);
//
//                    for (int i = 0; i < m_ll.getChildCount(); i++) {
//                        View childView = m_ll.getChildAt(i);
//                        if (childView instanceof TextView) {
//                            m_ll.removeView(childView);
//                        }
//                    }


                    txt1.setText(""+arr[0]+ arrOfStr[0]+".");
                    txt2.setText(""+arr[1]+ arrOfStr[1]+".");
                    txt3.setText(""+arr[2]+ arrOfStr[2]+".");
                    txt4.setText(""+arr[3]+ arrOfStr[3]+".");
                    txt5.setText(""+arr[4]+ arrOfStr[4]+".");
                    txt6.setText(""+arr[5]+ arrOfStr[5]+".");




                    DBHelper1 db = new DBHelper1(getBaseContext());

                    Toast.makeText(this, getCurrentDateAsString(), Toast.LENGTH_SHORT).show();
                    if(db.checkIfCodeExists(arrOfStr[0])){
                        db.updatePlant(arrOfStr[0], arrOfStr[1], arrOfStr[2], arrOfStr[3], arrOfStr[4], arrOfStr[5], getCurrentDateAsString());
                    }
                    else {
                        db.addData(arrOfStr[0], arrOfStr[1], arrOfStr[2], arrOfStr[3], arrOfStr[4], arrOfStr[5], getCurrentDateAsString(), "", "","","");
                    }

                    txt1.setVisibility(View.VISIBLE);
                    txt2.setVisibility(View.VISIBLE);
                    txt3.setVisibility(View.VISIBLE);
                    txt4.setVisibility(View.VISIBLE);
                    txt5.setVisibility(View.VISIBLE);
                    txt6.setVisibility(View.VISIBLE);

                    scanBtn.setVisibility(View.GONE);


//                    for(int i=0;i<6;i++)
//                    {
//                        TextView text = new TextView(this);
//                        text.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
//                        text.setText(""+arr[i]+ arrOfStr[i]+".");
//                        text.setTextSize(20);
//                        text.setTextColor(Color.BLACK);
//
//                        m_ll.addView(text);
//                    }
                    Button b = findViewById(R.id.scanBtn); // Replace your_imageview_id with the id of your ImageView

// Make the ImageView visible
                    b.setVisibility(View.GONE);

                    ImageView imageView = findViewById(R.id.image); // Replace your_imageview_id with the id of your ImageView

// Make the ImageView visible
                    imageView.setVisibility(View.VISIBLE);
                    btnArr.setVisibility(View.VISIBLE);

                 //   messageText.setText(newMsg);

                }
                else{
//                    LinearLayout m_ll = (LinearLayout) findViewById(R.id.group);
//                    for (int i = 0; i < m_ll.getChildCount(); i++) {
//                        View childView = m_ll.getChildAt(i);
//                        if (childView instanceof TextView) {
//                            m_ll.removeView(childView);
//                        }
//                    }
//                    for(int i=0;i<1;i++)
//                    {
//                        TextView text = new TextView(this);
//                        text.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
//                        text.setText(""+intentResult.getContents().toString());
//                        text.setTextSize(20);
//                        text.setAutoLinkMask(Linkify.WEB_URLS);
//                        text.setTextColor(Color.BLACK);
//                        m_ll.addView(text);
//                    }
                    txt1.setText(""+intentResult.getContents().toString());
                    txt1.setVisibility(View.VISIBLE);


                    Button b = findViewById(R.id.scanBtn); // Replace your_imageview_id with the id of your ImageView

// Make the ImageView visible
                    b.setVisibility(View.GONE);
                   // messageText.setText(intentResult.getContents());
                }



//                messageFormat.setText(intentResult.getFormatName());

            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }


    private void searchPlantBenefits(String plantName) {
        String searchQuery = "benefits of " + plantName;
        Uri uri = Uri.parse("https://www.google.com/search?q=" + searchQuery);
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }

    private void searchPlantBenefits1(String plantName) {
        String searchQuery = "" + plantName;
        Uri uri = Uri.parse("https://en.wikipedia.org/wiki/" + searchQuery);
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
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

}
