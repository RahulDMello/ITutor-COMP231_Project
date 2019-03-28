package com.example.itutor.main.profile.view;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.example.itutor.main.MasterActivity;
import com.example.itutor.main.R;
import com.example.itutor.main.databinding.ActivityTutorProfileBinding;
import com.example.itutor.main.model.TutorProfile;
import com.example.itutor.main.profile.viewmodel.TutorProfileViewModel;

public class TutorProfileActivity extends MasterActivity {

    public static final String TUTOR_ID_KEY = "TUTOR_ID_KEY";

    private TutorProfileViewModel activeModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final ActivityTutorProfileBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_tutor_profile);
        activeModel = ViewModelProviders.of(this).get(TutorProfileViewModel.class);
        activeModel.loadProfile(getIntent().getStringExtra(TUTOR_ID_KEY));

        activeModel.getProfile().observe(this, new Observer<TutorProfile>() {
            @Override
            public void onChanged(@Nullable TutorProfile tutorProfile) {
                binding.setTutorProfile(activeModel.getProfileValue());
            }
        });
        binding.setTutorProfile(activeModel.getProfileValue());
    }
}
