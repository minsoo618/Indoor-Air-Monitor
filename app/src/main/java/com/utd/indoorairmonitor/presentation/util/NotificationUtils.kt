package com.utd.indoorairmonitor.presentation.util

import android.app.Notification
import android.app.NotificationManager
import android.app.PendingIntent
import androidx.core.app.RemoteInput
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Message
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.text.isDigitsOnly
import com.utd.indoorairmonitor.R
import com.utd.indoorairmonitor.presentation.MainActivity
import kotlinx.android.synthetic.main.fragment_home.*

private const val NOTIFICATION_ID = 0
val TXT_REPLY = "text_reply"

fun NotificationManager.sendNotification(messageBody: String, applicationContext: Context) {
    // content intent (if you press the notification it wil launch main activity)
    val contentIntent = Intent(applicationContext, MainActivity::class.java)

    // pending intent (will launch main activity even outside of the app)
    val contentPendingIntent = PendingIntent.getActivity(
            applicationContext,
            NOTIFICATION_ID,
            contentIntent,
            PendingIntent.FLAG_UPDATE_CURRENT)

    val notificationBuilder = NotificationCompat.Builder(applicationContext,applicationContext.getString(R.string.prediction_notification_channel_id))
            .setContentTitle(applicationContext.getString(R.string.notification_title))
            .setSmallIcon(R.drawable.nav_header)
            .setContentText(messageBody)
            .setContentIntent(contentPendingIntent)
            .setAutoCancel(true)
            .setPriority(NotificationCompat.PRIORITY_HIGH)

    if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.N){
        val remoteInput = RemoteInput.Builder(TXT_REPLY).setLabel("REPLY").build();

        val replyIntent = Intent(applicationContext, MainActivity::class.java)
        replyIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        val replyPendingIntent = PendingIntent.getActivity(applicationContext, 0, replyIntent, PendingIntent.FLAG_ONE_SHOT)

        val action = NotificationCompat.Action.Builder(R.drawable.nav_header,"Reply", replyPendingIntent)
                .addRemoteInput(remoteInput)
                .build()

        notificationBuilder.addAction(action)
    }

    notify(NOTIFICATION_ID, notificationBuilder.build())
}
fun recieveInput(intent: Intent) : String{
    val txtReply = TXT_REPLY
    val remoteReply = android.app.RemoteInput.getResultsFromIntent(intent)
    if (remoteReply != null){
        val x = remoteReply.getCharSequence(txtReply)
        return remoteReply.getCharSequence(txtReply).toString()
    }
    return ""
}