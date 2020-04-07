package com.example.itutor.main.profile.view;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import android.text.TextUtils;

import com.example.itutor.main.MasterActivity;
import com.example.itutor.main.R;
import com.example.itutor.main.booking.view.ConfirmBookingFragment;
import com.example.itutor.main.chat.view.ChatActivity;
import com.example.itutor.main.databinding.ActivityTutorProfileBinding;
import com.example.itutor.main.model.TutorProfile;
import com.example.itutor.main.profile.presenter.TutorProfilePresenter;
import com.example.itutor.main.profile.viewmodel.TutorProfileViewModel;
import com.example.itutor.main.review.view.ReviewActivity;

public class TutorProfileActivity extends MasterActivity {

    public static final String TUTOR_ID_KEY = "TUTOR_ID_KEY";

    private TutorProfileViewModel activeModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final ActivityTutorProfileBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_tutor_profile);
        activeModel = ViewModelProviders.of(this).get(TutorProfileViewModel.class);
        String tutorID = getIntent().getStringExtra(TUTOR_ID_KEY);
        activeModel.loadProfile(tutorID);

        TutorProfilePresenter presenter = new TutorProfilePresenter();
        presenter.setPersonalTutorProfile(TextUtils.isEmpty(tutorID));
        binding.setPresenter(presenter);

        activeModel.getProfile().observe(this, new Observer<TutorProfile>() {
            @Override
            public void onChanged(@Nullable TutorProfile tutorProfile) {
                binding.setTutorProfile(activeModel.getProfileValue());
            }
        });
        binding.setTutorProfile(activeModel.getProfileValue());

        binding.bookMeeting.setOnClickListener(view -> {
            showConfirmBookingDialog();
        });

        binding.showBookedMeetings.setOnClickListener(view -> {
            showBookedMeetingsDialog();
        });

        binding.chat.setOnClickListener(view -> {
            Intent intent = new Intent(TutorProfileActivity.this, ChatActivity.class);
            intent.putExtra(ChatActivity.RECIPIENT_ID_KEY, tutorID);
            startActivity(intent);
        });

        binding.reviews.setOnClickListener(view -> {
            Intent intent = new Intent(TutorProfileActivity.this, ReviewActivity.class);
            intent.putExtra(ReviewActivity.IS_TUTOR_TAG, presenter.isPersonalTutorProfile());
            intent.putExtra(ReviewActivity.TUTOR_ID_TAG, tutorID);
            startActivity(intent);
        });
    }

    private void showConfirmBookingDialog() {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        Fragment prev = getSupportFragmentManager().findFragmentByTag("dialog");
        if (prev != null) {
            ft.remove(prev);
        }
        ft.addToBackStack(null);

        // Create and show the dialog.
        DialogFragment newFragment = new ConfirmBookingFragment();
        newFragment.show(ft, "dialog");
    }

    private void showBookedMeetingsDialog() {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        Fragment prev = getSupportFragmentManager().findFragmentByTag("bookedMeetings");
        if (prev != null) {
            ft.remove(prev);
        }
        ft.addToBackStack(null);

        // Create and show the dialog.
        DialogFragment newFragment = new BookedMeetingsFragment();
        newFragment.show(ft, "bookedMeetings");
    }
}
