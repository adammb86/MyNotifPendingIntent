package com.example.adammb.mynotiifpendingintent;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Handler;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    public static final int NOTIFICATION_ID=1;
    private NotificationCompat.Builder notification;
    private Handler handler=new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    private Runnable runnable=new Runnable() {
        @Override
        public void run() {
            NotificationManager nm=(NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            nm.notify(NOTIFICATION_ID,notification.build());
        }
    };

    public void sendNotification(View view){
        Intent intent=new Intent(Intent.ACTION_VIEW, Uri.parse("http://codelabs.unikom.ac.id"));

        PendingIntent pendingIntent = PendingIntent.getActivity(this,0,intent,0);

        notification=(NotificationCompat.Builder)new NotificationCompat.Builder(this,"My Notification")
                .setSmallIcon(R.drawable.ic_notifications_white_24dp)
                .setContentIntent(pendingIntent)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(),R.drawable.ic_notifications_white_24dp))
                .setContentTitle(getResources().getString(R.string.content_title))
                .setContentText(getResources().getString(R.string.content_text))
                .setSubText(getResources().getString(R.string.subtext))
                .setAutoCancel(true);

        handler.postDelayed(runnable,5000);
    }
}
