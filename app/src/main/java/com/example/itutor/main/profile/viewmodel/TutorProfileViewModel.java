package com.example.itutor.main.profile.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import android.content.Context;
import androidx.databinding.Observable;
import androidx.annotation.NonNull;
import android.text.TextUtils;
import android.widget.Toast;

import com.example.itutor.main.model.BookedMeeting;
import com.example.itutor.main.model.TutorProfile;
import com.google.android.gms.tasks.OnCanceledListener;
import com.google.android.gms.tasks.OnCompleteListener;
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
        if (profile == null || profile.getValue() == null) {
            loadProfile("");
        }
        return profile;
    }

    public void bookAMeeting(Context context, BookedMeeting meeting) {
        if (mAuth == null)
            mAuth = FirebaseAuth.getInstance();

        final DatabaseReference meetingsRef = mRootRef.child("users").child(getProfileValue().getId()).child("tutorProfile").child("bookedMeetings");

        meetingsRef.push().setValue(meeting).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                Toast.makeText(context, "Meeting Booked", Toast.LENGTH_LONG).show();
            }
        }).addOnCanceledListener(new OnCanceledListener() {
            @Override
            public void onCanceled() {
                Toast.makeText(context, "Error! Please try again.", Toast.LENGTH_LONG).show();
            }
        });
    }

    public TutorProfile getProfileValue() {
        return getProfile().getValue();
    }

    public void loadProfile(String uid) {

        profile = new MutableLiveData<>();

        mAuth = FirebaseAuth.getInstance();

        profile.setValue(new TutorProfile(mAuth.getCurrentUser()));

        if (TextUtils.isEmpty(uid)) {
            uid = mAuth.getUid();
        }

        final DatabaseReference tutorProfileRef = mRootRef.child("users").child(uid).child("tutorProfile");

        profile.getValue().addOnPropertyChangedCallback(new Observable.OnPropertyChangedCallback() {
            @Override
            public void onPropertyChanged(Observable sender, int propertyId) {
                if (sender instanceof TutorProfile) {
                    tutorProfileRef.setValue(sender);
                }
            }
        });

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
            }
        });
    }

    public void setProfile(MutableLiveData<TutorProfile> profile) {
        this.profile = profile;
    }
}