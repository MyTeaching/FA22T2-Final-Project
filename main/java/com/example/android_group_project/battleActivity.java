package com.example.android_group_project;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class battleActivity extends AppCompatActivity{

    private static final String TAG = "Battle Activity";
    private Character enemy;
    private Character hero;
    private String heroMove;
    private String enemyMove;
    private TextView heroView;
    private TextView enemyView;
    private TextView heroMoveView;
    private TextView enemyMoveView;
    private Button move;
    private boolean isRunning;
    private Thread thread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_battle);
        Log.d(TAG, "onCreate: On battleActivity");
        //This is the views for the activity_battle.xml
        //this'll be the one displaying the heroName then the move the user chose for it
        Intent intent = getIntent();
        if(intent != null) {
            if(intent.getSerializableExtra("enemy") != null) {
                enemy = (Character) intent.getSerializableExtra("enemy");
            }
            if(intent.getSerializableExtra("heroChosen") != null) {
                hero = (Character) intent.getSerializableExtra("heroChosen");
            }
            Log.d(TAG, "onCreate: Hero hp is: " + hero.getHp());
            Log.d(TAG, "onCreate: enemy hp id: " + enemy.getHp());
        }
       // heroMove = hero.getChosenAttack();
        // enemyMove = enemy.getChosenAttack();

        Window window = getWindow();
        window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN

        );
        loadViews();

        if(hero.getAttackDamage() > enemy.getAttackDamage()){
            enemy.decreaseHpBy(hero.getAttackDamage());
        }else if(enemy.getAttackDamage() > hero.getAttackDamage()){
            hero.decreaseHpBy(enemy.getAttackDamage());
        }

        Log.d(TAG, "onCreate: Hero hp is: " + hero.getHp());
        Log.d(TAG, "onCreate: enemy hp id: " + enemy.getHp());


    }

    private void loadViews(){
        heroView = (TextView) findViewById(R.id.hero);
        enemyView = (TextView) findViewById(R.id.enemy);
        heroMoveView  = (TextView) findViewById(R.id.usersChoice);
        enemyMoveView =  (TextView) findViewById(R.id.enemyChoice);
        heroView.setText(hero.getName());
        enemyView.setText(enemy.getName());
        heroMoveView.setText(heroMove);
        enemyMoveView.setText(enemyMove);
        move = (Button) findViewById(R.id.chosemove);
        move.setText("Choose Move");
        move.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), MovesActivity.class);
                intent.putExtra("hero", hero);
                intent.putExtra("enemy", enemy);
                startActivity(intent);
            }
        });

    }




}