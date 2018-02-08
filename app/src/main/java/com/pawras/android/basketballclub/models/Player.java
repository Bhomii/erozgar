package com.pawras.android.basketballclub.models;

public class Player {
    public String uid;
    public String firstName;
    public String surnameName;
    public String dateOfBirth;
    public String email;
    public String address;
    public String contactNumber;
    public String team;

    // to be handled later
    // private int mImageResourceId = NO_IMAGE_PROVIDED;
    // private static final int NO_IMAGE_PROVIDED = -1;

    // empty constructor cannot be called
    public Player(){};


    public Player(String firstName,String surnameName,String dateOfBirth, String email, String address, String contactNumber) {
        this.firstName = firstName;
        this.surnameName = surnameName;
        this.dateOfBirth = dateOfBirth;
        this.address = address;
        this.email  = email;
        this.contactNumber = contactNumber;
    }

}
