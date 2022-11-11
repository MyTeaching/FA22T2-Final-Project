package com.example.finalproject;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class BattleActivity extends AppCompatActivity {
    private TextView usersHeroInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //This is the views for the activity_battle.xml
        //this'll be the one displaying the heroName then the move the user chose for it
        usersHeroInfo = (TextView) findViewById(R.id.usersChoice);

    }
}
