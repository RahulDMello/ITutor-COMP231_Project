package com.example.itutor.itutor.profile.viewmodel;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.support.annotation.NonNull;
import android.widget.Toast;

import com.example.itutor.itutor.DateUtilsHelper;
import com.example.itutor.itutor.profile.tools.StudentProfile;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.functions.FirebaseFunctions;

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

        studentProfileRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (!dataSnapshot.exists()) {
                    studentProfileRef.setValue(this)
                            .continueWith(new Continuation<Void, Object>() {
                                @Override
                                public Object then(@NonNull Task<Void> task) throws Exception {
                                    if(task.isSuccessful()) {
                                        attachValueListenerForStudentProfile(studentProfileRef);
                                    } else {
                                        // TODO: present error
                                    }
                                    return null;
                                }
                            });
                } else {
                    attachValueListenerForStudentProfile(studentProfileRef);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // TODO: present error
            }
        });
    }

    private void attachValueListenerForStudentProfile(DatabaseReference studentProfileRef) {
        studentProfileRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                updateProfile(dataSnapshot.getValue(StudentProfile.class));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // TODO: present error
            }
        });
    }

    private void updateProfile(StudentProfile studentProfile) {
        profile.setValue(studentProfile);
    }

    public void setProfile(MutableLiveData<StudentProfile> profile) {
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

    public String getDateOfBirth() {
        return DateUtilsHelper.getShortFormattedDate(getProfileValue().getDateOfBirth());
    }

    public void setDateOfBirth(String dateOfBirth) {
        getProfileValue().setDateOfBirth(DateUtilsHelper.getShortDate(dateOfBirth));
    }
}
