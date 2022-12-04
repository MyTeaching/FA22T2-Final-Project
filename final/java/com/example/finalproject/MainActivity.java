package com.example.finalproject;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

/*
 * MainActivity (Starting Point aka where we pick our character)
 * once the character is chosen it'll be passed to BattleActivity.java
 *
 * We need to add Win/Loss system.
 * */

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    private TextView textView_counter;
    private int totalWin, totalLose;
    private String heroChosen;
    private int heroValue, heroHP, heroMP;
    private String enemyChosen;
    private int enemyValue, enemyHP, enemyMP;
    private String[] hero_name, hero_HP, hero_MP;
    private ArrayList hero_list_name, hero_list_hp, hero_list_mp;
    private GameSettings settings;
    private ConstraintLayout background;
    public static final String SHARED_PREFS = "sharedPrefs";
    private static final String COLOR = "color";
    public static final String TROPHY_IMG = "trophyImage";
    public static final String ISCOLORCHANGED = "colorBoolean";

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putSerializable("settings", settings);
        // do this for each or your Spinner
        // You might consider using Bundle.putStringArray() instead
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
       settings = (GameSettings) savedInstanceState.getSerializable("settings");


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getIntentData();
        textView_counter = (TextView) findViewById(R.id.textView_counter);
        Resources res = getResources();
        hero_name = res.getStringArray(R.array.hero_name);
        hero_HP = res.getStringArray(R.array.hero_HP);
        hero_MP = res.getStringArray(R.array.hero_MP);
        hero_list_name = new ArrayList<String>(Arrays.asList(hero_name));
        hero_list_hp = new ArrayList<String>(Arrays.asList(hero_HP));
        hero_list_mp = new ArrayList<String>(Arrays.asList(hero_MP));
        Log.d(TAG, "ArrayList Names: " + hero_list_name);
        Log.d(TAG, "ArrayList HP: " + hero_list_hp);
        Log.d(TAG, "ArrayList MP: " + hero_list_mp);
        loadViews();

        loadSettings();

        loadData();
        updateViews();
        saveData();
    }

    public void onClickMoves(View view) {
        switch (view.getId()) {
            case  R.id.genji: {
                heroValue = 0;
                findHero(heroValue);
                break;
            }
            case R.id.mei: {
                heroValue = 1;
                findHero(heroValue);
                break;
            }
            case R.id.moira: {
                heroValue = 2;
                findHero(heroValue);
                break;
            }
            case R.id.cassidy: {
                heroValue = 3;
                findHero(heroValue);
                break;
            }
            case R.id.roadhog: {
                heroValue = 4;
                findHero(heroValue);
                break;
            }
            case R.id.reaper: {
                heroValue = 5;
                findHero(heroValue);
                break;
            }
            case R.id.mercy: {
                heroValue = 6;
                findHero(heroValue);
                break;
            }
            case R.id.widow: {
                heroValue = 7;
                findHero(heroValue);
                break;
            }
            default:
                break;
        }

    }

    private void findHero(int heroValue) {
        heroChosen = (String) hero_list_name.get(heroValue);
        heroHP = ArraySearchHP(heroValue);
        heroMP = ArraySearchMP(heroValue);

        Log.d(TAG, "Hero Name: " + heroChosen);
        Log.d(TAG, "Hero HP: " + heroHP);
        Log.d(TAG, "Hero MP: " + heroMP);

        findEnemy();
    }

    public void findEnemy() {
        Random random = new Random();
        enemyValue = random.nextInt(7);

        enemyChosen = (String) hero_list_name.get(enemyValue);
        enemyHP = ArraySearchHP(enemyValue);
        enemyMP = ArraySearchMP(enemyValue);

        Log.d(TAG, "Enemy Name: " + enemyChosen);
        Log.d(TAG, "Enemy HP: " + enemyHP);
        Log.d(TAG, "Enemy MP: " + enemyMP);

        launchBattle();
    }

//     Chanyu - Notes
//     If anyone doesn't understand why I implemented this:

//     Array Search Function was necessary, because
//     ArrayList<Integer>.asList only returns 1st index (when I need the entire list)
//     so I didn't want to loop the entire ArrayList, just to grab an int
//     since String had no issues with .asList(),
//     I grabbed everything as String, then converted to int
    public int ArraySearchHP(int index) {
        String temp_string = (String) hero_list_hp.get(index);
        return Integer.valueOf(temp_string);
    }

    public int ArraySearchMP(int index) {
        String temp_string = (String) hero_list_mp.get(index);
        return Integer.valueOf(temp_string);
    }

    // After character selected + enemy found, this will be triggered
    public void launchBattle() {
        Intent intent = new Intent(this, BattleActivity.class);
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


    public void loadViews(){

        background = (ConstraintLayout) findViewById(R.id.main_background);
    }


    //-----------------------------------------------------------------------------------

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_menu,menu);
        return true;
    }

    //-----------------------------------------------------------------------------------

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.item1:
                Log.d(TAG, "onOptionsItemSelected: you clicked on item 1!");
                Intent intent = new Intent(this, SettingsActivity.class);
                intent.putExtra("current_settings", settings);
                startActivity(intent);
                break;
            case R.id.item2:
                Log.d(TAG, "onOptionsItemSelected: you clicked on item 2!");
                break;
            case R.id.item3:
                Log.d(TAG, "onOptionsItemSelected: you clicked on item 3!");
                break;
            case R.id.item4:
                Log.d(TAG, "onOptionsItemSelected: you clicked on item 4!");
                break;

            default:
                return super.onOptionsItemSelected(item);
        }
        return super.onOptionsItemSelected(item);
    }

    public void loadSettings(){

        if(settings == null) {
            settings = new GameSettings();
            background.setBackgroundResource(settings.getBgColor());
        }else {
            Log.d(TAG, "loadSettings: trophy is: " + settings.getTrophyString());
            Log.d(TAG, "loadSettings: is color chnaged: " + settings.getColorChanged());

                //Log.d(TAG, "loadSettings: settings bg color is: " + settings.getBgColor());
            background.setBackgroundColor(settings.getBgColor());

        }
    }

    public void getIntentData(){
        Intent intent = getIntent();
        if(intent.getSerializableExtra("updated_settings") != null) {
            settings = (GameSettings) intent.getSerializableExtra("updated_settings");
        }
    }

    public void saveData(){
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(COLOR, settings.getBgColor());
        editor.putInt(TROPHY_IMG, settings.getTrophy());
        editor.putBoolean(ISCOLORCHANGED, settings.getColorChanged());
        editor.apply();
    }

    public void loadData(){
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        settings.setTrophy(sharedPreferences.getInt(TROPHY_IMG, 0));
        settings.setBgColor(sharedPreferences.getInt(COLOR, 0));
        settings.setColorChanged(sharedPreferences.getBoolean(ISCOLORCHANGED, false));
    }

    public void updateViews(){
        background.setBackgroundColor(settings.getBgColor());
    }
}