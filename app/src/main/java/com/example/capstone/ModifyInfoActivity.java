package com.example.capstone;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class ModifyInfoActivity extends AppCompatActivity {

    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    private String name, address, address_gu, address_dong, photo_url,
            new_address_gu, new_address_dong, new_photo_url, profilePath;
    private ImageView profileImageView;
    private EditText nameText;
    private TextView locationText, emailText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_info);

        findViewById(R.id.galleryButton).setOnClickListener(onClickListener);
        findViewById(R.id.goToPasswordResetButton).setOnClickListener(onClickListener);
        findViewById(R.id.locationAuthButton).setOnClickListener(onClickListener);
        findViewById(R.id.modifyButton).setOnClickListener(onClickListener);

        profileImageView = findViewById(R.id.profileImageView);
        nameText = (EditText) findViewById(R.id.nameEditText);
        locationText = (TextView) findViewById(R.id.locationText);
        profileImageView = (ImageView) findViewById(R.id.profileImageView);
        emailText = (TextView) findViewById(R.id.emailText);
        upload();
    }
    public void upload() {
        //DB에 저장된 사용자 정보 가져옴
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        emailText.setText(user.getEmail());
        db.collection("users").document(user.getUid()).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot documentSnapshot = task.getResult();
                    name = documentSnapshot.getString("name");
                    address_dong = documentSnapshot.getString("address_dong");
                    address_gu = documentSnapshot.getString("address_gu");
                    photo_url = documentSnapshot.getString("photo_url");

                    nameText.setText(name);
                    locationText.setText(address_gu + " " + address_dong);

                    //Bitmap bmp = BitmapFactory.decodeFile(photo_url);
                    //profileImageView.setImageBitmap(bmp);
                    //Log.d("name", name);
                } else {
                    Log.d("error", "error");
                }
            }
        });
        FirebaseStorage storage = FirebaseStorage.getInstance("gs://capstonedesign-d1ced.appspot.com/");
        StorageReference storageReference = storage.getReference();

        storageReference.child("users/" + user.getUid() + "/profileImage.jpg").getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Glide.with(getApplicationContext())
                        .load(uri)
                        .centerCrop()
                        .override(500)
                        .into(profileImageView);
                Log.e("profileImage", getApplicationContext().toString());
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                profileImageView.setImageResource(R.drawable.ic_baseline_person_24);
                // Toast.makeText(getApplicationContext(), "실패", Toast.LENGTH_SHORT).show();
            }
        });
    }

    //다른 액티비티로부터 온 결과처리
    @SuppressLint("SetTextI18n")
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent resultIntent) {
        super.onActivityResult(requestCode, resultCode, resultIntent);

        switch (requestCode) {
            case 0: //이미지
                if (resultCode == Activity.RESULT_OK) {
                    profilePath = resultIntent.getStringExtra("profilePath");
                    new_photo_url = profilePath;
                    Glide.with(this).load(profilePath).centerCrop().override(500).into(profileImageView);
                }
                break;
            case 1: //위치
                if (resultCode == RESULT_OK) {
                    address = resultIntent.getStringExtra("address");

                    String split_address[] = address.split(" ");
                    for (int i = 0; i < split_address.length; i++) {
                        System.out.println(split_address[i]);
                    }
                    new_address_gu = split_address[2];
                    new_address_dong = split_address[3];
                    locationText.setText(new_address_gu + " " + new_address_dong);
                }
                break;
        }
    }

    //onClickListener
    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch(v.getId()){
                case R.id.galleryButton:
                    myStartActivity(GalleryActivity.class, "image"); //갤러리로 이동
                    break;

                case R.id.locationAuthButton:
                    myStartActivity(LocationAuthActivity.class, 1); //위치인증 페이지로 이동
                    break;

                case R.id.goToPasswordResetButton:
                    myStartActivity(PasswordResetActivity.class); //비밀번호 재설정 페이지로 이동
                    break;

                case R.id.modifyButton :
                    updateInfo(); //변경내용에 따라 DB 수정
                    break;
            }
        }
    };

    //변경내용에 따라 DB 수정
    private void updateInfo() {
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        String new_name = nameText.getText().toString();

        if(new_address_dong == null && new_address_gu == null){
            new_address_dong = address_dong;
            new_address_gu = address_gu;
        }

        db.collection("users").document(user.getUid()).update("name", new_name, "address_dong", new_address_dong, "address_gu", new_address_gu, "photo_url", new_photo_url)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        startToast("회원정보 수정을 성공하였습니다.");
                        //finish();
                        myStartActivity(MainActivity.class);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        startToast("회원정보 수정에 실패하였습니다.");
                    }
                });


        //Storage에 변경된 사진 저장
        FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference storageRef = storage.getReference();
        user = FirebaseAuth.getInstance().getCurrentUser();
        final StorageReference mountainImagesRef = storageRef.child("users/" + user.getUid() + "/profileImage.jpg");

        try {
            InputStream stream = new FileInputStream(new File(profilePath));
            UploadTask uploadTask = mountainImagesRef.putStream(stream);
            uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                @Override
                public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                    if (!task.isSuccessful()) {
                        throw task.getException();
                    }
                    return mountainImagesRef.getDownloadUrl();
                }
            }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                @Override
                public void onComplete(@NonNull Task<Uri> task) {
                    if (task.isSuccessful()) {
                        Uri downloadUri = task.getResult();

                    } else {
                        startToast("회원정보를 보내는데 실패하였습니다.");
                    }
                }
            });
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    //토스트 메시지
    private void startToast(String msg){
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }

    //다른 액티비티 실행
    private void myStartActivity(Class c) {
        Intent intent = new Intent(this, c);
        startActivity(intent);
    }
    private void myStartActivity(Class c, int requestCode) {

        Intent intent = new Intent(this, c);
        startActivityForResult(intent, requestCode);
    }
    private void myStartActivity(Class c, String media) {
        Intent intent = new Intent(this, c);
        intent.putExtra("media", media);
        startActivityForResult(intent, 0);
    }
}

