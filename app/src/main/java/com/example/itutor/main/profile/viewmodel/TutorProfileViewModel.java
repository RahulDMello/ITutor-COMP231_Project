package com.example.itutor.main.profile.viewmodel;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModel;
import android.databinding.Observable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.example.itutor.main.MainActivity;
import com.example.itutor.main.profile.model.StudentProfile;
import com.example.itutor.main.profile.model.TutorProfile;
import com.example.itutor.main.tools.DateUtilsHelper;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class TutorProfileViewModel extends ViewModel {

    private FirebaseAuth mAuth;
    private DatabaseReference mRootRef = FirebaseDatabase.getInstance().getReference();

    private MutableLiveData<TutorProfile> profile;

    public MutableLiveData<TutorProfile> getProfile() {
        if(profile == null || profile.getValue() == null) {
            loadProfile();
        }
        return profile;
    }

    public TutorProfile getProfileValue() {
        return getProfile().getValue();
    }

    public void loadProfile() {

        profile = new MutableLiveData<>();
        profile.setValue(new TutorProfile());

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

        final DatabaseReference tutorProfileRef = mRootRef.child("users").child(mAuth.getUid()).child("tutorProfile");

        profile.getValue().addOnPropertyChangedCallback(new Observable.OnPropertyChangedCallback() {
            @Override
            public void onPropertyChanged(Observable sender, int propertyId) {
                if (sender instanceof TutorProfile) {
                    tutorProfileRef.setValue(sender);
                }
            }
        });

        // attachValueListenerForStudentProfile(studentProfileRef);

        tutorProfileRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    TutorProfile profile = dataSnapshot.getValue(TutorProfile.class);
                    TutorProfileViewModel.this.profile.getValue().update(profile);
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

    public void setProfile(MutableLiveData<TutorProfile> profile) {
        this.profile = profile;
    }
}