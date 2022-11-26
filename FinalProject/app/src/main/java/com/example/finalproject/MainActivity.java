package com.example.finalproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

/*
 * WE PICK OUR CHARACTER HERE
 *
 * SETTINGS TO CHANGE OUR HERO WE CHOSE
 * */

public class MainActivity extends AppCompatActivity {
    //this is the string that will be called in the intent for the second activity
    private String heroChosen;
    private TextView move1;
    private TextView move2;
    private TextView move3;
    //this will be assigned to the name from the textviews I'll be adding
    //these textviews will be under the heros image. it'll be used to identify them
    //feel free to add them yourself if you want. I already started to implement it for Genji
    private TextView heroName;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    //ON CLICK METHOD FOR GENJI WHICH CALLS ON THE SECOND ACTIVITY(MOVESACTIVITY)
    public void genjiMoves(View view){
        //if statement for the hero chosen
        heroChosen = "genji";
        launchMovesActivity();
    }

    //ON CLICK METHOD FOR MEI WHICH CALLS ON THE SECOND ACTIVITY
    public void meiMoves(View view){
        //if statement for the hero chosen
        if(heroChosen == "mei") {
            //making the strings for the moves that'll be displayed for the character chosen
            String meimove1 = "ENDOTHERMIC BLASTER";
            String meimove2 = "ICE WALL OFF MAP";
            String meimove3 = "CAST A BLIZZARD TO FREEZE ";
            //giving value to the buttons in the moves.xml
            //the values will be specified to the moves the hero chosen can do
            move1.setText(meimove1);
            move2.setText(meimove2);
            move3.setText(meimove3);
            launchMovesActivity();
        }
    }

    //ON CLICK METHOD FOR MOIRA WHICH CALLS ON THE SECOND ACTIVITY
    public void moiraMoves(View view){
        //if statement for the hero chosen
        if(heroChosen == "moira") {
            //making the strings for the moves that'll be displayed for the character chosen
            String moiramove1 = "SOUL SUCK";
            String moiramove2 = "CAST BIOTIC ORB";
            String moiramove3 = "COALESCENCE";
            //giving value to the buttons in the moves.xml
            //the values will be specified to the moves the hero chosen can do
            move1.setText(moiramove1);
            move2.setText(moiramove2);
            move3.setText(moiramove3);
            launchMovesActivity();
        }
    }

    //ON CLICK METHOD FOR CASSIDY WHICH CALLS ON THE SECOND ACTIVITY
    public void cassidyMoves(View view){
        //if statement for the hero chosen
        if(heroChosen == "cassidy") {
            //making the strings for the moves that'll be displayed for the character chosen
            String cassidymove1 = "PEACEKEEPER";
            String cassidymove2 = "MAGNETIC GRENADE";
            String cassidymove3 = "DEADEYE - IT'S HIGH NOON";
            //giving value to the buttons in the moves.xml
            //the values will be specified to the moves the hero chosen can do
            move1.setText(cassidymove1);
            move2.setText(cassidymove2);
            move3.setText(cassidymove3);
            launchMovesActivity();
        }
    }

    //ON CLICK METHOD FOR ROADHOG WHICH CALLS ON THE SECOND ACTIVITY
    public void roadhogMoves(View view){
        //if statement for the hero chosen
        if(heroChosen == "roadhog") {
            //making the strings for the moves that'll be displayed for the character chosen
            String roadhogmove1 = "SCRAP GUN";
            String roadhogmove2 = "CHAIN HOOK";
            String roadhogmove3 = "WHOLE HOG";
            //giving value to the buttons in the moves.xml
            //the values will be specified to the moves the hero chosen can do
            move1.setText(roadhogmove1);
            move2.setText(roadhogmove2);
            move3.setText(roadhogmove3);
            launchMovesActivity();
        }
    }

    //ON CLICK METHOD FOR REAPER WHICH CALLS ON THE SECOND ACTIVITY
    public void reaperMoves(View view){
        //if statement for the hero chosen
        if(heroChosen == "reaper") {
            //making the strings for the moves that'll be displayed for the character chosen
            String reapermove1 = "HELLFIRE SHOTGUNS";
            String reapermove2 = "THE REAPING";
            String reapermove3 = "DEATH BLOSSOM";
            //giving value to the buttons in the moves.xml
            //the values will be specified to the moves the hero chosen can do
            move1.setText(reapermove1);
            move2.setText(reapermove2);
            move3.setText(reapermove3);
            launchMovesActivity();
        }
    }

    //ON CLICK METHOD FOR MERCY WHICH CALLS ON THE SECOND ACTIVITY
    public void mercyMoves(View view){
        //if statement for the hero chosen
        if(heroChosen == "mercy") {
            //making the strings for the moves that'll be displayed for the character chosen
            String mercymove1 = "CADUCEUS BLASTER";
            String mercymove2 = "RESURRECT";
            String mercymove3 = "VALKYRIE";
            //giving value to the buttons in the moves.xml
            //the values will be specified to the moves the hero chosen can do
            move1.setText(mercymove1);
            move2.setText(mercymove2);
            move3.setText(mercymove3);
            launchMovesActivity();
        }
    }

    //ON CLICK METHOD FOR WIDOW WHICH CALLS ON THE SECOND ACTIVITY
    public void widowMoves(View view){
        //if statement for the hero chosen
        if(heroChosen == "widow") {
            //making the strings for the moves that'll be displayed for the character chosen
            String widowmove1 = "WIDOW'S KISS";
            String widowmove2 = "VENOM MINE";
            String widowmove3 = "INFRA-SIGHT";
            //giving value to the buttons in the moves.xml
            //the values will be specified to the moves the hero chosen can do
            move1.setText(widowmove1);
            move2.setText(widowmove2);
            move3.setText(widowmove3);
            launchMovesActivity();
        }
    }


    //launch moves activity which calls upon the second activity
   public void launchMovesActivity(){
        //sends intent from this class to the moves class
        Intent intent = new Intent(this, BattleActivity.class);
        //calls back the heros choice and the moves will be shown via the choice the user makes
        intent.putExtra("HeroChoice", heroChosen);
        //starting the moves activity
        startActivity(intent);
    }
}