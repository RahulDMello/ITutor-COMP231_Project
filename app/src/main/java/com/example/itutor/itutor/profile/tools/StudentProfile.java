package com.example.itutor.itutor.profile.tools;

import com.example.itutor.itutor.DateUtilsHelper;
import com.google.firebase.auth.FirebaseUser;

import java.util.Date;

public class StudentProfile {

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
        id = user.getUid();
        firstName = "";
        lastName = "";
        dateOfBirth = null;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public String getFormattedDateOfBirth() {
        return DateUtilsHelper.getShortFormattedDate(getDateOfBirth());
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
}
