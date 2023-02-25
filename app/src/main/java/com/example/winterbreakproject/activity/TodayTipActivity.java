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

        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    boolean sucess = jsonObject.getBoolean("success");
                    // sql 실행에 성공한 경우
                    if (sucess) {
                        int id = jsonObject.getInt("id");
                        String title = jsonObject.getString("title");
                        String contents = jsonObject.getString("contents");
                        Toast.makeText(getApplicationContext(), "test: 성공.", Toast.LENGTH_SHORT).show();
                        // sql 실행에 실패한 경우
                    } else {
                        Toast.makeText(getApplicationContext(), "test: 실패.", Toast.LENGTH_SHORT).show();
                        return;
                    }
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
            }
        };

        TodayTipRequest todayTipRequest = new TodayTipRequest(id, title, contents, responseListener);
        RequestQueue queue = Volley.newRequestQueue(TodayTipActivity.this);
        queue.add(todayTipRequest);
    }
}