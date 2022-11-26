package com.example.finalproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

/*
 * PICK A MOVE
 *
 * */

public class MovesActivity extends AppCompatActivity {
    private String heroChoice, move;
    private TextView move1, move2, move3;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.moves);

        Bundle extras = getIntent().getExtras();
        heroChoice = extras.getString("HeroChoice");

        //This is the views being found for the moves.xml
        //whatever hero the user chooses effects the moves that will be shown (every hero has diff moves)
        move1 = (TextView) findViewById((R.id.move1button));
        move2 = (TextView) findViewById((R.id.move2button));
        move3 = (TextView) findViewById((R.id.move3button));
    }

    //ON CLICK METHOD FOR MOVE 1
    public void move1(View view){

        //making the strings for the moves that'll be displayed for the character chosen
        if(heroChoice == "genji") {
            String genjimove1 = "SHURIKEN";
            move1.setText(genjimove1);
        }

        launchBattleActivity();
    }

    //ON CLICK METHOD FOR MOVE 2
    public void move2(View view){

        String genjimove2 = "DEFLECT ONCOMING PROJECTILES BACK";
        move2.setText(genjimove2);

        launchBattleActivity();
    }

    //ON CLICK METHOD FOR MOVE 3
    public void move3(View view){

        String genjimove3 = "DRAGONBLADE";
        move3.setText(genjimove3);

        launchBattleActivity();
    }

    //launch battle activity which displays what hero the user chose/the move and what they're against
    //as well as who wins
    public void launchBattleActivity(){
        //sends intent from this class to the moves class
        Intent intent = new Intent(this, BattleActivity.class);
        //calls back the heros choice and the moves will be shown via the choice the user makes
        intent.putExtra("moveChoice", move);
        //starting the moves activity
        startActivity(intent);
    }
}
