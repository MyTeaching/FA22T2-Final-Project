package com.example.finalproject;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

/*
* This is where you'll see your enemy
* If you choose to run away -> goes back to MainActivity.java
*
* IF you choose to fight -> sends user to MovesActivity.java
*
*
* */

public class BattleActivity extends AppCompatActivity {

    //private static final String TAG = MainActivity.class.getSimpleName();
    private static final String TAG = ".BattleActivity";
    private TextView textView_My_Hero_Name, textView_My_Hero_HP;
    private TextView textView_Enemy_Hero_Name, textView_Enemy_Hero_HP;
    private ImageView imageView_My_Hero, imageView_Enemy_Hero;

    private String heroChosen;
    private int heroValue, heroHP, heroMP;

    private String enemyChosen;
    private int enemyValue, enemyHP, enemyMP;

    private ConstraintLayout background;
    private GameSettings settings;
    public static final String SHARED_PREFS = "sharedPrefs";
    private static final String COLOR = "color";
    public static final String TROPHY_IMG = "trophyImage";

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

        background = (ConstraintLayout) findViewById(R.id.battle_background);
        settings = (GameSettings) extras.getSerializable("settings");
        GameSettings s = (GameSettings) extras.getSerializable("settings_to_battle");
        if(s != null){
            settings = s;
        }
        if(settings == null){
            settings = new GameSettings();
            background.setBackgroundResource(settings.getBgColor());
        }else{
            background.setBackgroundColor(settings.getBgColor());
        }
        Log.d(TAG, "onCreate: trophy is: " + settings.getTrophyString());

        loadData();
        updateViews();
        saveData();

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

        intent.putExtra("settings", settings);
        startActivity(intent);
    }

    // If user chickens away, it should add a Loss to their record
    public void onClickRun(View view){
        Intent intent = new Intent(this, LoserActivity.class);
        intent.putExtra("HeroChosen", heroChosen);
        intent.putExtra("HeroValue", heroValue);
        intent.putExtra("HeroHP", heroHP);
        intent.putExtra("HeroMP", heroMP);
        intent.putExtra("settings", settings);

        intent.putExtra("EnemyChosen", enemyChosen);
        intent.putExtra("EnemyValue", enemyValue);
        intent.putExtra("EnemyHP", enemyHP);
        intent.putExtra("EnemyMP", enemyMP);
        startActivity(intent);
    }

    public void saveData(){
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(COLOR, settings.getBgColor());
        editor.putInt(TROPHY_IMG, settings.getTrophy());
        editor.apply();
    }

    public void loadData(){
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        settings.setTrophy(sharedPreferences.getInt(TROPHY_IMG, 0));
        settings.setBgColor(sharedPreferences.getInt(COLOR, 0));
    }

    public void updateViews(){
        background.setBackgroundColor(settings.getBgColor());
    }

}
