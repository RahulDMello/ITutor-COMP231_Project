package com.example.itutor.main.model;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.android.databinding.library.baseAdapters.BR;
import com.example.itutor.main.SessionInfo;
import com.example.itutor.main.tools.DateUtilsHelper;
import com.google.firebase.auth.FirebaseUser;

import java.util.Date;

public class StudentProfile extends BaseObservable {

    private String id;
    private String firstName;
    private String lastName;
    private Date dateOfBirth;

    public StudentProfile() {
        id = "";
        firstName = "";
        lastName = "";
        dateOfBirth = null;
    }

    public StudentProfile(FirebaseUser user) {
        setId(user.getUid());
        firstName = "";
        lastName = "";
        dateOfBirth = null;
    }

    public void update(StudentProfile studentProfile) {
        setId(studentProfile.id);
        setFirstName(studentProfile.firstName);
        setLastName(studentProfile.lastName);
        setFormattedDateOfBirth(DateUtilsHelper.getShortFormattedDate(this.dateOfBirth = studentProfile.dateOfBirth));
    }

    @Bindable
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
        notifyPropertyChanged(BR.id);
    }

    @Bindable
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
        SessionInfo.STUDENT_NAME = firstName + " " + lastName;
        notifyPropertyChanged(BR.firstName);
    }

    @Bindable
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
        SessionInfo.STUDENT_NAME = firstName + " " + lastName;
        notifyPropertyChanged(BR.lastName);
    }

    @Bindable
    public String getFormattedDateOfBirth() {
        return DateUtilsHelper.getShortFormattedDate(dateOfBirth);
    }

    public void setFormattedDateOfBirth(String formattedDateOfBirth) {
        if (formattedDateOfBirth.length() == 10) {
            this.dateOfBirth = DateUtilsHelper.getShortDate(formattedDateOfBirth);
            if (dateOfBirth != null) {
                notifyPropertyChanged(BR.formattedDateOfBirth);
            }
        }
    }
}
