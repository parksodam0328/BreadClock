package kr.hs.emirim.parksodam.mirimbreadclock2;

/**
 * Created by parksodam on 2017-09-28.
 */

import android.content.Intent;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class MyFirebaseMessagingService extends FirebaseMessagingService {

    private static final String TAG = "MyFirebaseMsgService";

    /**
     * Called when message is received.
     *
     * @param remoteMessage Object representing the message received from Firebase Cloud Messaging.
     */
    // [START receive_message]
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {

        // Check if message contains a notification payload.
        if (remoteMessage.getNotification() != null) {
            Log.d(TAG, "Message Notification Body: " + remoteMessage.getNotification().getBody());
            Intent intent = new Intent();
            intent.setAction("kr.hs.emirim.parksodam.mirimbreadclock.fcm");
            intent.addFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES);
            intent.putExtra("body", remoteMessage.getNotification().getBody());
            intent.putExtra("title", remoteMessage.getNotification().getTitle());
            sendBroadcast(intent);
        }
    }
    // [END receive_message]

}