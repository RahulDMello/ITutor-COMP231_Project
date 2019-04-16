package com.example.itutor.main.profile.presenter;

import android.view.View;

public class TutorProfilePresenter {
    private boolean isPersonalTutorProfile;

    public TutorProfilePresenter() {
        isPersonalTutorProfile = false;
    }

    public boolean isPersonalTutorProfile() {
        return isPersonalTutorProfile;
    }

    public void setPersonalTutorProfile(boolean personalTutorProfile) {
        isPersonalTutorProfile = personalTutorProfile;
    }

    public int getMeetingButtonVisibility() {
        return isPersonalTutorProfile ? View.GONE : View.VISIBLE;
    }

    public int getShowMeetingsButtonVisibility() {
        return isPersonalTutorProfile ? View.VISIBLE : View.GONE;
    }
}
