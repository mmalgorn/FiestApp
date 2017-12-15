package andoird.fiestapp.Object;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;

import andoird.fiestapp.MainActivity;
import andoird.fiestapp.R;

/**
 * Created by nicod on 14/12/2017.
 */

public class SoireeNotification {

    static int id=0;

    public void addNotificationSet(Context context, String text) {
        NotificationCompat.Builder builder =
                new NotificationCompat.Builder(context)
                        .setSmallIcon(R.drawable.com_facebook_button_icon)
                        .setContentTitle(context.getString(R.string.app_name))
                        .setContentText(text);

        Intent notificationIntent = new Intent(context, MainActivity.class);
        PendingIntent contentIntent = PendingIntent.getActivity(context, 0, notificationIntent,
                PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(contentIntent);

        // Add as notification
        NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        manager.notify(this.id, builder.build());
        this.id++;
    }

    public void addNotificationContest(Context context,String text) {
        NotificationCompat.Builder builder =
                new NotificationCompat.Builder(context)
                        .setSmallIcon(R.drawable.com_facebook_button_icon)
                        .setContentTitle(context.getString(R.string.app_name))
                        .setContentText(text);

        Intent notificationIntent = new Intent(context, MainActivity.class);
        PendingIntent contentIntent = PendingIntent.getActivity(context, 0, notificationIntent,
                PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(contentIntent);

        // Add as notification
        NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        manager.notify(this.id, builder.build());
        this.id++;
    }


}
