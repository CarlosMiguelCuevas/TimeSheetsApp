package rraya.nearsoft.com.timesheetsapp.notifications

import android.annotation.SuppressLint
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.os.Build
import android.support.v4.app.NotificationCompat
import rraya.nearsoft.com.timesheetsapp.R

class NotificationHelper {

    companion object {
        val REPEATED_NOTIFICATION_ID = 201
        val EDIT_PENDING_INTENT_ID = 202
        val SUBMIT_PENDING_INTENT_ID = 203
        private val NOTIFICATION_CHANNEL = "300"
    }

    var mClientName: String? = null

    fun buildEditSendTimeSheetNotification(context: Context, pendingIntentEdit: PendingIntent?, pendingIntentSubmit: PendingIntent?, clientName: String, hours: String = "40"): NotificationCompat.Builder {

        mClientName = clientName

        val sendAsIsAction = NotificationCompat.Action(R.drawable.send_timesheet_icon, "Send", pendingIntentSubmit)
        val editAction = NotificationCompat.Action(R.drawable.edit_timesheet, "Edit", pendingIntentEdit)

        //channel
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            createChannel(context)
        }

        return NotificationCompat.Builder(context, NOTIFICATION_CHANNEL)
                .setContentIntent(pendingIntentEdit)
                .setContentTitle(clientName)
                .setContentText("${hours} Hours")
                .setSmallIcon(R.drawable.timesheet_notif_icon)
                .setColor(context.resources.getColor(R.color.colorPrimary))
                .addAction(editAction)
                .addAction(sendAsIsAction)
                .setAutoCancel(true)
    }

    fun buildProgressNotification(context: Context, icon: Int, description: String): NotificationCompat.Builder {

        //channel
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            createChannel(context)
        }

        return NotificationCompat.Builder(context, NOTIFICATION_CHANNEL)
                .setContentTitle(mClientName)
                .setContentText(description)
                .setSmallIcon(icon)
                .setColor(context.resources.getColor(R.color.colorPrimary))
                .setProgress(0, 0, true)
                .setAutoCancel(false)
    }

    fun buildDefaultProgressNotification(context: Context, description: String): NotificationCompat.Builder {
        return buildProgressNotification(context, R.drawable.timesheet_notif_icon, description)
    }

    fun buildUploadProgressNotification(context: Context, description: String = context.getString(R.string.progress_notification_default_description)): NotificationCompat.Builder {
        return buildProgressNotification(context, R.drawable.upload_timesheet_notification_icon, description)
    }

    fun buildSimpleNotification(context: Context, description: String): NotificationCompat.Builder {
        //channel
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            createChannel(context)
        }

        return NotificationCompat.Builder(context, NOTIFICATION_CHANNEL)
                .setContentTitle(mClientName)
                .setContentText(description)
                .setSmallIcon(R.drawable.timesheet_notif_icon)
                .setColor(context.resources.getColor(R.color.colorPrimary))
                .setProgress(0, 0, false)
                .setAutoCancel(false)
    }

    fun notify(context: Context, id: Int, notification: Notification) {
        (context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager).notify(id, notification)
    }

    fun dismissNotification(context: Context, id: Int) {
        (context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager).cancel(id)
    }

    @SuppressLint("NewApi")
    private fun createChannel(context: Context) {
        val defaultChannel = NotificationChannel(NOTIFICATION_CHANNEL, context.getString(R.string.notification_channel_name_default), NotificationManager.IMPORTANCE_DEFAULT)
        defaultChannel.description = context.getString(R.string.notification_channel_description)
        val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(defaultChannel)
    }

}