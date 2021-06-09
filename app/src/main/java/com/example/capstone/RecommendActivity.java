package com.example.capstone;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.chip.Chip;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.transform.Result;

public class RecommendActivity extends AppCompatActivity {

    private static final String TAG = "Warn";
    private Chip chip_1, chip_2, chip_3, chip_4, chip_5, chip_6, chip_7, chip_8, chip_9,
            chip_10, chip_11, chip_12, chip_13, chip_14, chip_15, chip_16, chip_17, chip_18;
    private Button confirmButton;
    private Map<String, Integer> mchip1,mchip2,mchip3,mchip4,mchip5,mchip6,mchip7,mchip8,mchip9,mchip10,mchip11,mchip12,mchip13,mchip14,mchip15,mchip16,mchip17,mchip18;
    public static Activity recommendActivity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recommend);
        Intent intent = new Intent(this, ResultActivity.class);
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        recommendActivity = RecommendActivity.this;
        recommendActivity = RecommendActivity.this;
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
        confirmButton = findViewById(R.id.confirmButton);

        ArrayList<String> sel = new ArrayList<>();
        ArrayList<String> gu = new ArrayList<>();
        ArrayList<String> regu = new ArrayList<>();
        ArrayList<String> chart = new ArrayList<>(); //막대그래프 사용 데이터 <구, 선택된 요소>
        ArrayList<Integer> chip1 = new ArrayList<>();
        ArrayList<Integer> chip2 = new ArrayList<>();
        ArrayList<Integer> chip3 = new ArrayList<>();
        ArrayList<Integer> chip4 = new ArrayList<>();
        ArrayList<Integer> chip5 = new ArrayList<>();
        ArrayList<Integer> chip6 = new ArrayList<>();
        ArrayList<Integer> chip7 = new ArrayList<>();
        ArrayList<Integer> chip8 = new ArrayList<>();
        ArrayList<Integer> chip9 = new ArrayList<>();
        ArrayList<Integer> chip10 = new ArrayList<>();
        ArrayList<Integer> chip11 = new ArrayList<>();
        ArrayList<Integer> chip12 = new ArrayList<>();
        ArrayList<Integer> chip13 = new ArrayList<>();
        ArrayList<Integer> chip14 = new ArrayList<>();
        ArrayList<Integer> chip15 = new ArrayList<>();
        ArrayList<Integer> chip16 = new ArrayList<>();
        ArrayList<Integer> chip17 = new ArrayList<>();
        ArrayList<Integer> chip18 = new ArrayList<>();
        int[] result = new int[25];
        Map<String, Integer> map = new HashMap<String, Integer>();


        db.collection("data")
                .orderBy("지역구")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                //Log.w(TAG, document.get("지역구").toString() + document.get(binding.chip3.getText() +"R"));
                                gu.add(document.get("지역구").toString());
                                chip1.add(Integer.parseInt(document.get(chip_1.getText() +"R").toString()));
                                chip2.add(Integer.parseInt(document.get(chip_2.getText() +"R").toString()));
                                chip3.add(Integer.parseInt(document.get(chip_3.getText() +"R").toString()));
                                chip4.add(Integer.parseInt(document.get(chip_4.getText() +"R").toString()));
                                chip5.add(Integer.parseInt(document.get(chip_5.getText() +"R").toString()));
                                chip6.add(Integer.parseInt(document.get(chip_6.getText() +"R").toString()));
                                chip7.add(Integer.parseInt(document.get(chip_7.getText() +"R").toString()));
                                chip8.add(Integer.parseInt(document.get(chip_8.getText() +"R").toString()));
                                chip9.add(Integer.parseInt(document.get(chip_9.getText() +"R").toString()));
                                chip10.add(Integer.parseInt(document.get(chip_10.getText() +"R").toString()));
                                chip11.add(Integer.parseInt(document.get(chip_11.getText() +"R").toString()));
                                chip12.add(Integer.parseInt(document.get(chip_12.getText() +"R").toString()));
                                chip13.add(Integer.parseInt(document.get(chip_13.getText() +"R").toString()));
                                chip14.add(Integer.parseInt(document.get(chip_14.getText() +"R").toString()));
                                chip15.add(Integer.parseInt(document.get(chip_15.getText() +"R").toString()));
                                chip16.add(Integer.parseInt(document.get(chip_16.getText() +"R").toString()));
                                chip17.add(Integer.parseInt(document.get(chip_17.getText() +"R").toString()));
                                chip18.add(Integer.parseInt(document.get(chip_18.getText() +"R").toString()));

                            }

                            confirmButton.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    if(chip_1.isChecked()){
                                        mchip1 = new HashMap<>();
                                        for(int i=0; i<25; i++){
                                            result[i] = result[i] + chip1.get(i);
                                            mchip1.put(gu.get(i),chip1.get(i));
                                        }
                                        List<String> keySetList1 = new ArrayList<>(mchip1.keySet());
                                        Collections.sort(keySetList1, (o1, o2) -> (mchip1.get(o2).compareTo(mchip1.get(o1))));
                                        for(String key : keySetList1) {
                                            chart.add(key);
                                            chart.add(mchip1.get(key).toString());
                                        }
                                        sel.add(chip_1.getText().toString());
                                    }

                                    if(chip_2.isChecked()){
                                        mchip2 = new HashMap<>();
                                        for(int i=0; i<25; i++){
                                            result[i] = result[i] + chip2.get(i);
                                            mchip2.put(gu.get(i),chip2.get(i));
                                        }
                                        List<String> keySetList2 = new ArrayList<>(mchip2.keySet());
                                        Collections.sort(keySetList2, (o1, o2) -> (mchip2.get(o2).compareTo( mchip2.get(o1))));
                                        for(String key : keySetList2) {
                                            chart.add(key);
                                            chart.add(mchip2.get(key).toString());
                                        }
                                        sel.add(chip_2.getText().toString());
                                    }

                                    if(chip_3.isChecked()){
                                        mchip3 = new HashMap<>();
                                        for(int i=0; i<25; i++){
                                            result[i] = result[i] + chip3.get(i);
                                            mchip3.put(gu.get(i),chip3.get(i));
                                        }
                                        List<String> keySetList3 = new ArrayList<>(mchip3.keySet());
                                        Collections.sort(keySetList3, (o1, o2) -> (mchip3.get(o2).compareTo( mchip3.get(o1))));
                                        for(String key : keySetList3) {
                                            chart.add(key);
                                            chart.add(mchip3.get(key).toString());
                                        }
                                        sel.add(chip_3.getText().toString());
                                    }
                                    if(chip_4.isChecked()){
                                        mchip4 = new HashMap<>();
                                        for(int i=0; i<25; i++){
                                            result[i] = result[i] + chip4.get(i);
                                            mchip4.put(gu.get(i),chip4.get(i));
                                        }
                                        List<String> keySetList4 = new ArrayList<>(mchip4.keySet());
                                        Collections.sort(keySetList4, (o1, o2) -> (mchip4.get(o2).compareTo( mchip4.get(o1))));
                                        for(String key : keySetList4) {
                                            chart.add(key);
                                            chart.add(mchip4.get(key).toString());
                                        }
                                        sel.add(chip_4.getText().toString());
                                    }

                                    if(chip_5.isChecked()){
                                        mchip5 = new HashMap<>();
                                        for(int i=0; i<25; i++){
                                            result[i] = result[i] + chip5.get(i);
                                            mchip5.put(gu.get(i),chip5.get(i));
                                        }
                                        List<String> keySetList5 = new ArrayList<>(mchip5.keySet());
                                        Collections.sort(keySetList5, (o1, o2) -> (mchip5.get(o2).compareTo( mchip5.get(o1))));
                                        for(String key : keySetList5) {
                                            chart.add(key);
                                            chart.add(mchip5.get(key).toString());
                                        }
                                        sel.add(chip_5.getText().toString());
                                    }

                                    if(chip_6.isChecked()){
                                        mchip6 = new HashMap<>();
                                        for(int i=0; i<25; i++){
                                            result[i] = result[i] + chip6.get(i);
                                            mchip6.put(gu.get(i),chip6.get(i));
                                        }
                                        List<String> keySetList6 = new ArrayList<>(mchip6.keySet());
                                        Collections.sort(keySetList6, (o1, o2) -> (mchip6.get(o2).compareTo( mchip6.get(o1))));
                                        for(String key : keySetList6) {
                                            chart.add(key);
                                            chart.add(mchip6.get(key).toString());
                                        }
                                        sel.add(chip_6.getText().toString());
                                    }

                                    if(chip_7.isChecked()){
                                        mchip7 = new HashMap<>();
                                        for(int i=0; i<25; i++){
                                            result[i] = result[i] + chip7.get(i);
                                            mchip7.put(gu.get(i),chip7.get(i));
                                        }
                                        List<String> keySetList7 = new ArrayList<>(mchip7.keySet());
                                        Collections.sort(keySetList7, (o1, o2) -> (mchip7.get(o2).compareTo( mchip7.get(o1))));
                                        for(String key : keySetList7) {
                                            chart.add(key);
                                            chart.add(mchip7.get(key).toString());
                                        }
                                        sel.add(chip_7.getText().toString());
                                    }

                                    if(chip_8.isChecked()){
                                        mchip8 = new HashMap<>();
                                        for(int i=0; i<25; i++){
                                            result[i] = result[i] + chip8.get(i);
                                            mchip8.put(gu.get(i),chip8.get(i));
                                        }
                                        List<String> keySetList8 = new ArrayList<>(mchip8.keySet());
                                        Collections.sort(keySetList8, (o1, o2) -> (mchip8.get(o2).compareTo( mchip8.get(o1))));
                                        for(String key : keySetList8) {
                                            chart.add(key);
                                            chart.add(mchip8.get(key).toString());
                                        }
                                        sel.add(chip_8.getText().toString());
                                    }

                                    if(chip_9.isChecked()){
                                        mchip9 = new HashMap<>();
                                        for(int i=0; i<25; i++){
                                            result[i] = result[i] + chip9.get(i);
                                            mchip9.put(gu.get(i),chip9.get(i));
                                        }
                                        List<String> keySetList9 = new ArrayList<>(mchip9.keySet());
                                        Collections.sort(keySetList9, (o1, o2) -> (mchip9.get(o2).compareTo( mchip9.get(o1))));
                                        for(String key : keySetList9) {
                                            chart.add(key);
                                            chart.add(mchip9.get(key).toString());
                                        }
                                        sel.add(chip_9.getText().toString());
                                    }

                                    if(chip_10.isChecked()){
                                        mchip10 = new HashMap<>();
                                        for(int i=0; i<25; i++){
                                            result[i] = result[i] + chip10.get(i);
                                            mchip10.put(gu.get(i),chip10.get(i));
                                        }
                                        List<String> keySetList10 = new ArrayList<>(mchip10.keySet());
                                        Collections.sort(keySetList10, (o1, o2) -> (mchip10.get(o2).compareTo( mchip10.get(o1))));
                                        for(String key : keySetList10) {
                                            chart.add(key);
                                            chart.add(mchip10.get(key).toString());
                                        }
                                        sel.add(chip_10.getText().toString());
                                    }

                                    if(chip_11.isChecked()){
                                        mchip11 = new HashMap<>();
                                        for(int i=0; i<25; i++){
                                            result[i] = result[i] + chip11.get(i);
                                            mchip11.put(gu.get(i),chip11.get(i));
                                        }
                                        List<String> keySetList11 = new ArrayList<>(mchip11.keySet());
                                        Collections.sort(keySetList11, (o1, o2) -> (mchip11.get(o2).compareTo( mchip11.get(o1))));
                                        for(String key : keySetList11) {
                                            chart.add(key);
                                            chart.add(mchip11.get(key).toString());
                                        }
                                        sel.add(chip_11.getText().toString());
                                    }

                                    if(chip_12.isChecked()){
                                        mchip12 = new HashMap<>();
                                        for(int i=0; i<25; i++){
                                            result[i] = result[i] + chip12.get(i);
                                            mchip12.put(gu.get(i),chip12.get(i));
                                        }
                                        List<String> keySetList12 = new ArrayList<>(mchip12.keySet());
                                        Collections.sort(keySetList12, (o1, o2) -> (mchip12.get(o2).compareTo( mchip12.get(o1))));
                                        for(String key : keySetList12) {
                                            chart.add(key);
                                            chart.add(mchip12.get(key).toString());
                                        }
                                        sel.add(chip_12.getText().toString());
                                    }

                                    if(chip_13.isChecked()){
                                        mchip13 = new HashMap<>();
                                        for(int i=0; i<25; i++){
                                            result[i] = result[i] + chip13.get(i);
                                            mchip13.put(gu.get(i),chip13.get(i));
                                        }
                                        List<String> keySetList13 = new ArrayList<>(mchip13.keySet());
                                        Collections.sort(keySetList13, (o1, o2) -> (mchip13.get(o2).compareTo( mchip13.get(o1))));
                                        for(String key : keySetList13) {
                                            chart.add(key);
                                            chart.add(mchip13.get(key).toString());
                                        }
                                        sel.add(chip_13.getText().toString());
                                    }

                                    if(chip_14.isChecked()){
                                        mchip14 = new HashMap<>();
                                        for(int i=0; i<25; i++){
                                            result[i] = result[i] + chip14.get(i);
                                            mchip14.put(gu.get(i),chip14.get(i));
                                        }
                                        List<String> keySetList14 = new ArrayList<>(mchip14.keySet());
                                        Collections.sort(keySetList14, (o1, o2) -> (mchip14.get(o2).compareTo( mchip14.get(o1))));
                                        for(String key : keySetList14) {
                                            chart.add(key);
                                            chart.add(mchip14.get(key).toString());
                                        }
                                        sel.add(chip_14.getText().toString());
                                    }

                                    if(chip_15.isChecked()){
                                        mchip15 = new HashMap<>();
                                        for(int i=0; i<25; i++){
                                            result[i] = result[i] + chip15.get(i);
                                            mchip15.put(gu.get(i),chip15.get(i));
                                        }
                                        List<String> keySetList15 = new ArrayList<>(mchip15.keySet());
                                        Collections.sort(keySetList15, (o1, o2) -> (mchip15.get(o2).compareTo( mchip15.get(o1))));
                                        for(String key : keySetList15) {
                                            chart.add(key);
                                            chart.add(mchip15.get(key).toString());
                                        }
                                        sel.add(chip_15.getText().toString());
                                    }

                                    if(chip_16.isChecked()){
                                        mchip16 = new HashMap<>();
                                        for(int i=0; i<25; i++){
                                            result[i] = result[i] + chip16.get(i);
                                            mchip16.put(gu.get(i),chip16.get(i));
                                        }
                                        List<String> keySetList16 = new ArrayList<>(mchip16.keySet());
                                        Collections.sort(keySetList16, (o1, o2) -> (mchip16.get(o2).compareTo( mchip16.get(o1))));
                                        for(String key : keySetList16) {
                                            chart.add(key);
                                            chart.add(mchip16.get(key).toString());
                                        }
                                        sel.add(chip_16.getText().toString());
                                    }

                                    if(chip_17.isChecked()){
                                        mchip17 = new HashMap<>();
                                        for(int i=0; i<25; i++){
                                            result[i] = result[i] + chip17.get(i);
                                            mchip17.put(gu.get(i),chip17.get(i));
                                        }
                                        List<String> keySetList17 = new ArrayList<>(mchip17.keySet());
                                        Collections.sort(keySetList17, (o1, o2) -> (mchip17.get(o2).compareTo( mchip17.get(o1))));
                                        for(String key : keySetList17) {
                                            chart.add(key);
                                            chart.add(mchip17.get(key).toString());
                                        }
                                        sel.add(chip_17.getText().toString());
                                    }

                                    if(chip_18.isChecked()){
                                        mchip18 = new HashMap<>();
                                        for(int i=0; i<25; i++){
                                            result[i] = result[i] + chip18.get(i);
                                            mchip18.put(gu.get(i),chip18.get(i));
                                        }
                                        List<String> keySetList18 = new ArrayList<>(mchip18.keySet());
                                        Collections.sort(keySetList18, (o1, o2) -> (mchip18.get(o2).compareTo( mchip18.get(o1))));
                                        for(String key : keySetList18) {
                                            chart.add(key);
                                            chart.add(mchip18.get(key).toString());
                                        }
                                        sel.add(chip_18.getText().toString());
                                    }

                                    for (int i = 0; i < gu.size(); i++) {
                                        map.put(gu.get(i),result[i]);
                                    }

                                    System.out.println(chart);
                                    List<String> keySetList = new ArrayList<>(map.keySet());



                                    // 내림차순
                                    //System.out.println("------value 내림차순------");
                                    Collections.sort(keySetList, (o1, o2) -> (map.get(o2).compareTo(map.get(o1))));

                                    for(String key : keySetList) {
                                        //System.out.println("key : " + key + " / " + "value : " + map.get(key));
                                        regu.add(key);
                                    }

                                    if(!chip_1.isChecked() && !chip_2.isChecked() && !chip_3.isChecked() && !chip_4.isChecked() && !chip_5.isChecked() && !chip_6.isChecked()
                                            && !chip_7.isChecked() && !chip_8.isChecked() && !chip_9.isChecked() && !chip_10.isChecked() && !chip_11.isChecked() && !chip_12.isChecked()
                                            && !chip_13.isChecked() && !chip_14.isChecked() && !chip_15.isChecked() && !chip_16.isChecked() && !chip_17.isChecked() && !chip_18.isChecked() ){
                                        Toast.makeText(getApplicationContext(), "조건을 선택해주세요.", Toast.LENGTH_SHORT).show();
                                    } else {
                                        intent.putExtra("m1",regu.get(0));
                                        intent.putExtra("m2",regu.get(1));
                                        intent.putExtra("m3",regu.get(2));
                                        intent.putExtra("sel",sel);
                                        intent.putExtra("chart",chart);
                                        intent.putExtra("gu",gu);
                                        startActivity(intent);

                                    }

                                }
                            });
                        } else {
                            Log.w(TAG, "Error getting documents.", task.getException());
                        }
                    }
                });
    }

    private void myStartActivity(Class c) {
        Intent intent = new Intent(this, c);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.mainmenu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu:
                myStartActivity(InfoActivity.class);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}


