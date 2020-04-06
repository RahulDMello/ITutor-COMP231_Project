package com.example.itutor.main.profile.view;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import android.content.Intent;
import androidx.databinding.DataBindingUtil;
import android.os.Bundle;
import androidx.annotation.Nullable;

import com.example.itutor.main.MasterActivity;
import com.example.itutor.main.R;
import com.example.itutor.main.booking.view.SearchTutorActivity;
import com.example.itutor.main.databinding.ActivityStudentProfileBinding;
import com.example.itutor.main.model.StudentProfile;
import com.example.itutor.main.profile.viewmodel.StudentProfileViewModel;

public class StudentProfileActivity extends MasterActivity {

    private StudentProfileViewModel activeModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final ActivityStudentProfileBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_student_profile);

        binding.searchTutorButton.setOnClickListener(view -> {
            startActivity(new Intent(this, SearchTutorActivity.class));
        });

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
