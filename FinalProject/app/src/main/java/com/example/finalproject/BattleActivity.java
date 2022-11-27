package com.example.finalproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/*
* This is where you'll see your enemy
* If you choose to run away -> goes back to MainActivity.java
*
* IF you choose to fight -> sends user to MovesActivity.java
*
*
* */

public class BattleActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    private TextView textView_My_Hero_Name, textView_My_Hero_HP;
    private TextView textView_Enemy_Hero_Name, textView_Enemy_Hero_HP;
    private ImageView imageView_My_Hero, imageView_Enemy_Hero;

    private String heroChosen;
    private int heroValue, heroHP, heroMP;

    private String enemyChosen;
    private int enemyValue, enemyHP, enemyMP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_battle);

        Bundle extras = getIntent().getExtras();
        heroChosen = extras.getString("HeroChosen");
        heroValue = extras.getInt("HeroValue");
        heroHP = extras.getInt("HeroHP");
        heroMP = extras.getInt("HeroMP");

        enemyChosen = extras.getString("EnemyChosen");
        enemyValue = extras.getInt("EnemyValue");
        enemyHP = extras.getInt("EnemyHP");
        enemyMP = extras.getInt("EnemyMP");

        textView_My_Hero_Name = (TextView) findViewById(R.id.textView_My_Hero_Name);
        textView_My_Hero_HP = (TextView) findViewById(R.id.textView_My_Hero_HP);
        imageView_My_Hero = (ImageView) findViewById(R.id.imageView_My_Hero);
        textView_My_Hero_Name.setText(heroChosen);
        textView_My_Hero_HP.setText("HP: " + String.valueOf(heroHP));
        int hero_id = getResources().getIdentifier(heroChosen, "drawable", getPackageName());
        imageView_My_Hero.setImageResource(hero_id);

        textView_Enemy_Hero_Name = (TextView) findViewById(R.id.textView_Enemy_Hero_Name);
        textView_Enemy_Hero_HP = (TextView) findViewById(R.id.textView_Enemy_Hero_HP);
        imageView_Enemy_Hero = (ImageView) findViewById(R.id.imageView_Enemy_Hero);
        textView_Enemy_Hero_Name.setText(enemyChosen);
        textView_Enemy_Hero_HP.setText("HP: " + String.valueOf(enemyHP));
        int enemy_id = getResources().getIdentifier(enemyChosen, "drawable", getPackageName());
        imageView_Enemy_Hero.setImageResource(enemy_id);

    }

    public void onClickFight(View view){
        Intent intent = new Intent(this, MovesActivity.class);
        intent.putExtra("HeroChosen", heroChosen);
        intent.putExtra("HeroValue", heroValue);
        intent.putExtra("HeroHP", heroHP);
        intent.putExtra("HeroMP", heroMP);

        intent.putExtra("EnemyChosen", enemyChosen);
        intent.putExtra("EnemyValue", enemyValue);
        intent.putExtra("EnemyHP", enemyHP);
        intent.putExtra("EnemyMP", enemyMP);
        startActivity(intent);
    }

    // If user chickens away, it should add a Loss to their record
    public void onClickRun(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
