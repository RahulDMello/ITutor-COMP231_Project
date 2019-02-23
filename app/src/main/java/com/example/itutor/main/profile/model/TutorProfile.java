package com.example.itutor.main.profile.model;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.android.databinding.library.baseAdapters.BR;
import com.example.itutor.main.tools.DateUtilsHelper;
import com.google.firebase.auth.FirebaseUser;

import java.util.Date;

public class TutorProfile extends BaseObservable {

    private String id;
    private String firstName;
    private String lastName;
    private String subject1;
    private String subject2;
    private String subject3;

    public TutorProfile() {
        id = "";
        firstName = "";
        lastName = "";
        subject1 = "";
        subject2 = "";
        subject3 = "";
    }

    public TutorProfile(FirebaseUser user) {
        id = user.getUid();
        firstName = "";
        lastName = "";
        subject1 = "";
        subject2 = "";
        subject3 = "";

    }

    public void update(TutorProfile tutorProfile) {
        setId(tutorProfile.id);
        setFirstName(tutorProfile.firstName);
        setLastName(tutorProfile.lastName);
        setSubject1(tutorProfile.subject1);
        setSubject2(tutorProfile.subject2);
        setSubject3(tutorProfile.subject3);
    }

    @Bindable
    public String getId() {
        return id;
    }

    public void setId(String id){
        this.id = id;
    notifyPropertyChanged(BR.id);
    }

    @Bindable
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
      this.firstName = firstName;
        notifyPropertyChanged(BR.firstName);
    }

    @Bindable
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
        notifyPropertyChanged(BR.lastName);
    }

    @Bindable
    public String getSubject1() {
        return subject1;
    }

    public void setSubject1(String subject1) {
        this.subject1 = subject1;
        notifyPropertyChanged(BR.subject1);
    }

    @Bindable
    public String getSubject2() {
        return subject2;
    }

    public void setSubject2(String subject2) {
        this.subject2 = subject2;
        notifyPropertyChanged(BR.subject2);
    }

    @Bindable
    public String getSubject3() {
        return subject3;
    }

    public void setSubject3(String subject3) {
        this.subject3 = subject3;
        notifyPropertyChanged(BR.subject3);
    }

}
