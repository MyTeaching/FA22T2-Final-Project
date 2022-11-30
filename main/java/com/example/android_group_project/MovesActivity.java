package com.example.android_group_project;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MovesActivity extends AppCompatActivity {

    private static final String TAG= "Moves Activity";
    private Character player;
    private TextView move1;
    private TextView move2;
    private TextView move3;
    private ImageView avatarChosen;
    private String moveChosen;
    private Character enemy;
    private String enemyGeneratedMove;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.moves);
        Log.d(TAG, "onCreate: On MovesActivity");
        Intent intent = getIntent();
        if(intent != null) {
            if(intent.getSerializableExtra("hero_choice") != null) {
                player = (Character) intent.getSerializableExtra("hero_choice");
            }if(intent.getSerializableExtra("enemyGenerated") != null) {
                enemy = (Character) intent.getSerializableExtra("enemyGenerated");
            }if(intent.getSerializableExtra("hero") != null){
                player = (Character) intent.getSerializableExtra("hero");
            }if(intent.getSerializableExtra("enemy") != null){
                enemy = (Character) intent.getSerializableExtra("enemy");
            }
        }
        loadMovesViews();
        Window window = getWindow();
        window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN

        );
        move1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moveChosen = player.getFirstAttack();
                player.setChosenAttack(moveChosen);
                generateEnemyMove();
                launchBattleActivity();
            }
        });
        move2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moveChosen = player.getSecondAttack();
                player.setChosenAttack(moveChosen);
                generateEnemyMove();
                launchBattleActivity();
            }
        });
        move3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moveChosen = player.getThirdAttack();
                player.setChosenAttack(moveChosen);
                generateEnemyMove();
                launchBattleActivity();
            }
        });
    }

    public void loadMovesViews(){
        avatarChosen = (ImageView) findViewById(R.id.avatarImg);
        avatarChosen.setImageResource(player.getImage());
        move1 = (TextView) findViewById((R.id.move1button));
        move2 = (TextView) findViewById((R.id.move2button));
        move3 = (TextView) findViewById((R.id.move3button));
        if(player != null) {
            move1.setText(player.getFirstAttack());
            move2.setText(player.getSecondAttack());
            move3.setText(player.getThirdAttack());
        }
    }

    private void generateEnemyMove(){
        Log.d(TAG, "generateEnemyMove: Array size: " + enemy.getAllAttackMoves().length);
        int randNumber = (int) (Math.random() * enemy.getAllAttackMoves().length);
        if (randNumber == 0){
            enemyGeneratedMove = enemy.getFirstAttack();
            enemy.setChosenAttack(enemyGeneratedMove);
        } else if(randNumber == 1){
            enemyGeneratedMove = enemy.getSecondAttack();
            enemy.setChosenAttack(enemyGeneratedMove);
        }else if(randNumber == 2){
            enemyGeneratedMove = enemy.getThirdAttack();
            enemy.setChosenAttack(enemyGeneratedMove);
        }
    }

    public void launchBattleActivity(){
        Intent intent = new Intent(this, battleActivity.class);
        intent.putExtra("heroChosen", player);
        intent.putExtra("enemy",enemy);
        startActivity(intent);
    }
}
