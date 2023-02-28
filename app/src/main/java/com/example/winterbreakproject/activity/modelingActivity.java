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
        secondBtn = (ImageButton) findViewById(R.id.closet_1);
        secondBtn.setOnClickListener(listener2);



        final ImageView imageView1= findViewById(R.id.imageView);
        Bitmap icon1 = BitmapFactory.decodeResource(getResources(), R.drawable.basic);
        SharedPreferences getImg1 = getSharedPreferences("imgSave1",MODE_PRIVATE);
        String str1 = getImg1.getString("imgSave1","");

        byte[] encodeByte1 = Base64.decode(str1, Base64.DEFAULT);
        Bitmap bitmap1 = BitmapFactory.decodeByteArray(encodeByte1, 0, encodeByte1.length);
        if(bitmap1==null){
            imageView1.setImageBitmap(icon1);

        }else if(bitmap1!=null){
            imageView1.setImageBitmap(bitmap1);
        }

        Button btn = (Button) findViewById(R.id.button2);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                BitmapDrawable drawable1 = (BitmapDrawable) imageView1.getDrawable();

                Bitmap bitmap1 = drawable1.getBitmap();

                //Bitmap을 String형으로 변환
                ByteArrayOutputStream baos1= new ByteArrayOutputStream();
                bitmap1.compress(Bitmap.CompressFormat.PNG, 70, baos1);
                byte[] bytes1 = baos1.toByteArray();
                String temp1 = Base64.encodeToString(bytes1, Base64.DEFAULT);

                SharedPreferences imgSave1 = getSharedPreferences("imgSave1",MODE_PRIVATE);
                SharedPreferences.Editor editor1 = imgSave1.edit();
                editor1.putString("imgSave1", temp1);
                editor1.commit();

            }
        });
    }
}