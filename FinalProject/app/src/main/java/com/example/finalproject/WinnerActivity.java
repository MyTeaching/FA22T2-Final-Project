package com.example.finalproject;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class WinnerActivity extends AppCompatActivity {

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

    private ImageView imageView_winner;
    private TextView textView_who_won;
    private String WonBy_Text;

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
        WonBy_Text = "Your hero " + heroChosen + " won the enemy hero by " + WonBy + " hp!";
        textView_who_won.setText(WonBy_Text);
        int hero_id = getResources().getIdentifier(heroChosen, "drawable", getPackageName());
        imageView_winner.setImageResource(hero_id);

        notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        sendNotification();
    }

    public void onClickReturn(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    protected void sendNotification() {
        NotificationCompat.Builder notificationBuilder;
        Intent intent = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, NOTIFICATION_ID_0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        if(Build.VERSION.SDK_INT >= 26){
            NotificationChannel notificationChannel = new NotificationChannel(PRIMARY_CHANNEL_ID, PRIMARY_CHANNEL_NAME, IMPORTANCE_LEVEL);
            notificationChannel.setDescription("Reminders");
            notificationManager.createNotificationChannel(notificationChannel);
            notificationBuilder = new NotificationCompat.Builder(this, PRIMARY_CHANNEL_ID)
                    .setContentTitle("You've won!")
                    .setContentText(WonBy_Text)
                    .setSmallIcon(R.drawable.ic_action_victory);
        } else {
            notificationBuilder = new NotificationCompat.Builder(this)
                    .setPriority(IMPORTANCE_LEVEL)
                    .setContentTitle("You've won!").setContentText(WonBy_Text)
                    .setSmallIcon(R.drawable.ic_action_victory);
        }
        notificationBuilder.setContentIntent(pendingIntent);
        notificationManager.notify(NOTIFICATION_ID_0, notificationBuilder.build());
    }
}
