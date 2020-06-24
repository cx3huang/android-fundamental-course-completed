package com.example.notificationscheduler;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.job.JobParameters;
import android.app.job.JobService;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;

import androidx.core.app.NotificationCompat;

public class NotificationJobService extends JobService {
    NotificationManager mNotifyManager;

    private static final String PRIMARY_CHANNEL_ID = "primary_notification_channel";

    @Override
    public boolean onStartJob(JobParameters jobParameters) {
        createNotificationChannel();
        PendingIntent contentPendingIntent = PendingIntent.getActivity(this, 0,
                new Intent(this, MainActivity.class), PendingIntent.FLAG_UPDATE_CURRENT);

        // Constructing and delivering a notification.
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this,
                PRIMARY_CHANNEL_ID)
                .setContentIntent(contentPendingIntent)
                .setContentTitle("Job Service")
                .setContentText("Your job ran to completion!")
                .setSmallIcon(R.drawable.ic_job_running)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setDefaults(NotificationCompat.DEFAULT_ALL)
                .setAutoCancel(true);

        mNotifyManager.notify(0, builder.build());
        return false;
    }

    @Override
    public boolean onStopJob(JobParameters jobParameters) {
        return true;
    }

    public void createNotificationChannel() {
        mNotifyManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel notificationChannel = new NotificationChannel(PRIMARY_CHANNEL_ID,
                    "Job Service Notification", NotificationManager.IMPORTANCE_HIGH);

            notificationChannel.enableVibration(true);
            notificationChannel.enableLights(true);
            notificationChannel.setLightColor(Color.RED);
            notificationChannel.setDescription("Notification from Job Service");

            mNotifyManager.createNotificationChannel(notificationChannel);
        }
    }
}
