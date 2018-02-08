package com.pawras.android.basketballclub.models;

import com.google.firebase.database.IgnoreExtraProperties;

/**
 * Created by Ravi Tamada on 07/10/16.
 * www.androidhive.info
 */

@IgnoreExtraProperties
public class CoachAndPlayer {

    private String address;
    private String date_of_birth;
    private String contact_number;
    private String email;
    private String sure_name;
    private String first_name;
    private String id;

    public String getAddress() {
        return address;
    }
    CoachAndPlayer(){
    }

    public void setAddress(String address) {
        this.address = address;
    }



    public String getId() {
        return id;
    }

    public CoachAndPlayer(String address, String date_of_birth, String contact_number, String email, String sure_name, String first_name, String id) {
        this.address = address;
        this.date_of_birth = date_of_birth;
        this.contact_number = contact_number;
        this.email = email;
        this.sure_name = sure_name;
        this.first_name = first_name;
        this.id = id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getSure_name() {
        return sure_name;
    }

    public void setSure_name(String sure_name) {
        this.sure_name = sure_name;
    }

    public String getDate_of_birth() {
        return date_of_birth;
    }

    public void setDate_of_birth(String date_of_birth) {
        this.date_of_birth = date_of_birth;
    }

    public String getContact_number() {
        return contact_number;
    }

    public void setContact_number(String contact_number) {
        this.contact_number = contact_number;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
