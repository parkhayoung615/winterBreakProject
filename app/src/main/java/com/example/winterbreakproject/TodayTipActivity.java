package com.example.winterbreakproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import java.util.Random;

public class TodayTipActivity extends AppCompatActivity {

    /*
        Select *
        From today_tip
        Order by rand()
        Limit 1
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_today_tip);
    }
}