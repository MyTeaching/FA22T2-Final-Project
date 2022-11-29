package com.example.finalproject;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Random;

/*
 * MainActivity (Starting Point aka where we pick our character)
 * once the character is chosen it'll be passed to BattleActivity.java
 *
 * We need to add Win/Loss system.
 * */

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    private NotificationManager notificationManager;
    private static final String PRIMARY_CHANNEL_ID = "primary_notification_channel"; //API 26 and above each notification needs a channel id
    private static final String PRIMARY_CHANNEL_NAME = "my primary notification channel";//API 26 and above each notification needs a channel name
    private static final int IMPORTANCE_LEVEL = NotificationManager.IMPORTANCE_DEFAULT; //priority level
    private static final int NOTIFICATION_ID_0 = 0; //unique allows us to update notification

    private String heroChosen;
    private int heroValue, heroHP, heroMP;

    private String enemyChosen;
    private int enemyValue, enemyHP, enemyMP;

    private String[] hero_name, hero_HP, hero_MP;
    private ArrayList hero_list_name, hero_list_hp, hero_list_mp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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

        startActivity(intent);
    }

    protected void sendNotification() {
        Log.d(TAG, "inside sendNotification method");
        NotificationCompat.Builder notificationBuilder;
        Intent intent = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, NOTIFICATION_ID_0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        if(Build.VERSION.SDK_INT >= 26){//API 26 or greater needs channel
            Log.d(TAG, "inside API >=26 block of code");
            NotificationChannel notificationChannel = new NotificationChannel(PRIMARY_CHANNEL_ID, PRIMARY_CHANNEL_NAME, IMPORTANCE_LEVEL);
            notificationChannel.setDescription("Reminders");

            notificationManager.createNotificationChannel(notificationChannel);

            notificationBuilder = new NotificationCompat.Builder(this, PRIMARY_CHANNEL_ID)
                    .setContentTitle("MOVE AROUND Notification")
                    .setContentText("Get moving!")
                    .setSmallIcon(R.drawable.ic_action_victory);


        }
        else{//API less than 26 set priority on notification... not using a channel

            notificationBuilder =
                    new NotificationCompat.Builder(this)
                            .setPriority(IMPORTANCE_LEVEL)
                            .setContentTitle("MOVE AROUND Notification").setContentText("Get moving!")
                            .setSmallIcon(R.drawable.ic_action_victory);


        }

        notificationBuilder.setContentIntent(pendingIntent);
        notificationManager.notify(NOTIFICATION_ID_0, notificationBuilder.build());

        //disable the create new notification button
    }
}