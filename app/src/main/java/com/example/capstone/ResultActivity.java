package com.example.capstone;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.GeolocationPermissions;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.chip.Chip;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class  ResultActivity extends AppCompatActivity {
    private static final String TAG = "Warn";
    private Map<String, String> map;
    private WebView mWebView;
    private ImageButton rev, next;
    private Button result;
    private String m1,m2,m3;
    private static String loca;
    private Chip chip_1, chip_2, chip_3, chip_4, chip_5, chip_6, chip_7, chip_8, chip_9, chip_10, chip_11, chip_12, chip_13, chip_14
            , chip_15, chip_16, chip_17, chip_18;
    private float gp1=0 , gp2=0, gp3=0,gp4=0,gp5=0;

    private TextView resultGu;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        RecommendActivity recommendActivity = (RecommendActivity)RecommendActivity.recommendActivity;
        recommendActivity.finish();
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        Intent chartintent = new Intent(this, ChartActivity.class);
        Intent intent = getIntent();
        rev = findViewById(R.id.rev);
        result = findViewById(R.id.result);
        next = findViewById(R.id.next);
        resultGu = findViewById(R.id.resultGuTextView);

        mWebView = (WebView) findViewById(R.id.webview);
        chip_1 = findViewById(R.id.chip1);
        chip_2 = findViewById(R.id.chip2);
        chip_3 = findViewById(R.id.chip3);
        chip_4 = findViewById(R.id.chip4);
        chip_5 = findViewById(R.id.chip5);
        chip_6 = findViewById(R.id.chip6);
        chip_7 = findViewById(R.id.chip7);
        chip_8 = findViewById(R.id.chip8);
        chip_9 = findViewById(R.id.chip9);
        chip_10 = findViewById(R.id.chip10);
        chip_11 = findViewById(R.id.chip11);
        chip_12 = findViewById(R.id.chip12);
        chip_13 = findViewById(R.id.chip13);
        chip_14 = findViewById(R.id.chip14);
        chip_15 = findViewById(R.id.chip15);
        chip_16 = findViewById(R.id.chip16);
        chip_17 = findViewById(R.id.chip17);
        chip_18 = findViewById(R.id.chip18);
        ArrayList<String> list = new ArrayList<>();
        ArrayList<String> sel = (ArrayList<String>)  intent.getSerializableExtra("sel");
        ArrayList<String> gu = (ArrayList<String>) intent.getSerializableExtra("gu");
        ArrayList<String> chart = (ArrayList<String>) intent.getSerializableExtra("chart"); // 0부터 짝수는 구이름 홀수는 sel된 값
        ArrayList<Float> l1 = new ArrayList<>();
        ArrayList<Float> avg = new ArrayList<>();
        ArrayList<Float> group1 = new ArrayList<>();
        ArrayList<Float> group2 = new ArrayList<>();
        ArrayList<Float> group3 = new ArrayList<>();
        ArrayList<Float> group4 = new ArrayList<>();
        ArrayList<Float> group5 = new ArrayList<>();
        ArrayList<Float> chip_1 = new ArrayList<>();
        ArrayList<Float> chip_2 = new ArrayList<>();
        ArrayList<Float> chip_3 = new ArrayList<>();
        ArrayList<Float> chip_4 = new ArrayList<>();
        ArrayList<Float> chip_5 = new ArrayList<>();
        ArrayList<Float> chip_6 = new ArrayList<>();
        ArrayList<Float> chip_7 = new ArrayList<>();
        ArrayList<Float> chip_8 = new ArrayList<>();
        ArrayList<Float> chip_9 = new ArrayList<>();
        ArrayList<Float> chip_10 = new ArrayList<>();
        ArrayList<Float> chip_11 = new ArrayList<>();
        ArrayList<Float> chip_12 = new ArrayList<>();
        ArrayList<Float> chip_13 = new ArrayList<>();
        ArrayList<Float> chip_14 = new ArrayList<>();
        ArrayList<Float> chip_15 = new ArrayList<>();
        ArrayList<Float> chip_16 = new ArrayList<>();
        ArrayList<Float> chip_17 = new ArrayList<>();
        ArrayList<Float> chip_18 = new ArrayList<>();
        map = new HashMap<String, String>();
        m1 = intent.getStringExtra("m1");
        m2 = intent.getStringExtra("m2");
        m3 = intent.getStringExtra("m3");
        list.add(m1);
        list.add(m2);
        list.add(m3);
        map.put("종로구","https://www.google.co.kr/maps/place/%EC%84%9C%EC%9A%B8%ED%8A%B9%EB%B3%84%EC%8B%9C+%EC%A2%85%EB%A1%9C%EA%B5%AC/@37.6009055,126.9485624,13z/data=!3m1!4b1!4m5!3m4!1s0x357cbd2fdd279c4b:0x50c7a92e7ab85ce6!8m2!3d37.5729503!4d126.9793579?hl=ko");
        map.put("중구","https://www.google.co.kr/maps/place/%EC%84%9C%EC%9A%B8%ED%8A%B9%EB%B3%84%EC%8B%9C+%EC%A4%91%EA%B5%AC/@37.5576695,126.959146,13z/data=!3m1!4b1!4m5!3m4!1s0x357ca2fc53a1f59b:0x5f7e54ee689e9e17!8m2!3d37.5640907!4d126.9979403?hl=ko");
        map.put("용산구","https://www.google.co.kr/maps/place/%EC%84%9C%EC%9A%B8%ED%8A%B9%EB%B3%84%EC%8B%9C+%EC%9A%A9%EC%82%B0%EA%B5%AC/@37.5305156,126.9459478,13z/data=!3m1!4b1!4m5!3m4!1s0x357ca23c7e313fd3:0xa4342ca52af9b117!8m2!3d37.5384272!4d126.9654442?hl=ko");
        map.put("성동구","https://www.google.co.kr/maps/place/%EC%84%9C%EC%9A%B8%ED%8A%B9%EB%B3%84%EC%8B%9C+%EC%84%B1%EB%8F%99%EA%B5%AC/@37.5508716,127.0058758,13z/data=!3m1!4b1!4m5!3m4!1s0x357ca49fc48757e7:0x2bc64441faf7cba2!8m2!3d37.5633415!4d127.0371025?hl=ko");
        map.put("광진구","https://www.google.co.kr/maps/place/%EC%84%9C%EC%9A%B8%ED%8A%B9%EB%B3%84%EC%8B%9C+%EA%B4%91%EC%A7%84%EA%B5%AC/@37.5462703,127.068361,14z/data=!3m1!4b1!4m5!3m4!1s0x357ca528f81c1909:0xa8f1a5f74974ff31!8m2!3d37.5384843!4d127.0822938?hl=ko");
        map.put("동대문구","https://www.google.co.kr/maps/place/%EC%84%9C%EC%9A%B8%ED%8A%B9%EB%B3%84%EC%8B%9C+%EB%8F%99%EB%8C%80%EB%AC%B8%EA%B5%AC/@37.5835742,127.0330432,14z/data=!3m1!4b1!4m5!3m4!1s0x357cbb5cd4298ec1:0xe040c8bbb76d2b24!8m2!3d37.5743682!4d127.0400189?hl=ko");
        map.put("중랑구","https://www.google.co.kr/maps/place/%EC%84%9C%EC%9A%B8%ED%8A%B9%EB%B3%84%EC%8B%9C+%EC%A4%91%EB%9E%91%EA%B5%AC/@37.5950484,127.0781966,14z/data=!3m1!4b1!4m5!3m4!1s0x357cba5a7e744759:0x9d8a4df6d7c943b7!8m2!3d37.6065602!4d127.0926519?hl=ko");
        map.put("성북구","https://www.google.co.kr/maps/place/%EC%84%9C%EC%9A%B8%ED%8A%B9%EB%B3%84%EC%8B%9C+%EC%84%B1%EB%B6%81%EA%B5%AC/@37.6023243,126.9902166,13z/data=!3m1!4b1!4m5!3m4!1s0x357cbc914445e619:0x6c9b1237a957b524!8m2!3d37.589116!4d127.0182146?hl=ko");
        map.put("강북구","https://www.google.co.kr/maps/place/%EC%84%9C%EC%9A%B8%ED%8A%B9%EB%B3%84%EC%8B%9C+%EA%B0%95%EB%B6%81%EA%B5%AC/@37.6482079,126.9813874,13z/data=!3m1!4b1!4m5!3m4!1s0x357cbc1886085d81:0x524d50859b698cfc!8m2!3d37.6396099!4d127.0256575?hl=ko");
        map.put("도봉구","https://www.google.co.kr/maps/place/%EC%84%9C%EC%9A%B8%ED%8A%B9%EB%B3%84%EC%8B%9C+%EB%8F%84%EB%B4%89%EA%B5%AC/@37.6662274,126.9948531,13z/data=!3m1!4b1!4m5!3m4!1s0x357cbeb702124eeb:0x17e4d217a9b6d771!8m2!3d37.6687738!4d127.0470706?hl=ko");
        map.put("노원구","https://www.google.co.kr/maps/place/%EC%84%9C%EC%9A%B8%ED%8A%B9%EB%B3%84%EC%8B%9C+%EB%85%B8%EC%9B%90%EA%B5%AC/@37.6541905,127.0419499,13z/data=!3m1!4b1!4m5!3m4!1s0x357cb90c0b7cbb4f:0xdc9b8dbc9f220e60!8m2!3d37.6541917!4d127.056793?hl=ko");
        map.put("은평구","https://www.google.co.kr/maps/place/%EC%84%9C%EC%9A%B8%ED%8A%B9%EB%B3%84%EC%8B%9C+%EC%9D%80%ED%8F%89%EA%B5%AC/@37.6175055,126.8898972,13z/data=!3m1!4b1!4m5!3m4!1s0x357c978634080885:0xffe21430ce509646!8m2!3d37.6026957!4d126.9291119?hl=ko");
        map.put("서대문구","https://www.google.co.kr/maps/place/%EC%84%9C%EC%9A%B8%ED%8A%B9%EB%B3%84%EC%8B%9C+%EC%84%9C%EB%8C%80%EB%AC%B8%EA%B5%AC/@37.5833068,126.9006407,13z/data=!3m1!4b1!4m5!3m4!1s0x357c98688e51049d:0x1159731b249eda5c!8m2!3d37.5791158!4d126.9367789?hl=ko");
        map.put("마포구","https://www.google.co.kr/maps/place/%EC%84%9C%EC%9A%B8%ED%8A%B9%EB%B3%84%EC%8B%9C+%EB%A7%88%ED%8F%AC%EA%B5%AC/@37.5615911,126.8736237,13z/data=!3m1!4b1!4m5!3m4!1s0x357c99206b2e6821:0xc2eb5359fa1f1d05!8m2!3d37.5637561!4d126.9084211?hl=ko");
        map.put("양천구","https://www.google.co.kr/maps/place/%EC%84%9C%EC%9A%B8%ED%8A%B9%EB%B3%84%EC%8B%9C+%EC%96%91%EC%B2%9C%EA%B5%AC/@37.5274268,126.820859,13z/data=!3m1!4b1!4m5!3m4!1s0x357c9dcd357864cb:0xd363c3aa854bbed9!8m2!3d37.5168721!4d126.8663985?hl=ko");
        map.put("강서구","https://www.google.co.kr/maps/place/%EC%84%9C%EC%9A%B8%ED%8A%B9%EB%B3%84%EC%8B%9C+%EA%B0%95%EC%84%9C%EA%B5%AC/@37.5672838,126.7883912,13z/data=!3m1!4b1!4m5!3m4!1s0x357c9c89aa3d6cbd:0x9f0bd8d303ea97d1!8m2!3d37.5509786!4d126.8495382?hl=ko");
        map.put("구로구","https://www.google.co.kr/maps/place/%EC%84%9C%EC%9A%B8%ED%8A%B9%EB%B3%84%EC%8B%9C+%EA%B5%AC%EB%A1%9C%EA%B5%AC/@37.4957599,126.8228503,13z/data=!3m1!4b1!4m5!3m4!1s0x357c9dfa0f3181f7:0x37de4d5e679d54ac!8m2!3d37.4954031!4d126.887369?hl=ko");
        map.put("금천구","https://www.google.co.kr/maps/place/%EC%84%9C%EC%9A%B8%ED%8A%B9%EB%B3%84%EC%8B%9C+%EA%B8%88%EC%B2%9C%EA%B5%AC/@37.4599884,126.8837569,14z/data=!3m1!4b1!4m5!3m4!1s0x357b61c615a3d89f:0x4e8a86e9c34e96c5!8m2!3d37.4518527!4d126.9020358?hl=ko");
        map.put("영등포구","https://www.google.co.kr/maps/place/%EC%84%9C%EC%9A%B8%ED%8A%B9%EB%B3%84%EC%8B%9C+%EC%98%81%EB%93%B1%ED%8F%AC%EA%B5%AC/@37.520829,126.8783068,13z/data=!3m1!4b1!4m5!3m4!1s0x357c9f1dbf40f193:0x2cd0f2c80a840b40!8m2!3d37.5263715!4d126.8962283?hl=ko");
        map.put("동작구","https://www.google.co.kr/maps/place/%EC%84%9C%EC%9A%B8%ED%8A%B9%EB%B3%84%EC%8B%9C+%EB%8F%99%EC%9E%91%EA%B5%AC/@37.4971068,126.9093586,13z/data=!3m1!4b1!4m5!3m4!1s0x357c9f78172a8681:0x1534ad844a336498!8m2!3d37.512402!4d126.9392525?hl=ko");
        map.put("관악구","https://www.google.co.kr/maps/place/%EC%84%9C%EC%9A%B8%ED%8A%B9%EB%B3%84%EC%8B%9C+%EA%B4%80%EC%95%85%EA%B5%AC/@37.4654477,126.9092285,13z/data=!3m1!4b1!4m5!3m4!1s0x357c9ffac52c471b:0x4db3138b56ffcdc9!8m2!3d37.4784063!4d126.9516133?hl=ko");
        map.put("서초구","https://www.google.co.kr/maps/place/%EC%84%9C%EC%9A%B8%ED%8A%B9%EB%B3%84%EC%8B%9C+%EC%84%9C%EC%B4%88%EA%B5%AC/@37.4760208,127.0021826,13z/data=!3m1!4b1!4m5!3m4!1s0x357ca13466bcacd1:0xb70bc7ff482af6ab!8m2!3d37.4837121!4d127.0324112?hl=ko");
        map.put("강남구","https://www.google.co.kr/maps/place/%EC%84%9C%EC%9A%B8%ED%8A%B9%EB%B3%84%EC%8B%9C+%EA%B0%95%EB%82%A8%EA%B5%AC/@37.4968436,127.03292,13z/data=!3m1!4b1!4m5!3m4!1s0x357ca4235fb589fb:0xb331971bc570bb6a!8m2!3d37.5172363!4d127.0473248?hl=ko");
        map.put("송파구","https://www.google.co.kr/maps/place/%EC%84%9C%EC%9A%B8%ED%8A%B9%EB%B3%84%EC%8B%9C+%EC%86%A1%ED%8C%8C%EA%B5%AC/@37.5047358,127.0794455,13z/data=!3m1!4b1!4m5!3m4!1s0x357ca59cd04e4777:0x891d79c846e2cb75!8m2!3d37.5145437!4d127.1065971?hl=ko");
        map.put("강동구","https://www.google.co.kr/maps/place/%EC%84%9C%EC%9A%B8%ED%8A%B9%EB%B3%84%EC%8B%9C+%EA%B0%95%EB%8F%99%EA%B5%AC/@37.5492942,127.1114081,13z/data=!3m1!4b1!4m5!3m4!1s0x357cb01583ca5b39:0x7e69bbcaf6932c8b!8m2!3d37.5301251!4d127.123762?hl=ko");
        db.collection("nomal")
                .orderBy("지역구")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            int j=0;
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                //Log.d(TAG, document.getMetadata() + " => " + document.getData());
                                //생활·편의·교통
                                chip_1.add(Float.parseFloat(document.get("편의시설 수").toString())); //편의시설 수
                                chip_2.add(Float.parseFloat(document.get("쇼핑시설 수").toString())); //쇼핑시설 수
                                chip_3.add(Float.parseFloat(document.get("외식시설 수").toString())); //외식시설 수
                                //교육
                                chip_4.add(Float.parseFloat(document.get("고등교육기관수").toString())); //고등교육 기관 수
                                chip_5.add(Float.parseFloat(document.get("학원 수").toString()));  //학원 수
                                //복지 문화
                                chip_6.add(Float.parseFloat(document.get("유치원 및 보육시설").toString())); //유치원 및 보육시설
                                chip_7.add(Float.parseFloat(document.get("병의원 및 약국").toString())); //병의원 및 약국
                                chip_8.add(Float.parseFloat(document.get("노인복지시설").toString())); //노인복지시설
                                chip_9.add(Float.parseFloat(document.get("사회복지시설").toString())); //사회복지시설
                                chip_10.add(Float.parseFloat(document.get("문화시설 수").toString())); //문화시설 수
                                chip_11.add(Float.parseFloat(document.get("체육시설 수").toString())); //체육시설 수
                                //자연
                                chip_12.add(Float.parseFloat(document.get("대기오염").toString())); //대기오염\
                                //안전
                                chip_13.add(Float.parseFloat(document.get("교통사고").toString())); //교통사고
                                chip_14.add(Float.parseFloat(document.get("화재").toString())); //화재
                                chip_15.add(Float.parseFloat(document.get("범죄").toString())); //범죄
                                chip_16.add(Float.parseFloat(document.get("생활안전").toString())); //생활안전
                                chip_17.add(Float.parseFloat(document.get("자살").toString())); //자살
                                chip_18.add(Float.parseFloat(document.get("감염병").toString())); //감염병

                            }


                            for (int i = 0; i < 25; i++) {
                                group1.add(chip_1.get(i) + chip_2.get(i) + chip_3.get(i));//생활·편의·교통 합
                                group2.add(chip_4.get(i) + chip_5.get(i));//교육 합
                                group3.add(chip_6.get(i) + chip_7.get(i) + chip_8.get(i) + chip_9.get(i) + chip_10.get(i) + chip_11.get(i));//복지 문화 합
                                group4.add(chip_12.get(i));//자연 합
                                group5.add(1-chip_13.get(i) + 1-chip_14.get(i) + 1-chip_15.get(i) + 1-chip_16.get(i) + 1-chip_17.get(i) + 1-chip_18.get(i));//안전 합
                            }

                            for(int i =0; i<25; i++){
                                gp1 = gp1 + group1.get(i);
                                gp2 = gp2 + group2.get(i);
                                gp3 = gp3 + group3.get(i);
                                gp4 = gp4 + group4.get(i);
                                gp5 = gp5 + group5.get(i);

                            }

                            avg.add(gp1/25);
                            avg.add(gp2/25);
                            avg.add(gp3/25);
                            avg.add(gp4/25);
                            avg.add(gp5/25);



                            int m1idx = gu.indexOf(m1);
                            l1.add((group1.get(m1idx)/avg.get(0))*5);
                            l1.add((group2.get(m1idx)/avg.get(1))*5);
                            l1.add((group3.get(m1idx)/avg.get(2))*5);
                            l1.add((group4.get(m1idx)/avg.get(3))*5);
                            l1.add((group5.get(m1idx)/avg.get(4))*5);
                            int m2idx = gu.indexOf(m2);
                            l1.add((group1.get(m2idx)/avg.get(0))*5);
                            l1.add((group2.get(m2idx)/avg.get(1))*5);
                            l1.add((group3.get(m2idx)/avg.get(2))*5);
                            l1.add((group4.get(m2idx)/avg.get(3))*5);
                            l1.add((group5.get(m2idx)/avg.get(4))*5);
                            int m3idx = gu.indexOf(m3);
                            l1.add((group1.get(m3idx)/avg.get(0))*5);
                            l1.add((group2.get(m3idx)/avg.get(1))*5);
                            l1.add((group3.get(m3idx)/avg.get(2))*5);
                            l1.add((group4.get(m3idx)/avg.get(3))*5);
                            l1.add((group5.get(m3idx)/avg.get(4))*5);

                            for(int i=0; i<l1.size();i++){
                                System.out.println(l1.get(i));
                            }
                            System.out.println("chart" +chart.size() +"sel"+sel.size());

                        } else {
                            Log.w(TAG, "Error getting documents.", task.getException());
                        }
                    }
                });
        System.out.print(list);
        loca = map.get(list.get(0));
        resultGu.setText("① " + list.get(0));

        initWebView();
        rev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(loca == map.get(list.get(2))){
                    loca = map.get(list.get(1));
                    mWebView.loadUrl(loca);
                    resultGu.setText("② " + list.get(1));
                }
                else if(loca == map.get(list.get(1))){
                    loca = map.get(list.get(0));
                    mWebView.loadUrl(loca);
                    resultGu.setText("① " + list.get(0));
                }

            }

        });
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(loca == map.get(list.get(0))){
                    loca = map.get(list.get(1));
                    mWebView.loadUrl(loca);
                    resultGu.setText("② " + list.get(1));
                }
                else if(loca == map.get(list.get(1))){
                    loca = map.get(list.get(2));
                    mWebView.loadUrl(loca);
                    resultGu.setText("③ " + list.get(2));
                }

            }
        });
        result.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chartintent.putExtra("m1",m1);
                chartintent.putExtra("m2",m2);
                chartintent.putExtra("m3",m3);
                chartintent.putExtra("l1",l1);
                chartintent.putExtra("sel",sel);
                chartintent.putExtra("chart",chart);
                //chartintent.putExtra("avg",avg);
                startActivity(chartintent);
            }
        });
    }
    private void initWebView() {
        mWebView.setWebViewClient(new WebViewClient());
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.setWebViewClient(new WebViewClient());
        mWebView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onGeolocationPermissionsShowPrompt(String origin, GeolocationPermissions.Callback callback) {
                super.onGeolocationPermissionsShowPrompt(origin, callback);
                callback.invoke(origin, true, false);
            }
        });
        mWebView.loadUrl(loca);
    }


}