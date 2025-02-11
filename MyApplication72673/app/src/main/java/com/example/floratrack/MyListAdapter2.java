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

public class MyListAdapter2 extends ArrayAdapter<String> {


    private final Activity context;
    private final String[] text1;
    private final String[] text2;





    public MyListAdapter2(Activity context, String[] text1, String[] text2) {
        super(context, R.layout.layout1, text1);
        // TODO Auto-generated constructor stub

        this.context=context;
        this.text1=text1;
        this.text2=text2;




    }

    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.layout1, null,true);

        TextView titleText1 = (TextView) rowView.findViewById(R.id.txt2);
        TextView titleText2 = (TextView) rowView.findViewById(R.id.txt3);




        titleText1.setText(text1[position]);
        titleText2.setText(text2[position]);




        return rowView;

    };
}
