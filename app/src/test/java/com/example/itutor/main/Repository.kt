package com.example.itutor.main

import com.example.itutor.main.model.TutorProfile
import java.util.*

object Repository {
    @JvmField
    var tutorProfile = ArrayList<TutorProfile>().apply {
        add(TutorProfile().apply {
            id = "1"
            firstName = "Rahul"
            lastName = "DMello"
            subject1 = "math"
            subject2 = "league of legends"
            subject3 = "team fight tactics"
            bookedMeetings = HashMap()
            reviews = HashMap()
        })
        add(TutorProfile().apply {
            id = "2"
            firstName = "Deva"
            lastName = "Deva"
            subject1 = "cooking"
            subject2 = "computer science"
            subject3 = "science"
            bookedMeetings = HashMap()
            reviews = HashMap()
        })
        add(TutorProfile().apply {
            id = "3"
            firstName = "David"
            lastName = "Kim"
            subject1 = "media"
            subject2 = "cooking"
            subject3 = "environment"
            bookedMeetings = HashMap()
            reviews = HashMap()
        })
        add(TutorProfile().apply {
            id = "4"
            firstName = "Harsh"
            lastName = "Patel"
            subject1 = "chemistry"
            subject2 = "biology"
            subject3 = "math"
            bookedMeetings = HashMap()
            reviews = HashMap()
        })
        add(TutorProfile().apply {
            id = "5"
            firstName = "Jay"
            lastName = "Patel"
            subject1 = "robotics"
            subject2 = "automation"
            subject3 = "python"
            bookedMeetings = HashMap()
            reviews = HashMap()
        })
    }

    @JvmField
    var mathTutorProfiles = ArrayList<TutorProfile>().apply {
        add(TutorProfile().apply {
            id = "1"
            firstName = "Rahul"
            lastName = "DMello"
            subject1 = "Math"
            subject2 = "league of legends"
            subject3 = "team fight tactics"
            bookedMeetings = HashMap()
            reviews = HashMap()
        })
        add(TutorProfile().apply {
            id = "4"
            firstName = "Harsh"
            lastName = "Patel"
            subject1 = "chemistry"
            subject2 = "biology"
            subject3 = "math"
            bookedMeetings = HashMap()
            reviews = HashMap()
        })
    }

    @JvmField
    var thirdTutorProfile = TutorProfile().apply {
            id = "3"
            firstName = "David"
            lastName = "Kim"
            subject1 = "media"
            subject2 = "cooking"
            subject3 = "environment"
            bookedMeetings = HashMap()
            reviews = HashMap()
    }
}