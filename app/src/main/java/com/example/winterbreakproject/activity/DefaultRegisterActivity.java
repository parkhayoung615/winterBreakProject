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
import com.example.winterbreakproject.request.DefaultRegisterRequest;

import org.json.JSONException;
import org.json.JSONObject;

public class DefaultRegisterActivity extends AppCompatActivity {

    private EditText et_id, et_pass, et_name, et_age;
    private Button btn_register;

    // 액티비티 시작 시 처음으로 실행되는 생명주기
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // activity_main에 회원가입 화면 넣기
        setContentView(R.layout.activity_main);

        // 아이디 값 찾기
        /*
        et_id = findViewById(R.id.et_id);
        et_pass = findViewById(R.id.et_pass);
        et_name = findViewById(R.id.et_name);
        et_age = findViewById(R.id.et_age);
        */

        // 회원가입 버튼 클릭 시 수행
        // btn_register = findViewById(R.id.btn_register);
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // EditText에 현재 입력 되어있는 값을 가져온다.
                String userID = et_id.getText().toString();
                String userPass = et_pass.getText().toString();
                String userName = et_name.getText().toString();
                int userAge = Integer.parseInt(et_age.getText().toString());

                Response.Listener<String> respoListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            boolean sucess = jsonObject.getBoolean("success");
                            // 회원 등록에 성공한 경우
                            if (sucess) {
                                Toast.makeText(getApplicationContext(), "회원 등록에 성공하였습니다.", Toast.LENGTH_SHORT).show();
                                // 화면 전환?
                                Intent intent = new Intent(DefaultRegisterActivity.this, DefaultLoginActivity.class);
                                startActivity(intent);
                                // 회원 등록에 실패한 경우
                            } else {
                                Toast.makeText(getApplicationContext(), "회원 등록에 실패하였습니다.", Toast.LENGTH_SHORT).show();
                                return;
                            }
                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }
                    }
                };

                // 서버로 Volley를 이용해서 요청한다.
                DefaultRegisterRequest defaultRegisterRequest = new DefaultRegisterRequest(userID, userPass, userName, userAge, respoListener);
                RequestQueue queue = Volley.newRequestQueue(DefaultRegisterActivity.this);
                queue.add(defaultRegisterRequest);
            }
        });
    }
}