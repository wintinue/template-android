package com.loggingwin.androidtemplate.util

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import com.loggingwin.androidtemplate.MainActivity
import com.loggingwin.androidtemplate.R


class NotificationTask(private val context: Context) {

    private val smallIcon = R.drawable.ic_launcher_background
    private val importantChannelId = context.getString(R.string.app_name) + 1
    private val unimportantChannelId = context.getString(R.string.app_name) + 2
    private val channelName = context.getString(R.string.app_name)

    private val notificationId = R.string.app_name


    fun showNotification(isImportant: Boolean) {

        val manager = createNotificationChannel(isImportant)

        // activity that move to when notification clicked
        val notificationIntent = Intent(context, MainActivity::class.java).apply {
            // clear activity stack and start in new task
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK

            /* do put data
            * ex) putExtra("data", 123)
            *  */
        }


        val notification = Notification.Builder(context, importantChannelId)
            .setSmallIcon(smallIcon)
            .setContentTitle("title")
            .setContentText("content")
            .setContentIntent(
                PendingIntent.getActivity(
                    context,
                    notificationId, // request code
                    notificationIntent,
                    PendingIntent.FLAG_CANCEL_CURRENT // if there is PendingIntent that same requestCode, cancel PendingIntent and set again
                )
            )
            .setVisibility(Notification.VISIBILITY_PUBLIC) // VISIBILITY_PUBLIC: show notification when lock screen
            .setAutoCancel(true)
            .setShowWhen(true) // show when notification created
            .build()


        manager.notify(notificationId, notification)
    }

    private fun createNotificationChannel(isImportant: Boolean = true): NotificationManager {
        val importance =
            if (isImportant) NotificationManager.IMPORTANCE_HIGH // show head-up and vibrate
            else NotificationManager.IMPORTANCE_MIN // no vibration, not shown on status bar
        val channel = NotificationChannel(
            if (isImportant) importantChannelId else unimportantChannelId,
            channelName,
            importance
        )

        // Register the channel with the system
        val notificationManager: NotificationManager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(channel)

        return notificationManager
    }
}