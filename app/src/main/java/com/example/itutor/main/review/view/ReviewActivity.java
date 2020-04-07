package com.example.itutor.main.review.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.view.View;

import com.example.itutor.main.R;
import com.example.itutor.main.SessionInfo;
import com.example.itutor.main.databinding.ActivityReviewBinding;
import com.example.itutor.main.model.Review;
import com.example.itutor.main.tools.ReviewAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ReviewActivity extends AppCompatActivity {

    public static final String IS_TUTOR_TAG = "IS_TUTOR";
    public static final String TUTOR_ID_TAG = "TUTOR_ID";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityReviewBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_review);

        if (getIntent().getBooleanExtra(IS_TUTOR_TAG, false)) {
            binding.submitArea.setVisibility(View.GONE);
        } else {

            binding.submit.setOnClickListener(v -> {
                getReference().push().setValue(
                        new Review(SessionInfo.STUDENT_NAME, binding.comment.getText().toString())
                );
                binding.comment.setText("");
            });
        }

        FirebaseRecyclerOptions<Review> options =
                new FirebaseRecyclerOptions.Builder<Review>()
                        .setQuery(getReference(), Review.class)
                        .setLifecycleOwner(this)
                        .build();

        binding.recycler.setAdapter(new ReviewAdapter(options));
        binding.recycler.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.HORIZONTAL));

    }

    private DatabaseReference getReference() {
        String tutorID = getIntent().getStringExtra(TUTOR_ID_TAG);
        if (tutorID == null)
            tutorID = FirebaseAuth.getInstance().getUid();

        return FirebaseDatabase.getInstance().getReference()
                .child("users")
                .child(tutorID)
                .child("tutorProfile")
                .child("reviews");
    }
}
