package com.example.winterbreakproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

public class modelingActivity extends AppCompatActivity {

    ImageButton basicBtn;
    ImageButton secondBtn;
    ImageView modelingImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_3d);

        modelingImg = (ImageView) findViewById(R.id.imageView);

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                modelingImg.setImageResource(R.drawable.basic);
            }
        };
        basicBtn = (ImageButton) findViewById(R.id.closet_1);
        basicBtn.setOnClickListener(listener);

        View.OnClickListener listener2 = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                modelingImg.setImageResource(R.drawable.second);
            }
        };
        secondBtn = (ImageButton) findViewById(R.id.closet_1);
        secondBtn.setOnClickListener(listener2);
    }
}