package com.example.winterbreakproject.request;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class TodayTipRequest extends StringRequest {
    // 서버 URL 설정 (PHP 파일 연동)
    final static private String URL = "http://monayoung0323.dothome.co.kr/TodayTipUpdate.php";
    private Map<String, String> map;

    public TodayTipRequest(String title, Response.Listener<String> listener) {
        super(Request.Method.POST, URL, listener, null);

        map = new HashMap<>();
        map.put("title", title);
    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return map;
    }
}