package com.pawras.android.basketballclub.models;


import com.google.firebase.database.IgnoreExtraProperties;

import java.util.ArrayList;

@IgnoreExtraProperties
public class Team {


    public String teamName;
    public Coach coach;
    public ArrayList<Player> players;

    public Team() {
        // Default constructor required for calls to DataSnapshot.getValue(Coach.class)
    }

    public Team(String teamName, Coach coach, ArrayList<Player> players) {
        this.teamName = teamName;
        this.coach = coach;
        this.players = players;
    }

}
