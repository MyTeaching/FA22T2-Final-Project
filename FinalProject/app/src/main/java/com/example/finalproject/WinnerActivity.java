package com.example.finalproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class WinnerActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    private String heroChosen;
    private int heroValue, heroHP, heroMP;

    private String enemyChosen;
    private int enemyValue, enemyHP, enemyMP;

    private ImageView imageView_winner;
    private TextView textView_who_won;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.winner_activity);

        Bundle extras = getIntent().getExtras();
        heroChosen = extras.getString("HeroChosen");
        heroValue = extras.getInt("HeroValue");
        heroHP = extras.getInt("HeroHP");
        heroMP = extras.getInt("HeroMP");

        enemyChosen = extras.getString("EnemyChosen");
        enemyValue = extras.getInt("EnemyValue");
        enemyHP = extras.getInt("EnemyHP");
        enemyMP = extras.getInt("EnemyMP");

        textView_who_won = (TextView) findViewById(R.id.textView_who_won);
        imageView_winner = (ImageView) findViewById(R.id.imageView_winner);

        int WonBy = heroHP - enemyHP;
        String WonBy_Text = "Your hero " + heroChosen + " won the enemy hero by " + WonBy + " hp!";
        textView_who_won.setText(WonBy_Text);
        int hero_id = getResources().getIdentifier(heroChosen, "drawable", getPackageName());
        imageView_winner.setImageResource(hero_id);

    }

    public void onClickReturn(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
