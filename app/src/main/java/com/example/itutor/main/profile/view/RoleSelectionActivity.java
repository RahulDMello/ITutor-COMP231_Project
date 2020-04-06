package com.example.itutor.main.profile.view;

import android.content.Intent;
import androidx.databinding.DataBindingUtil;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import com.example.itutor.main.MasterActivity;
import com.example.itutor.main.R;
import com.example.itutor.main.databinding.ActivityRoleSelectionBinding;

public class RoleSelectionActivity extends MasterActivity {

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
