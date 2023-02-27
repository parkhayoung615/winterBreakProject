package com.example.winterbreakproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    ImageView image_1;
    public void btn_click(View view){
        image_1 = findViewById(R.id.imageView);
        if(view.getId() == R.id.closet_1){
            image_1.setImageResource(R.drawable.basic);
        } else if (view.getId() == R.id.closet_2) {
            image_1.setImageResource(R.drawable.second);
        }
    }
}