package com.example.itutor.main.profile.view;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.itutor.main.MainActivity;
import com.example.itutor.main.MasterActivity;
import com.example.itutor.main.R;
import com.example.itutor.main.databinding.ActivityStudentProfileBinding;
import com.example.itutor.main.profile.model.StudentProfile;
import com.example.itutor.main.profile.viewmodel.StudentProfileViewModel;
import com.google.firebase.auth.FirebaseAuth;

public class StudentProfileActivity extends MasterActivity {

    private StudentProfileViewModel activeModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final ActivityStudentProfileBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_student_profile);
        activeModel = ViewModelProviders.of(this).get(StudentProfileViewModel.class);
        activeModel.loadProfile();
        activeModel.getProfile().observe(this, new Observer<StudentProfile>() {
            @Override
            public void onChanged(@Nullable StudentProfile studentProfile) {
                binding.setStudentProfile(activeModel.getProfileValue());
            }
        });
        binding.setStudentProfile(activeModel.getProfileValue());
    }
}
