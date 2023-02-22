package com.example.winterbreakproject.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.example.winterbreakproject.R;
import com.example.winterbreakproject.request.DefaultLoginRequest;

import org.json.JSONException;
import org.json.JSONObject;

public class DefaultLoginActivity extends AppCompatActivity {

    private EditText et_id, et_pass;
    private Button btn_login, btn_register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // activity_main에 로그인 화면 넣기
        setContentView(R.layout.activity_main);

        // 아이디 값 찾기
        // et_id = findViewById(R.id.et_id);
        // et_pass = findViewById(R.id.et_pass);

        // 회원가입 버튼 클릭 시 회원가입 화면으로 전환환다.
        // btn_register = findViewById(R.id.btn_register);
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DefaultLoginActivity.this, DefaultRegisterActivity.class);
                startActivity(intent);
            }
        });

        // btn_login = findViewById(R.id.btn_login);
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userID = et_id.getText().toString();
                String userPass = et_pass.getText().toString();

                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            boolean sucess = jsonObject.getBoolean("success");
                            // 로그인에 성공한 경우
                            if (sucess) {
                                String userID = jsonObject.getString("userID");
                                String userPass = jsonObject.getString("userPassword");

                                Toast.makeText(getApplicationContext(), "로그인에 성공하였습니다.", Toast.LENGTH_SHORT).show();
                                // 화면 전환?
                                Intent intent = new Intent(DefaultLoginActivity.this, MainActivity.class);
                                intent.putExtra("userID", userID);
                                intent.putExtra("userPass", userPass);
                                startActivity(intent);
                                // 로그인에 실패한 경우
                            } else {
                                Toast.makeText(getApplicationContext(), "로그인에 실패하였습니다.", Toast.LENGTH_SHORT).show();
                                return;
                            }
                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }
                    }
                };

                DefaultLoginRequest defaultLoginRequest = new DefaultLoginRequest(userID, userPass, responseListener);
                RequestQueue queue = Volley.newRequestQueue(DefaultLoginActivity.this);
                queue.add(defaultLoginRequest);
            }
        });
    }
}