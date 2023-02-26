package com.example.winterbreakproject.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.example.winterbreakproject.R;
import com.example.winterbreakproject.request.TodayTipRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Random;

public class TodayTipActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // activity_main에 오늘의 팁 화면 넣기
        setContentView(R.layout.activity_main);

        String url = "http://monayoung0323.dothome.co.kr/TodayTip.php";
    }
}