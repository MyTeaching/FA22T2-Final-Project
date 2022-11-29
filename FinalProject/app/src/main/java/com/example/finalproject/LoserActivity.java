package com.example.finalproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class LoserActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    private String heroChosen;
    private int heroValue, heroHP, heroMP;

    private String enemyChosen;
    private int enemyValue, enemyHP, enemyMP;

    private ImageView imageView_loser;
    private TextView textView_who_lost;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loser_activity);

        Bundle extras = getIntent().getExtras();
        heroChosen = extras.getString("HeroChosen");
        heroValue = extras.getInt("HeroValue");
        heroHP = extras.getInt("HeroHP");
        heroMP = extras.getInt("HeroMP");

        enemyChosen = extras.getString("EnemyChosen");
        enemyValue = extras.getInt("EnemyValue");
        enemyHP = extras.getInt("EnemyHP");
        enemyMP = extras.getInt("EnemyMP");

        textView_who_lost = (TextView) findViewById(R.id.textView_who_lost);
        imageView_loser = (ImageView) findViewById(R.id.imageView_loser);

        int LostBy = heroHP - enemyHP;
        String LostBy_Text = "Your hero " + heroChosen + " lost the enemy hero by " + LostBy + " hp!";
        textView_who_lost.setText(LostBy_Text);
        int hero_id = getResources().getIdentifier(heroChosen, "drawable", getPackageName());
        imageView_loser.setImageResource(hero_id);

    }

    public void onClickReturn(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
