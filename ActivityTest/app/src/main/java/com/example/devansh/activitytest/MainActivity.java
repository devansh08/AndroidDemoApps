package com.example.devansh.activitytest;

import android.app.Activity;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    public static final String EXTRA_MESSAGE = "com.example.devansh.activitytest.MESSAGE";
    String message;

    EditText messageBox;
    Button sendMsg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        messageBox = (EditText) findViewById(R.id.editText);

        sendMsg = (Button) findViewById(R.id.button);

        sendMsg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMessage();
            }
        });
    }

    public void sendMessage() {
        int notificationId = 001;

        Intent intent = new Intent(this, RandomActivity.class);
        message = messageBox.getText().toString();

        intent.putExtra(EXTRA_MESSAGE, message);
        //startActivity(intent);

        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("Activity Notification")
                .setContentText("Click this Notification to start Activity");
        mBuilder.setContentIntent(pendingIntent);
        mBuilder.setAutoCancel(true);

        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notificationManager.notify(notificationId, mBuilder.build());
    }
}
