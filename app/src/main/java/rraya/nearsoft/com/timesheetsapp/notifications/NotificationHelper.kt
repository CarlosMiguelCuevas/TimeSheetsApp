package rraya.nearsoft.com.timesheetsapp.notifications

import android.annotation.SuppressLint
import android.app.*
import android.content.Context
import android.content.Intent
import android.os.Build
import android.support.v4.app.NotificationCompat
import android.util.Log
import rraya.nearsoft.com.timesheetsapp.R
import java.util.*

class NotificationHelper {

    private var alarmManager: AlarmManager? = null
    private var alarmReceiverPedingIntent: PendingIntent? = null

    companion object {
        val ALARM_PENDING_INTENT_ID = 100
        val REPEATED_NOTIFICATION_ID = 101
        val EDIT_PENDING_INTENT_ID = 102
        val SUBMIT_PENDING_INTENT_ID = 103
        private val NOTIFICATION_CHANNEL = "200"
        val EXTRA_CLIENT_NAME = "EXTRACLIENTNAME"
    }

    fun scheduleTimesheetReminderNotification(context: Context, clientName: String) {
        val calendar = Calendar.getInstance()
        calendar.setTimeInMillis(System.currentTimeMillis())
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.FRIDAY)
        calendar.set(Calendar.HOUR_OF_DAY, 12)

        val alarmReceiverIntent = Intent(context, AlarmReceiver::class.java)
        alarmReceiverIntent.putExtra(EXTRA_CLIENT_NAME, clientName)
        alarmReceiverPedingIntent = PendingIntent.getBroadcast(context, ALARM_PENDING_INTENT_ID, alarmReceiverIntent, PendingIntent.FLAG_UPDATE_CURRENT)

        alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager

        //I used setRepeateing instead of inexact because according to documentation `With setInexactRepeating(), you can't specify a custom interval the way you can with setRepeating()`
        //https://developer.android.com/training/scheduling/alarms.html
        alarmManager!!.setRepeating(AlarmManager.RTC_WAKEUP,
                calendar.timeInMillis, AlarmManager.INTERVAL_DAY * 7, alarmReceiverPedingIntent)
    }

    fun cancelAlarmRTC() {
        if (alarmManager != null) {
            alarmManager!!.cancel(alarmReceiverPedingIntent)
        }
    }

    fun notify(context: Context, id: Int, notification: Notification) {
        (context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager).notify(id, notification)
    }

    fun buildEditSendTimeSheetNotification(context: Context, pendingIntentEdit: PendingIntent?, pendingIntentSubmit: PendingIntent?, clientName: String, hours: String = "40"): NotificationCompat.Builder {

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

    @SuppressLint("NewApi")
    private fun createChannel(context: Context) {
        val defaultChannel = NotificationChannel(NOTIFICATION_CHANNEL, context.getString(R.string.notification_channel_name_default), NotificationManager.IMPORTANCE_DEFAULT)
        defaultChannel.description = context.getString(R.string.notification_channel_description)
        val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(defaultChannel)
    }

    fun buildErrorNotification() {
        //TODO:create the notification and notify it
        Log.d("NOTIFICATION", "error")
    }

    fun buildSuccesNotification() {
        //TODO:create the notification and notify it
        Log.d("NOTIFICATION", "success")
    }
}