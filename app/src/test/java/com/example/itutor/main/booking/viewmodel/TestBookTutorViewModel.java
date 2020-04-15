package com.example.itutor.main.booking.viewmodel;

import androidx.lifecycle.ViewModel;

import com.example.itutor.main.model.TutorProfile;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TestBookTutorViewModel extends ViewModel {

    ArrayList<TutorProfile> tutorProfiles = new ArrayList<>();

    public List<TutorProfile> getTutorProfiles(final String subject) {
        return tutorProfiles.stream().filter((tutorProfile) ->
                tutorProfile != null  && (tutorProfile.getSubject1().contains(subject)
                        || tutorProfile.getSubject2().contains(subject)
                        || tutorProfile.getSubject3().contains(subject))).collect(Collectors.toList());
    }

    public TutorProfile getTutor(final String id) {
        return tutorProfiles.stream().filter((tutorProfile) -> id.equals(tutorProfile.getId())).findFirst().orElse(new TutorProfile());
    }

}
