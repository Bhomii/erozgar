package com.pawras.android.basketballclub.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.pawras.android.basketballclub.R;
import com.pawras.android.basketballclub.models.Coach;

public class CoachViewHolder extends RecyclerView.ViewHolder {


    public TextView coachName;
    public TextView coachContact;
    public TextView coachEmail;

    public CoachViewHolder(View itemView) {
        super(itemView);

        coachName = (TextView) itemView.findViewById(R.id._name_coach);
        coachContact = (TextView) itemView.findViewById(R.id._contact_number_coach);
        coachEmail = (TextView) itemView.findViewById(R.id._email_address_coach);

    }

    public void bindToPost(Coach postTo) {
        coachName.setText(postTo.firstName + " " + postTo.surnameName);
        coachContact.setText(postTo.contactNumber);
        coachEmail.setText(postTo.email);
    }

}
