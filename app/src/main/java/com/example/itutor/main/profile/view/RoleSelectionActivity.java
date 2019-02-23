package com.example.itutor.main.profile.view;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.itutor.main.R;
import com.example.itutor.main.databinding.ActivityRoleSelectionBinding;
import com.example.itutor.main.profile.TutorProfileActivity;

public class RoleSelectionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityRoleSelectionBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_role_selection);
        binding.studentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RoleSelectionActivity.this, StudentProfileActivity.class);
                startActivity(intent);
            }
        });

        binding.tutorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RoleSelectionActivity.this, TutorProfileActivity.class);
                startActivity(intent);
            }
        });
    }
}
