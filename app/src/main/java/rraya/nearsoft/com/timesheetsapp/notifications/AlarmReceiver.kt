package rraya.nearsoft.com.timesheetsapp.notifications

import android.app.Notification
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import rraya.nearsoft.com.timesheetsapp.R
import rraya.nearsoft.com.timesheetsapp.timesheetform.TimeSheetActivity


class AlarmReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {

        val intentEditTimesheet = Intent(context, TimeSheetActivity::class.java)
        intentEditTimesheet.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)

        val notificationHelper = NotificationHelper()

        val pendingIntentEditTimesheet = PendingIntent.getActivity(context, notificationHelper.ALARM_TYPE_RTC, intentEditTimesheet, PendingIntent.FLAG_UPDATE_CURRENT)

        val repeatedNotification = buildLocalNotification(context, pendingIntentEditTimesheet).build()
        notificationHelper.getNotificationManager(context).notify(notificationHelper.ALARM_TYPE_RTC, repeatedNotification)
    }

    fun buildLocalNotification(context: Context, pendingIntentEditTimesheet: PendingIntent?): Notification.Builder {

        //TODO Add action to send timesheet as is.
        val sendAsIsIntent = null
        val sendAsIsAction = Notification.Action(R.drawable.send_timesheet_icon, "Send", sendAsIsIntent)
        val editAction = Notification.Action(R.drawable.edit_timesheet, "Edit", pendingIntentEditTimesheet)

        var clientName = "Cliente"
        var hours = "40 hrs"
        return Notification.Builder(context)
                .setContentIntent(pendingIntentEditTimesheet)
                .setContentTitle(clientName)
                .setContentText(hours)
                .setSmallIcon(R.drawable.timesheet_notif_icon)
                .setColor(context.resources.getColor(R.color.colorPrimary))
                .addAction(editAction)
                .addAction(sendAsIsAction)
                .setAutoCancel(true) as Notification.Builder
    }
}