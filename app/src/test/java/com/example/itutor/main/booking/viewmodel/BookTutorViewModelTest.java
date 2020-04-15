package com.example.itutor.main.booking.viewmodel;

import com.example.itutor.main.Repository;
import com.example.itutor.main.model.TutorProfile;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class BookTutorViewModelTest {

    @Test
    public void getTutorProfiles() {
        TestBookTutorViewModel viewModel = new TestBookTutorViewModel();
        viewModel.tutorProfiles = Repository.tutorProfile;
        List<TutorProfile> profiles = viewModel.getTutorProfiles("math");
        Assert.assertArrayEquals(Repository.mathTutorProfiles.toArray(), profiles.toArray());
    }

    @Test
    public void getTutor() {
        TestBookTutorViewModel viewModel = new TestBookTutorViewModel();
        viewModel.tutorProfiles = Repository.tutorProfile;
        TutorProfile profile = viewModel.getTutor("3");
        Assert.assertEquals(Repository.thirdTutorProfile, profile);
    }
}