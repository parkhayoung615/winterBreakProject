package com.example.winterbreakproject.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.example.winterbreakproject.R;

import java.io.ByteArrayOutputStream;

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
        secondBtn = (ImageButton) findViewById(R.id.closet_2);
        secondBtn.setOnClickListener(listener2);
        
    }
}