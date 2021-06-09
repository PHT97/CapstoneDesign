package com.example.capstone;

import android.content.Intent;
import android.icu.text.IDNA;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    private RecyclerView recyclerView;
    private ArrayList<ReviewInfo> reviewList;

    private TextView goToReviewTextView, goToInfoTextView;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        goToReviewTextView = findViewById(R.id.goToReviewTextView);
        goToInfoTextView = findViewById(R.id.infoButton);
        goToReviewTextView.setText(Html.fromHtml("<u>" + goToReviewTextView.getText() + "</u>"));
        goToInfoTextView.setText(Html.fromHtml("<u>" + goToInfoTextView.getText() + "</u>"));

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));

        findViewById(R.id.goToReviewTextView).setOnClickListener(onClickListener);
        findViewById(R.id.recommendButton).setOnClickListener(onClickListener);
        findViewById(R.id.infoButton).setOnClickListener(onClickListener);  //추가

        //로그인되어 있지 않으면 로그인 페이지로 이동
        if (user == null) {
            myStartActivity(LoginActivity.class);
        }

        //로그인되어 있으면 DB에 저장된 사용자 정보 및 리뷰 정보 가져옴
        else {
            final RelativeLayout loaderLayout = findViewById(R.id.loaderLayout);
            loaderLayout.setVisibility(View.VISIBLE);

            //사용자 정보
            DocumentReference docRef = db.collection("users").document(user.getUid());
            docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                    if (task.isSuccessful()) {
                        DocumentSnapshot document = task.getResult();
                        if (document != null) {
                            if (document.exists()) {
                                Log.d(TAG, "DocumentSnapshot data: " + document.getData());
                            } else {
                                Log.d(TAG, "No such document");
                            }
                        }
                    } else {
                        Log.d(TAG, "get failed with ", task.getException());
                    }
                }
            });

            //리뷰 정보
            CollectionReference colRef = db.collection("reviews");
            colRef.orderBy("createdAt", Query.Direction.DESCENDING).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @RequiresApi(api = Build.VERSION_CODES.N)
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    if (task.isSuccessful()) {
                        loaderLayout.setVisibility(View.GONE);

                        reviewList = new ArrayList<>();
                        int i = 0;
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            if (i == 3) break;
                            i++;
                            reviewList.add(new ReviewInfo(
                                    (String) document.getData().get("uid"),
                                    document.getData().get("name").toString(),
                                    document.getData().get("title").toString(),
                                    document.getData().get("contents").toString(),
                                    (ArrayList<String>) document.getData().get("post"),
                                    document.getData().get("address_gu").toString(),
                                    new Date(document.getDate("createdAt").getTime())));
                        }
                        RecyclerView.Adapter nAdapter = new ExReviewAdapter(MainActivity.this, reviewList);
                        recyclerView.setAdapter(nAdapter);
                    } else {
                        Log.d(TAG, "Error getting documents : ", task.getException());
                    }
                }
            });
        }
    }

    //onClickListener
    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.goToReviewTextView:
                    myStartActivity(ReviewActivity.class);  //리뷰 게시판으로 이동
                    break;

                case R.id.floatingActionButton:
                    myStartActivity(WriteReviewActivity.class); //리뷰 작성 페이지로 이동
                    break;

                case R.id.recommendButton:
                    myStartActivity(RecommendActivity.class); //추천 게시판으로 이동
                    break;
                //추가
                case R.id.infoButton:
                    myStartActivity(InfoActivity.class); //마이페이지로 이동
            }
        }
    };

    //다른 액티비티 실행
    private void myStartActivity(Class c) {
        Intent intent = new Intent(this, c);
        startActivity(intent);
    }

    /*//옵션메뉴
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
                myStartActivity(InfoActivity.class); //마이페이지로 이동
                break;
        }
        return super.onOptionsItemSelected(item);
    }*/
}