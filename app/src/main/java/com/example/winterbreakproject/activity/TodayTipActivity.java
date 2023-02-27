package com.example.winterbreakproject.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.winterbreakproject.R;
import com.example.winterbreakproject.dao.TodayTipDAO;
import com.example.winterbreakproject.vo.TodayTipVO;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

public class TodayTipActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    TodayTipDAO dao;
    ArrayList<TodayTipVO> voArrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_today_tip);

        recyclerView = findViewById(R.id.recycler);
        dao = new TodayTipDAO(this, voArrayList);
        recyclerView.setAdapter(dao);

        // 리사이클러뷰의 레이아웃 매니저 설정
        LinearLayoutManager layoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManager);

        /*
        // 사용자가 오늘의 팁을 본 날짜와 시각이 기록되는 ArrayList
        ArrayList<Date> dateRecord = new ArrayList<Date>();

        // 오늘의 팁을 본 적이 없다면
        if (dateRecord.isEmpty()) {
            Log.v("기록 전 날짜", dateRecord.toString());
            // 현재 날짜와 시각 가져오기
            long now = System.currentTimeMillis();
            Date date = new Date(now);
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
            simpleDateFormat.format(date);
            // 날짜를 기록하기
            dateRecord.add(date);
            Log.v("기록 후 날짜", dateRecord.toString());
            // 오늘의 팁 화면으로 전환
            clickLoad();
            Log.v("test", "오늘의 팁을 본 적이 없다면");
        } else {
            // 마지막으로 오늘의 팁을 본 날짜와 시각 가져오기
            Date beforeRecord = dateRecord.get(0);
            // 현재 날짜와 시각 가져오기
            long now = System.currentTimeMillis();
            Date date = new Date(now);
            // 두 날짜를 비교하고 마지막으로 본 날짜가 현재 날짜보다 이전이면 true 반환
            boolean result = beforeRecord.before(date);
            Log.v("test", "오늘의 팁을 본 적이 있다면");
            if (result) {
                // 원래 있던 날짜 삭제
                dateRecord.remove(0);
                // 후에 오늘 날짜를 기록하기
                dateRecord.add(date);
                // 오늘의 팁 화면으로 전환
                clickLoad();
                Log.v("test", "오늘의 팁을 본 적이 있고 날짜가 지났다면");
            }
        }
        */
    }
    public void clickLoad() {
        // Volley+ 라이브러리를 사용해 서버의 TodayTip.php에 접속하여 DB 데이터 받기
        String serverUrl = "http://monayoung0323.dothome.co.kr/TodayTip.php";

        // 결과를 JsonArray로 받을 것이므로 StringRequest가 아니라 JsonArrayRequest를 이용
        JsonArrayRequest jsonArrayRequest= new JsonArrayRequest(Request.Method.POST, serverUrl, null, new Response.Listener<JSONArray>() {
            //volley 라이브러리의 GET 방식은 버튼 누를 때마다 새로운 갱신 데이터를 불러들이지 않기 때문에 POST 방식을 사용
            @Override
            public void onResponse(JSONArray response) {
                // Toast.makeText(TodayTipActivity.this, response.toString(), Toast.LENGTH_SHORT).show();

                // 파라미터로 응답받은 결과인 JsonArray를 분석
                voArrayList.clear();
                dao.notifyDataSetChanged();

                try {
                    for(int i = 0; i < response.length(); i++){
                        JSONObject jsonObject = response.getJSONObject(i);

                        int id = Integer.parseInt(jsonObject.getString("id")); // id가 int형이기에 문자열로 바꿔야 함
                        String title = jsonObject.getString("title");
                        String contents = jsonObject.getString("contents");
                        String image = "http://monayoung0323.dothome.co.kr/" + jsonObject.getString("image");
                        voArrayList.add(0, new TodayTipVO(id, title, contents, image)); // 첫번째 매개변수는 몇 번째에 추가될 지, 제일 위에 오도록
                        dao.notifyItemInserted(0);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // Toast.makeText(TodayTipActivity.this, "ERROR", Toast.LENGTH_SHORT).show();
            }
        });

        // 실제 요청 작업을 수행해 주는 요청 큐 객체 생성
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        //요청 큐에 요청 객체 생성
        requestQueue.add(jsonArrayRequest);
    }
}