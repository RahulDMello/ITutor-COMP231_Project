package com.example.itutor.main.booking.viewmodel;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.support.annotation.NonNull;

import com.example.itutor.main.model.BookedMeeting;
import com.example.itutor.main.model.TutorProfile;
import com.example.itutor.main.model.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BookTutorViewModel extends ViewModel {

    private FirebaseAuth mAuth;
    private DatabaseReference mRootRef = FirebaseDatabase.getInstance().getReference();

    private ArrayList<TutorProfile> tutorProfiles = new ArrayList<>();

    private MutableLiveData<Boolean> areProfilesLoaded;

    public BookTutorViewModel() {
        areProfilesLoaded = new MutableLiveData<>();
        areProfilesLoaded.setValue(false);
    }

    public void loadProfiles() {
        mAuth = FirebaseAuth.getInstance();

        final DatabaseReference profilesRef = mRootRef.child("users");

        profilesRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    for (DataSnapshot data : dataSnapshot.getChildren()) {
                        TutorProfile profile = data.getValue(User.class).getTutorProfile();
                        BookTutorViewModel.this.tutorProfiles.add(profile);
                    }
                    areProfilesLoaded.setValue(true);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public List<TutorProfile> getTutorProfiles(final String subject) {
        return tutorProfiles.stream().filter((tutorProfile) ->
                tutorProfile != null && !tutorProfile.getId().equals(mAuth.getUid())
                        && (tutorProfile.getSubject1().contains(subject)
                        || tutorProfile.getSubject2().contains(subject)
                        || tutorProfile.getSubject3().contains(subject))).collect(Collectors.toList());
    }

    public TutorProfile getTutor(final String id) {
        return tutorProfiles.stream().filter((tutorProfile) -> id.equals(tutorProfile.getId())).findFirst().orElse(new TutorProfile());
    }

    public MutableLiveData<Boolean> getAreProfilesLoaded() {
        return areProfilesLoaded;
    }

    public void notifyChangeInFilter() {
        areProfilesLoaded.setValue(true);
    }
}
