package rraya.nearsoft.com.timesheetsapp.notifications

import android.app.AlarmManager
import android.app.Notification
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import rraya.nearsoft.com.timesheetsapp.R
import java.util.*

class NotificationHelper {

    private var alarmManager: AlarmManager? = null
    private var alarmReceiverPedingIntent: PendingIntent? = null

    companion object {
        var ALARM_PENDING_INTENT_ID = 100
        var REPEATED_NOTIFICATION_ID = 101
        var EDIT_PENDING_INTENT_ID = 102
    }

    fun scheduleRepeatingTimesheetNotification(context: Context) {
        val calendar = Calendar.getInstance()
        calendar.setTimeInMillis(System.currentTimeMillis())
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.FRIDAY)
        calendar.set(Calendar.HOUR_OF_DAY, 12)

        val alarmReceiverIntent = Intent(context, AlarmReceiver::class.java)
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

    fun buildEditSendTimeSheetNotification(context: Context, pendingIntentEdit: PendingIntent?, pendingIntentSend: PendingIntent?, clientName: String, hours: String = "40"): Notification.Builder {

        //TODO Add action to send timesheet as is.
        val sendAsIsIntent = null
        val sendAsIsAction = Notification.Action(R.drawable.send_timesheet_icon, "Send", sendAsIsIntent)
        val editAction = Notification.Action(R.drawable.edit_timesheet, "Edit", pendingIntentEdit)

        return Notification.Builder(context)
                .setContentIntent(pendingIntentEdit)
                .setContentTitle(clientName)
                .setContentText(hours)
                .setSmallIcon(R.drawable.timesheet_notif_icon)
                .setColor(context.resources.getColor(R.color.colorPrimary))
                .addAction(editAction)
                .addAction(sendAsIsAction)
                .setAutoCancel(true)
    }
}