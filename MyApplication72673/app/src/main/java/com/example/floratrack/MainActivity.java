package com.example.floratrack;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void generate(View v){
        Intent i = new Intent(MainActivity.this,GenerateActivity.class);
        startActivity(i);

    }
    public void scan(View v){
        Intent i = new Intent(MainActivity.this,ScanActivity.class);
        startActivity(i);

    }

    public void history(View v){
        Intent i = new Intent(MainActivity.this,HistoryActivity.class);
        startActivity(i);

    }

    public void history_scan(View v){
        Intent i = new Intent(MainActivity.this,HistoryActivity2.class);
        startActivity(i);

    }


    public void alert(View v){
        Intent i = new Intent(MainActivity.this,AlertActivity.class);
        startActivity(i);

    }

}