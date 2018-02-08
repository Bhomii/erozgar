package com.pawras.android.basketballclub.models;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class Coach {
    public String uid;
    public String firstName;
    public String surnameName;
    public String dateOfBirth;
    public String email;
    public String address;
    public String contactNumber;

    public Coach() {
        // Default constructor required for calls to DataSnapshot.getValue(Coach.class)
    }


    public Coach(String firstName, String surnameName, String dateOfBirth, String email, String address, String contactNumber) {
        this.firstName = firstName;
        this.surnameName = surnameName;
        this.dateOfBirth = dateOfBirth;
        this.address = address;
        this.email = email;
        this.contactNumber = contactNumber;

    }

}
