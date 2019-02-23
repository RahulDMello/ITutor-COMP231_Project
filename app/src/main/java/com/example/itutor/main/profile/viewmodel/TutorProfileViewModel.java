package com.example.itutor.main.profile.viewmodel;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.support.annotation.NonNull;

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

        // attachValueListenerForStudentProfile(studentProfileRef);

        tutorProfileRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (!dataSnapshot.exists()) {
                    tutorProfileRef.setValue(new TutorProfile(mAuth.getCurrentUser()))
                            .continueWith(new Continuation<Void, Object>() {
                                @Override
                                public Object then(@NonNull Task<Void> task) throws Exception {
                                    if(task.isSuccessful()) {
                                        attachValueListenerForTutorProfile(tutorProfileRef);
                                    } else {
                                        // TODO: present error
                                        int a = 5;
                                    }
                                    return null;
                                }
                            });
                } else {
                    attachValueListenerForTutorProfile(tutorProfileRef);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // TODO: present error
                int a = 5;
            }
        });
    }

    private void attachValueListenerForTutorProfile(DatabaseReference tutorProfileRef) {
        tutorProfileRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                updateProfile(dataSnapshot.getValue(TutorProfile.class));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // TODO: present error
            }
        });
    }

    private void updateProfile(TutorProfile tutorProfile) {
        profile.setValue(tutorProfile);
        // setFirstName(studentProfile.getFirstName());
    }

    public void setProfile(MutableLiveData<TutorProfile> profile) {
        this.profile = profile;
    }

    public String getFirstName() {
        return getProfileValue().getFirstName();
    }

    public void setFirstName(String firstName) {
        getProfileValue().setFirstName(firstName);
    }

    public String getLastName() {
        return getProfileValue().getLastName();
    }

    public void setLastName(String lastName) {
        getProfileValue().setLastName(lastName);
    }

    public String getSubject1() {
        return getProfileValue().getSubject1();
    }

    public void setSubject1(String subject1) {
        getProfileValue().setSubject1(subject1);
    }

    public String getSubject2() {
        return getProfileValue().getSubject2();
    }

    public void setSubject2(String subject2) {
        getProfileValue().setSubject2(subject2);
    }

    public String getSubject3() {
        return getProfileValue().getSubject3();
    }

    public void setSubject3(String subject3) { getProfileValue().setSubject3(subject3);
    }
}
