package com.pawras.android.basketballclub.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.pawras.android.basketballclub.R;
import com.pawras.android.basketballclub.models.Player;

public class PlayerViewHolder extends RecyclerView.ViewHolder {


    public TextView playerName;
    public TextView playerContact;
    public TextView playerEmail;

    public PlayerViewHolder(View itemView) {
        super(itemView);

        playerName = (TextView) itemView.findViewById(R.id._name_player);
        playerContact = (TextView) itemView.findViewById(R.id._contact_number_player);
        playerEmail = (TextView) itemView.findViewById(R.id._email_address_player);

    }

    public void bindToPost(Player postTo) {
        playerName.setText(postTo.firstName + " " + postTo.surnameName);
        playerContact.setText(postTo.contactNumber);
        playerEmail.setText(postTo.email);

    }

}
