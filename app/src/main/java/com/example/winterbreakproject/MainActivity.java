package com.example.winterbreakproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    private TextView tv_outPut;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 위젯에 대한 참조
        tv_outPut = (TextView) findViewById(R.id.tv_outPut);

        // URL 설정
        String service_key = "nQAxEQckNcvmZ8ixYcO9rlFBnv5VN1bv5hkI5vufYQNHEf80yngnldtLbyQryYCrRVm%2FX8Tc0Xa0AkVaulqrNQ%3D%3D";
        String num_of_rows = "10";
        String page_no = "1";
        String date_type = "JSON";
        String base_date = "20210410";
        String base_time = "0600";

        // 서울 좌표
        String nx = "55";
        String ny = "127";

        String url = "\uFEFFhttp://apis.data.go.kr/1360000/VilageFcstInfoService_2.0/getUltraSrtNcst?" +
                "serviceKey=" + service_key +
                "&numOfRows=" + num_of_rows +
                "&pageNo=" + page_no +
                "&dataType=" + date_type +
                "&base_date=" + base_date +
                "&base_time=" + base_time +
                "&nx=" + nx +
                "&ny=" + ny;

        // AsyncTask를 통해 HttpURLConnection 수행
        NetworkTask networkTask = new NetworkTask(url, null);
        networkTask.execute();
    }


    public class NetworkTask extends AsyncTask<Void, Void, String> {

        private String url;
        private ContentValues values;

        public NetworkTask(String url, ContentValues values) {
            this.url = url;
            this.values = values;
        }

        @Override
        protected String doInBackground(Void... params) {
            String result; // 요청 결과를 저장할 변수.
            RequestHttpConnection requestHttpConnection = new RequestHttpConnection();
            result = requestHttpConnection.request(url, values); // 해당 URL로 부터 결과

            return result;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            //doInBackgroud()로 부터 리턴된 값이 onPostExecute()의 매개변수로 넘어오므로..
            tv_outPut.setText(s);
            Log.d("onPostEx", "출력 값 : " + s);
        }
    }

}

