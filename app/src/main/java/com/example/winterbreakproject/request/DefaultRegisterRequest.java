package com.example.winterbreakproject.request;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class DefaultRegisterRequest extends StringRequest {

    // 서버 URL 설정 (PHP 파일 연동)
    final static private String URL = "http://monayoung0323.dothome.co.kr/Register.php";
    private Map<String, String> map;

    public DefaultRegisterRequest(String userID, String userPassword, String userName, int userAge, Response.Listener<String> listener) {
        super(Method.POST, URL, listener, null);

        map = new HashMap<>();
        map.put("userID", userID);
        map.put("userPassword", userPassword);
        map.put("userName", userName);
        // userAge는 int형이기 때문에 문자열을 붙여 눈속임!
        map.put("userAge", userAge + "");
    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return map;
    }
}