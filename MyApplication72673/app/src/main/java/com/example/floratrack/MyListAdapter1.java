package com.example.floratrack;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MyListAdapter1 extends ArrayAdapter<String> {

    private final Activity context;
    private final String[] text1;
    private final String[] text2;
    private final String[] text3;
    private final String[] text4;
    private final String[] text5;
    private final String[] text6;
    private final String[] text7;
    private final String[] text8;
    private final String[] text9;

    private final String[] text10;
    private final String[] text11;





    public MyListAdapter1(Activity context, String[] text1, String[] text2, String[] text3, String[] text4, String[] text5, String[] text6,String[] text7,String[] text8,String[] text9,String[] text10,String[] text11) {
        super(context, R.layout.layout, text1);
        // TODO Auto-generated constructor stub

        this.context=context;
        this.text1=text1;
        this.text2=text2;
        this.text3=text3;
        this.text4=text4;
        this.text5=text5;
        this.text6=text6;
        this.text7=text7;
        this.text8=text8;
        this.text9=text9;
        this.text10=text10;
        this.text11=text11;




    }

    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.layout, null,true);

        TextView titleText1 = (TextView) rowView.findViewById(R.id.txt1);
        TextView titleText2 = (TextView) rowView.findViewById(R.id.txt2);
        TextView titleText3 = (TextView) rowView.findViewById(R.id.txt3);
        TextView titleText4 = (TextView) rowView.findViewById(R.id.txt4);
        TextView titleText5 = (TextView) rowView.findViewById(R.id.txt5);
        TextView titleText6 = (TextView) rowView.findViewById(R.id.txt6);
        Button button = (Button) rowView.findViewById(R.id.btn);
        Button button1 = (Button) rowView.findViewById(R.id.btn1);




        titleText1.setText(text1[position]);
        titleText2.setText(text2[position]);
        titleText3.setText(text3[position]);
        titleText4.setText(text4[position]);
        titleText5.setText(text5[position]);
        titleText6.setText(text6[position]);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context.getBaseContext(), GenerateActivity2.class);
                String myText = "Info:"+text1[position] + "," + text2[position] + "," + text3[position] + "," + text4[position] + "," + text5[position] + "," + text6[position];
                i.putExtra("qr_in_progress", myText);
                context.startActivity(i);


            }
        });

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBHelper1 db = new DBHelper1(context.getBaseContext());
                db.deleteRow(text1[position]);
                Toast.makeText(context.getBaseContext(), "Data Deleted", Toast.LENGTH_SHORT).show();
                context.finish();


            }
        });




        return rowView;

    };

}
