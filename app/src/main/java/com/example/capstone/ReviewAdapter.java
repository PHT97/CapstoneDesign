package com.example.capstone;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;
import static android.view.View.VISIBLE;

public class ReviewAdapter extends RecyclerView.Adapter<com.example.capstone.ReviewAdapter.GalleryViewHolder> {

    private ArrayList<ReviewInfo> mDataset;
    private ArrayList<ReviewInfo> arrayList;
    private Activity activity;
    private String userUid;

    public static class GalleryViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;

        GalleryViewHolder(CardView v) {
            super(v);
            cardView = v;
        }
    }

    public ReviewAdapter(Activity activity, ArrayList<ReviewInfo> myDataset, String userUid) {
        this.activity = activity;
        mDataset = myDataset;
        arrayList = new ArrayList<ReviewInfo>();
        arrayList.addAll(myDataset);

        this.userUid = userUid;

    }

    public void filter(String charText) {
        charText = charText.toLowerCase(Locale.getDefault());
        mDataset.clear();
        if(charText.length() == 0){
            mDataset.addAll(arrayList);
        }
        else {
            for(ReviewInfo reviewInfo : arrayList) {
                String address = reviewInfo.getAddress_gu();
                if(address.toLowerCase().contains(charText)) {
                    mDataset.add(reviewInfo);
                }
            }
        }
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public com.example.capstone.ReviewAdapter.GalleryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CardView cardView = (CardView)LayoutInflater.from(parent.getContext()).inflate(R.layout.item_review, parent, false);

        final GalleryViewHolder galleryViewHolder = new GalleryViewHolder(cardView);

        return galleryViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull GalleryViewHolder viewHolder, final int position) {
        CardView cardView = viewHolder.cardView;

        TextView titleTextView = cardView.findViewById(R.id.titleTextView);
        titleTextView.setText(mDataset.get(position).getTitle());

        TextView createdAtTextView = cardView.findViewById(R.id.createdAtTextView);
        createdAtTextView.setText(new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(mDataset.get(position).getCreatedAt()));

        TextView contentsTextView = cardView.findViewById(R.id.contentsTextView);
        contentsTextView.setText(mDataset.get(position).getContents());

        String name = mDataset.get(position).getName();
        String uid = mDataset.get(position).getUid();

        String title = mDataset.get(position).getTitle(); //*************** 추가 ****************

        TextView userNameTextView = cardView.findViewById(R.id.userNameTextView);

        TextView locationTextView = cardView.findViewById(R.id.locationTextView5);
        locationTextView.setText(mDataset.get(position).getAddress_gu());

        TextView numberOfPhotosTextField = cardView.findViewById(R.id.numberOfPhotosText);

        FirebaseFirestore db = FirebaseFirestore.getInstance();


        userNameTextView.setText(name);
        LinearLayout contentLayout = cardView.findViewById(R.id.contentsLayout2);
        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        ArrayList<String> contentsList = mDataset.get(position).getPost();


        // ******************** 추가 **************************
        /*ImageButton reviewDeleteButton= cardView.findViewById(R.id.reviewDeleteButton);
        if(userUid.equals(uid)) {
            reviewDeleteButton.setVisibility(VISIBLE);
            Log.e("review uid", userUid + uid);
        }*/
        //////////////////////////////////////////////////////////


        if(contentsList.size() != 0) {
            String contents = contentsList.get(0);
            numberOfPhotosTextField.setText(String.valueOf(contentsList.size())); // 추가

            if(Patterns.WEB_URL.matcher(contents).matches()) {
                ImageView imageView = new ImageView(activity);
                imageView.setLayoutParams(layoutParams);
                //layoutParams.height = 400;
                contentLayout.addView(imageView);
                Glide.with(activity).load(contents).centerCrop().override(500).into(imageView);

            }
            ImageView photoIcon = cardView.findViewById(R.id.photoIcon);
            photoIcon.setVisibility(VISIBLE);
        } else {
            cardView.findViewById(R.id.cardView).setVisibility(View.GONE);
        }

        cardView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity, DetailedReviewActivity.class);

                intent.putExtra("uid", mDataset.get(position).getUid());
                intent.putExtra("title", mDataset.get(position).getTitle());
                intent.putExtra("name", mDataset.get(position).getName());
                intent.putExtra("contents", mDataset.get(position).getContents());
                intent.putExtra("post", mDataset.get(position).getPost());
                intent.putExtra("address_gu", mDataset.get(position).getAddress_gu());
                intent.putExtra("createdAt", mDataset.get(position).getCreatedAt().toString());

                activity.startActivity(intent);
            }
        });

        // ******************* 추가 ******************
        /*reviewDeleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity, ReviewActivity.class);
                intent.putExtra("title", mDataset.get(position).getTitle());

                mDataset.remove(position);

                notifyItemRemoved(position);
                notifyItemRangeChanged(position, mDataset.size());

                FirebaseFirestore db = FirebaseFirestore.getInstance();

                db.collection("reviews").document(title).delete()
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Log.d("ReviewAdapter", "DocumentSnapshot successfully deleted!");
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Log.w("ReviewAdapter", "Error deleting document", e);
                            }
                        });
            }
        });
*/
    }


    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}


