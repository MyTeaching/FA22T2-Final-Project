package com.example.finalproject;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

/*
 * This is where user picks the move and they don't see what enemy picked
 * UNTIL they return to BattleActivity.java aka after they pick the move.
 *
 * if they win -> WinnerActivity.java (We need to create this class)
 * WinnerActivity.java will also have a notification - win = alarm
 * it'll show how much HP you won by, against whom + your total win record
 *
 * if they loss -> LoserActivity.java (We need to create this class)
 * LoserActivity.java = will show how much HP you lost by, against whom + your total loss record
 *
 * if neither of them aren't dead yet -> return to BattleActivity.java
 * user can see enemy's HP + its own HP and can choose to run or fight.
 *
 * Adding MP + Regen speed will add complexity, so we'll ignore for now
 *
 *
 * */

public class MovesActivity extends AppCompatActivity {

    //private static final String TAG = MainActivity.class.getSimpleName();
    private static final String TAG = ".MovesActivity";
    private TextView textView_My_Hero_Name2, textView_My_Hero_HP2;
    private Button button_Move1, button_Move2, button_Move3;
    private String text_Move1, text_Move2, text_Move3;

    private String heroChosen;
    private int heroValue, heroHP, heroMP, heroMove;

    private String enemyChosen;
    private int enemyValue, enemyHP, enemyMP, enemyMove;

    private String[] hero_move1, hero_move2, hero_move3;
    private ArrayList hero_move1_list, hero_move2_list, hero_move3_list;
    private String[] hero_move1_dmg, hero_move2_dmg, hero_move3_dmg;
    private ArrayList hero_move1_dmglist, hero_move2_dmglist, hero_move3_dmglist;

    private String moveChosen, moveEnemyChosen;

