package com.pawras.android.basketballclub.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.pawras.android.basketballclub.R;
import com.pawras.android.basketballclub.models.Team;

public class TeamViewHolder extends RecyclerView.ViewHolder {


    public TextView team;
    public TextView coach;

    public TeamViewHolder(View itemView) {
        super(itemView);

        team = (TextView) itemView.findViewById(R.id._name_team);
        coach = (TextView) itemView.findViewById(R.id._contact_number_coach_team);
    }

    public void bindToPost(Team postTo) {
        team.setText(postTo.teamName);
        coach.setText(postTo.coach.firstName + " " + postTo.coach.surnameName);

    }

}