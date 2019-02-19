package com.example.itutor.itutor.profile;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.itutor.itutor.R;
import com.example.itutor.itutor.databinding.ActivityStudentProfileBinding;
import com.example.itutor.itutor.profile.tools.StudentProfile;
import com.example.itutor.itutor.profile.viewmodel.StudentProfileViewModel;

public class StudentProfileActivity extends AppCompatActivity {

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
                binding.firstName.setText(studentProfile.getFirstName());
                binding.lastName.setText(studentProfile.getLastName());
                binding.dateOfBirth.setText(studentProfile.getFormattedDateOfBirth());
            }
        });
       binding.setActiveModel(activeModel);
    }
}