    private ConstraintLayout background;
    private GameSettings settings;
    public static final String SHARED_PREFS = "sharedPrefs";
    private static final String COLOR = "color";
    public static final String TROPHY_IMG = "trophyImage";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.moves);

        Bundle extras = getIntent().getExtras();
        heroChosen = extras.getString("HeroChosen");
        heroValue = extras.getInt("HeroValue");
        heroHP = extras.getInt("HeroHP");
        heroMP = extras.getInt("HeroMP");

        enemyChosen = extras.getString("EnemyChosen");
        enemyValue = extras.getInt("EnemyValue");
        enemyHP = extras.getInt("EnemyHP");
        enemyMP = extras.getInt("EnemyMP");

        textView_My_Hero_Name2 = (TextView) findViewById(R.id.textView_My_Hero_Name2);
        textView_My_Hero_HP2 = (TextView) findViewById(R.id.textView_My_Hero_HP2);
        textView_My_Hero_Name2.setText(heroChosen);
        textView_My_Hero_HP2.setText("HP: " + String.valueOf(heroHP));

        Resources res = getResources();
        hero_move1 = res.getStringArray(R.array.hero_move1);
        hero_move2 = res.getStringArray(R.array.hero_move2);
        hero_move3 = res.getStringArray(R.array.hero_move3);

        hero_move1_list = new ArrayList<String>(Arrays.asList(hero_move1));
        hero_move2_list = new ArrayList<String>(Arrays.asList(hero_move2));
        hero_move3_list = new ArrayList<String>(Arrays.asList(hero_move3));

        hero_move1_dmg = res.getStringArray(R.array.hero_move1_dmg);
        hero_move2_dmg = res.getStringArray(R.array.hero_move2_dmg);
        hero_move3_dmg = res.getStringArray(R.array.hero_move3_dmg);

        hero_move1_dmglist = new ArrayList<String>(Arrays.asList(hero_move1_dmg));
        hero_move2_dmglist = new ArrayList<String>(Arrays.asList(hero_move2_dmg));
        hero_move3_dmglist = new ArrayList<String>(Arrays.asList(hero_move3_dmg));

        Log.d(TAG, "ArrayList Move1: " + hero_move1_list);
        Log.d(TAG, "ArrayList Move2: " + hero_move2_list);
        Log.d(TAG, "ArrayList Move3: " + hero_move3_list);

        button_Move1 = (Button) findViewById(R.id.button_Move1);
        button_Move2 = (Button) findViewById(R.id.button_Move2);
        button_Move3 = (Button) findViewById(R.id.button_Move3);

        text_Move1 = (String) hero_move1_list.get(heroValue);
        button_Move1.setText(text_Move1);

        text_Move2 = (String) hero_move2_list.get(heroValue);
        button_Move2.setText(text_Move2);

        text_Move3 = (String) hero_move3_list.get(heroValue);
        button_Move3.setText(text_Move3);

        background = (ConstraintLayout) findViewById(R.id.moves_background);

        settings = (GameSettings) extras.getSerializable("settings");
        if(settings == null){
            settings = new GameSettings();
            background.setBackgroundResource(settings.getBgColor());
        }else{
            background.setBackgroundColor(settings.getBgColor());
        }


        loadData();
        updateViews();
        saveData();
        Log.d(TAG, "onCreate: trophy is: " + settings.getTrophyString());
    }

    public void move1 (View view){
        moveChosen = (String) hero_move1_dmglist.get(heroValue);
        heroMove = Integer.valueOf(moveChosen);
        enemyPickMove();
    }

    public void move2 (View view){
        moveChosen = (String) hero_move2_dmglist.get(heroValue);
        heroMove = Integer.valueOf(moveChosen);
        enemyPickMove();
    }

    public void move3 (View view){
        moveChosen = (String) hero_move3_dmglist.get(heroValue);
        heroMove = Integer.valueOf(moveChosen);
        enemyPickMove();
    }

    public void enemyPickMove() {
        Random random = new Random();
        int enemy_picked = random.nextInt(2);

        if (enemy_picked == 0) {
            moveEnemyChosen = (String) hero_move1_dmglist.get(enemyValue);
        } else if (enemy_picked == 1) {
            moveEnemyChosen = (String) hero_move2_dmglist.get(enemyValue);
        } else if (enemy_picked == 2) {
            moveEnemyChosen = (String) hero_move3_dmglist.get(enemyValue);
        }
        enemyMove = Integer.valueOf(moveEnemyChosen);
        Log.d(TAG, "Hero Chose: " + heroMove);
        Log.d(TAG, "Enemy Chose: " + enemyMove);

        calculateBattle();
    }

    public void calculateBattle() {
        heroHP = heroHP - enemyMove;
        enemyHP = enemyHP - heroMove;

        Log.d(TAG, "Hero HP: " + heroHP);
        Log.d(TAG, "Enemy HP: " + enemyHP);

        if (heroHP > 0 || enemyHP > 0) {
            sendBack();
        }
        if (enemyHP <= 0) {
            winnerFound();
        }
        if (heroHP <= 0) {
            loserFound();
        }
    }

    // goes back to battle activity
    public void sendBack() {
        Intent intent = new Intent(this, BattleActivity.class);
        intent.putExtra("HeroChosen", heroChosen);
        intent.putExtra("HeroValue", heroValue);
        intent.putExtra("HeroHP", heroHP);
        intent.putExtra("HeroMP", heroMP);
        intent.putExtra("settings_to_battle", settings);
        intent.putExtra("EnemyChosen", enemyChosen);
        intent.putExtra("EnemyValue", enemyValue);
        intent.putExtra("EnemyHP", enemyHP);
        intent.putExtra("EnemyMP", enemyMP);
        startActivity(intent);
    }

    // NEED TO WORK!!
    // goes to the winners activity screen
    public void winnerFound() {
        Intent intent = new Intent(this, WinnerActivity.class);
        intent.putExtra("HeroChosen", heroChosen);
        intent.putExtra("HeroValue", heroValue);
        intent.putExtra("HeroHP", heroHP);
        intent.putExtra("HeroMP", heroMP);
        intent.putExtra("settings_to_winner", settings);
        intent.putExtra("EnemyChosen", enemyChosen);
        intent.putExtra("EnemyValue", enemyValue);
        intent.putExtra("EnemyHP", enemyHP);
        intent.putExtra("EnemyMP", enemyMP);
        startActivity(intent);
    }

    // NEED TO WORK!!
    // goes to the losers activity screen
    public void loserFound() {
        Intent intent = new Intent(this, LoserActivity.class);
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
