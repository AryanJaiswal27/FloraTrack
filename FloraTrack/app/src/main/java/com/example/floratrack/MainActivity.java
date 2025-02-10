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

}