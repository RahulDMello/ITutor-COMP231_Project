package com.example.itutor.main.model;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.example.itutor.main.BR;
import com.example.itutor.main.tools.DateUtilsHelper;

import java.util.Date;

public class BookedMeeting extends BaseObservable {

    private String studentName;

    private Date date;

    private Date time;

    private String place;
    private String message;

    public BookedMeeting() {
        studentName = "";
        date = null;
        place = "";
        message = "";
    }

    public BookedMeeting(String studentId) {
        setStudentName(studentId);
        date = null;
        place = "";
        message = "";
    }

    @Bindable
    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
        notifyPropertyChanged(BR.studentName);
    }

    @Bindable
    public String getFormattedDate() {
        return DateUtilsHelper.getShortFormattedDate(date);
    }

    public void setFormattedDate(String formattedDate) {
        if (formattedDate.length() == 10) {
            this.date = DateUtilsHelper.getShortDate(formattedDate);
            if (this.date != null) {
                notifyPropertyChanged(BR.formattedDate);
            }
        }
    }

    @Bindable
    public String getFormattedTime() {
        return DateUtilsHelper.getSimpleFormattedTime(time);
    }

    public void setFormattedTime(String time) {
        if (time.length() == 5) {
            this.time = DateUtilsHelper.getSimpleTime(time);
            if (this.time != null) {
                notifyPropertyChanged(BR.formattedTime);
            }
        }
    }

    @Bindable
    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
        notifyPropertyChanged(BR.place);
    }

    @Bindable
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
        notifyPropertyChanged(BR.message);
    }
}
