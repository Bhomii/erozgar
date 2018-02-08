package com.pawras.android.basketballclub.add_new;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.pawras.android.basketballclub.R;

public class CreateNew extends AppCompatActivity {

    Button createCoach,createPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_new);
        createCoach=(Button) findViewById(R.id.create_coach);
        createPlayer=(Button) findViewById(R.id.create_player);

        //set click listners
        createCoach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CreateNew.this,CreateCoach.class));
            }
        });

        createPlayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CreateNew.this,CreatePlayer.class));
            }
        });
    }
}
