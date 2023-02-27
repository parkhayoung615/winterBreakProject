package com.example.winterbreakproject.activity;

import android.os.Bundle;
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

import java.util.ArrayList;
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

        // 화면 넘어가기
        clickLoad();
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