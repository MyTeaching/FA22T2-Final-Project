package com.example.finalproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MovesActivity extends AppCompatActivity {
    private String move;


    //ON CLICK METHOD FOR MOVE 1
    public void move1(View view){
        launchBattleActivity();
    }

    //ON CLICK METHOD FOR MOVE 2
    public void move2(View view){
        launchBattleActivity();
    }

    //ON CLICK METHOD FOR MOVE 3
    public void move3(View view){
        launchBattleActivity();
    }

    //launch battle activity which displays what hero the user chose/the move and what they're against
    //as well as who wins
    public void launchBattleActivity(){
        //sends intent from this class to the moves class
        Intent intent = new Intent(this, MovesActivity.class);
        //calls back the heros choice and the moves will be shown via the choice the user makes
        intent.putExtra("moveChoice", move);
        //starting the moves activity
        startActivity(intent);
    }
}
