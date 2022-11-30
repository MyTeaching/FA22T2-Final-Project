package com.example.android_group_project;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity{

    private static final String TAG = "Main_Activity";
    private ArrayList<Character>listOfCharacters;
    private Character enemy;
    private Character heroChosen;
    private ConstraintLayout layout;
    private CardView genjiView;
    private CardView meiView;
    private CardView moiraView;
    private CardView cassidyView;
    private CardView roadhogView;
    private CardView reaperView;
    private CardView mercyView;
    private CardView widowView;
    private GameSettings settings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "onCreate: On MainActivity");
        Window window = getWindow();
        window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
        );
        loadCharacters();
        loadViews();
        loadSettings();
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

    //-----------------------------------------------------------------------------------

    public void loadCharacters(){
        enemy = new Character();
        heroChosen = new Character();
        listOfCharacters = new ArrayList<>();
        Log.d(TAG, "onCreate: heroChosen attack 1 is: " + heroChosen.getFirstAttack());
        Log.d(TAG, "onCreate: enemy attack 1 is: " + enemy.getFirstAttack());
        String[]genjiMoves = {"SHURIKEN", "DEFLECT ONCOMING PROJECTILES BACK", "DRAGONBLADE"};
        Character genji = new Character("Genji", R.drawable.genji,genjiMoves);
        listOfCharacters.add(genji);
        String[] meiMoves ={"ENDOTHERMIC BLASTER","ICE WALL OFF MAP","CAST A BLIZZARD TO FREEZE"};
        Character mei = new Character("Mei", R.drawable.mei, meiMoves);
        listOfCharacters.add(mei);
        String[] moiraMoves = {"SOUL SUCK","CAST BIOTIC ORB","COALESCENCE"};
        Character moira = new Character("Moira", R.drawable.moira,moiraMoves);
        listOfCharacters.add(moira);
        String[] cassidyMoves = {"PEACEKEEPER", "MAGNETIC GRENADE", "DEADEYE - IT'S HIGH NOON"};
        Character cassidy = new Character("Cassidy", R.drawable.cassidy,cassidyMoves);
        listOfCharacters.add(cassidy);
        String[] roadhogMoves = {"SCRAP GUN", "CHAIN HOOK", "WHOLE HOG"};
        Character roadhog = new Character("RoadHog", R.drawable.roadhog, roadhogMoves);
        listOfCharacters.add(roadhog);
        String[] reaperMoves = {"HELLFIRE SHOTGUNS", "THE REAPING", "DEATH BLOSSOM"};
        Character reaper = new Character("Reaper", R.drawable.reaper, reaperMoves);
        listOfCharacters.add(reaper);
        String[] mercyMoves ={"CADUCEUS BLASTER","RESURRECT", "VALKYRIE"};
        Character mercy = new Character("Mercy", R.drawable.mercy, mercyMoves);
        listOfCharacters.add(mercy);
        String[] widowMoves = {"WIDOW'S KISS", "VENOM MINE", "INFRA-SIGHT"};
        Character widow= new Character("Widow", R.drawable.widow, widowMoves);
        listOfCharacters.add(widow);
    }

    //-----------------------------------------------------------------------------------

    public void loadViews(){
        genjiView = (CardView) findViewById(R.id.genji);
        meiView = (CardView) findViewById(R.id.mei);
        moiraView = (CardView) findViewById(R.id.moira);
        cassidyView = (CardView) findViewById(R.id.cassidy);
        roadhogView = (CardView) findViewById(R.id.roadhog);
        reaperView = (CardView) findViewById(R.id.reaper);
        mercyView = (CardView) findViewById(R.id.mercy);
        widowView = (CardView) findViewById(R.id.widow);
        layout = findViewById(R.id.constraint_layout);
        genjiView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                heroChosen = listOfCharacters.get(0);
                generateEnemy();
                launchMovesActivity();}
        });
        meiView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                heroChosen = listOfCharacters.get(1);
                generateEnemy();
                launchMovesActivity();}
        });
        moiraView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                heroChosen = listOfCharacters.get(2);
                generateEnemy();
                launchMovesActivity();}
        });
        cassidyView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                heroChosen = listOfCharacters.get(3);
                generateEnemy();
                launchMovesActivity();}
        });
        roadhogView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                heroChosen = listOfCharacters.get(4);
                generateEnemy();
                launchMovesActivity();}
        });
        reaperView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                heroChosen = listOfCharacters.get(5);
                generateEnemy();
                launchMovesActivity();}
        });
        mercyView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                heroChosen = listOfCharacters.get(6);
                generateEnemy();
                launchMovesActivity();}
        });
        widowView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                heroChosen = listOfCharacters.get(7);
                generateEnemy();
                launchMovesActivity();}
        });
    }

    //-----------------------------------------------------------------------------------

    public void loadSettings(){
        if(settings == null) {
            settings = new GameSettings();
        }
        layout.setBackgroundResource(settings.getBgColor());
    }

    //-----------------------------------------------------------------------------------

    public void generateEnemy(){
        int randNumber = (int) (Math.random() * (listOfCharacters.size()));
        enemy = listOfCharacters.get(randNumber);
        if(enemy.equals(heroChosen)){
            generateEnemy();
        }
    }

    //-----------------------------------------------------------------------------------

    public void launchMovesActivity(){
        Intent intent = new Intent(this, MovesActivity.class);
        intent.putExtra("hero_choice", heroChosen);
        intent.putExtra("enemyGenerated", enemy);
        startActivity(intent);
    }
}