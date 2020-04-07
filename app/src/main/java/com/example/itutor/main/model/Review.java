package com.example.itutor.main.model;

public class Review {
    private String fullname;
    private String comment;

    public Review() {}

    public Review(String fullname, String comment) {
        this.fullname = fullname;
        this.comment = comment;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
