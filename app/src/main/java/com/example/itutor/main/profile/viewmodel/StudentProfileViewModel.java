package com.example.itutor.main.profile.viewmodel;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.databinding.Observable;
import android.support.annotation.NonNull;

import com.example.itutor.main.profile.model.StudentProfile;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.Task;
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
        if(profile == null || profile.getValue() == null) {
            loadProfile();
        }
        return profile;
    }

    public StudentProfile getProfileValue() {
        return getProfile().getValue();
    }

    public void loadProfile() {

        profile = new MutableLiveData<>();
        profile.setValue(new StudentProfile());

        mAuth = FirebaseAuth.getInstance();

/*      TODO: FOR LOGOUT

        mAuth.addAuthStateListener(new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if (firebaseAuth.getCurrentUser() == null) {
                    MyProfileActivity.super.onBackPressed();
                }
            }
        });
*/

        final DatabaseReference studentProfileRef = mRootRef.child("users").child(mAuth.getUid()).child("studentProfile");

        profile.getValue().addOnPropertyChangedCallback(new Observable.OnPropertyChangedCallback() {
            @Override
            public void onPropertyChanged(Observable sender, int propertyId) {
                if (sender instanceof StudentProfile) {
                    studentProfileRef.setValue(sender);
                }
            }
        });

        // attachValueListenerForStudentProfile(studentProfileRef);

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
                int a = 5;
            }
        });
    }

    private void updateProfile(StudentProfile studentProfile) {
        profile.getValue().update(studentProfile);
    }

    public void setProfile(MutableLiveData<StudentProfile> profile) {
        this.profile = profile;
    }
}
