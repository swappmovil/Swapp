package com.swapp.Librerias.Notificaciones;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.app.TaskStackBuilder;
import android.support.v7.app.NotificationCompat;
import android.widget.RemoteViews;

import com.swapp.R;
import com.swapp.SwappMainActivity;

/**
 * Created by angel on 23/07/16.
 */

public class BarNotificaciones {

    private final int NOTIFICATION_ID = 271191;

    private SwappMainActivity activity;

    NotificationManager notificationManager;

    public BarNotificaciones(SwappMainActivity activity){
        this.activity = activity;
        notificationManager = (NotificationManager) activity.getSystemService(activity.NOTIFICATION_SERVICE);
    }

    public void triggerNotification(String titulo, String msg, Bitmap img){

        NotificationCompat.Builder builder =
                (NotificationCompat.Builder) new NotificationCompat.Builder(activity)
                        .setSmallIcon(R.drawable.actualizar)
                        .setLargeIcon(img)
                        .setContentTitle(titulo)
                        .setContentText(msg)
                        .setColor(activity.getResources().getColor(R.color.Dark));

        Intent intent = new Intent(activity, SwappMainActivity.class);
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(activity);
        stackBuilder.addParentStack(SwappMainActivity.class);
        stackBuilder.addNextIntent(intent);
        PendingIntent resultPendingIntent = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(resultPendingIntent);
        builder.setAutoCancel(true);

        notificationManager.notify(NOTIFICATION_ID, builder.build());
    }

}
