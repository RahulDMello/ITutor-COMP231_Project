package com.example.itutor.main.profile.view;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.itutor.main.R;
import com.example.itutor.main.databinding.ActivityTutorProfileBinding;
import com.example.itutor.main.profile.model.StudentProfile;
import com.example.itutor.main.profile.model.TutorProfile;
import com.example.itutor.main.profile.viewmodel.TutorProfileViewModel;

public class TutorProfileActivity extends AppCompatActivity {

    private TutorProfileViewModel activeModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    //    setContentView(R.layout.activity_tutor_profile);
        final ActivityTutorProfileBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_tutor_profile);
        activeModel = ViewModelProviders.of(this).get(TutorProfileViewModel.class);
        activeModel.loadProfile();
        activeModel.getProfile().observe(this, new Observer<TutorProfile>() {
            @Override
            public void onChanged(@Nullable TutorProfile tutorProfile) {
                binding.firstName.setText(tutorProfile.getFirstName());
                binding.lastName.setText(tutorProfile.getLastName());
                binding.subject1.setText(tutorProfile.getSubject1());
                binding.subject2.setText(tutorProfile.getSubject2());
                binding.subject3.setText(tutorProfile.getSubject3());
                //binding.dateOfBirth.setText(studentProfile.getFormattedDateOfBirth());

            }
        });
        binding.setActiveModel(activeModel);
    }
}
