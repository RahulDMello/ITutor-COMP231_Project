package com.example.itutor.main.profile.model;

import com.example.itutor.main.tools.DateUtilsHelper;
import com.google.firebase.auth.FirebaseUser;

import java.util.Date;

public class TutorProfile {

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

    public String getSubject1() {
        return subject1;
    }

    public void setSubject1(String subject1) {
        this.subject1 = subject1;
    }

    public String getSubject2() {
        return subject2;
    }

    public void setSubject2(String subject1) {
        this.subject2 = subject2;
    }

    public String getSubject3() {
        return subject3;
    }

    public void setSubject3(String subject1) {
        this.subject3 = subject3;
    }

}
