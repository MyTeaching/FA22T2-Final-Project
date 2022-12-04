package com.example.finalproject;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

public class LoserActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    private NotificationManager notificationManager;
    private static final String PRIMARY_CHANNEL_ID = "primary_notification_channel";
    private static final String PRIMARY_CHANNEL_NAME = "my primary notification channel";
    private static final int IMPORTANCE_LEVEL = NotificationManager.IMPORTANCE_DEFAULT;
    private static final int NOTIFICATION_ID_0 = 0;

    private String heroChosen;
    private int heroValue, heroHP, heroMP;

    private String enemyChosen;
    private int enemyValue, enemyHP, enemyMP;

    private ImageView imageView_loser;
    private TextView textView_who_lost;
    private String LostBy_Text;

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
        LostBy_Text = "Your hero " + heroChosen + " lost the enemy hero by " + LostBy + " hp!";
        textView_who_lost.setText(LostBy_Text);
        int hero_id = getResources().getIdentifier(heroChosen, "drawable", getPackageName());
        imageView_loser.setImageResource(hero_id);

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
                    .setContentTitle("You've lost..")
                    .setContentText(LostBy_Text)
                    .setSmallIcon(R.drawable.ic_action_victory);
        } else {
            notificationBuilder = new NotificationCompat.Builder(this)
                    .setPriority(IMPORTANCE_LEVEL)
                    .setContentTitle("You've lost..").setContentText(LostBy_Text)
                    .setSmallIcon(R.drawable.ic_action_victory);
        }
        notificationBuilder.setContentIntent(pendingIntent);
        notificationManager.notify(NOTIFICATION_ID_0, notificationBuilder.build());
    }
}
