package com.example.itutor.main.profile.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.databinding.Observable;
import androidx.annotation.NonNull;

import com.example.itutor.main.model.StudentProfile;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class StudentProfileViewModel extends ViewModel {

    private FirebaseAuth mAuth;
    private DatabaseReference mRootRef = FirebaseDatabase.getInstance().getReference();

    private MutableLiveData<StudentProfile> profile;

    public MutableLiveData<StudentProfile> getProfile() {
        if (profile == null || profile.getValue() == null) {
            loadProfile();
        }
        return profile;
    }

    public StudentProfile getProfileValue() {
        return getProfile().getValue();
    }

    public void loadProfile() {

        profile = new MutableLiveData<>();

        mAuth = FirebaseAuth.getInstance();

        profile.setValue(new StudentProfile(mAuth.getCurrentUser()));

        final DatabaseReference studentProfileRef = mRootRef.child("users").child(mAuth.getUid()).child("studentProfile");

        profile.getValue().addOnPropertyChangedCallback(new Observable.OnPropertyChangedCallback() {
            @Override
            public void onPropertyChanged(Observable sender, int propertyId) {
                if (sender instanceof StudentProfile) {
                    studentProfileRef.setValue(sender);
                }
            }
        });

        studentProfileRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    StudentProfile profile = dataSnapshot.getValue(StudentProfile.class);
                    StudentProfileViewModel.this.profile.getValue().update(profile);
                } else {

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // TODO: present error
            }
        });
    }

    public void setProfile(MutableLiveData<StudentProfile> profile) {
        this.profile = profile;
    }
}
