package rraya.nearsoft.com.timesheetsapp.notifications

import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.support.v4.app.NotificationCompat
import rraya.nearsoft.com.timesheetsapp.R
import rraya.nearsoft.com.timesheetsapp.timesheetform.TimeSheetActivity


class AlarmReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        //Get notification manager to manage/send notifications
        val notificationHelper = NotificationHelper()


        //Intent to invoke app when click on notification.
        //In this sample, we want to start/launch this sample app when user clicks on notification
        val intentToRepeat = Intent(context, TimeSheetActivity::class.java)
        //set flag to restart/relaunch the app
        intentToRepeat.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)

        //Pending intent to handle launch of Activity in intent above
        val pendingIntent = PendingIntent.getActivity(context, notificationHelper.ALARM_TYPE_RTC, intentToRepeat, PendingIntent.FLAG_UPDATE_CURRENT)

        //Build notification
        val repeatedNotification = buildLocalNotification(context, pendingIntent).build()

        //Send local notification
        notificationHelper.getNotificationManager(context).notify(notificationHelper.ALARM_TYPE_RTC, repeatedNotification)
    }

    fun buildLocalNotification(context: Context, pendingIntent: PendingIntent): NotificationCompat.Builder {

        //TODO Add action to send timesheet as is.
        val sendAsIsIntent = null
        var sendAsIsAction = NotificationCompat.Action.Builder(R.drawable.send_timesheet_icon, "Send", sendAsIsIntent).build()
        var editAction = NotificationCompat.Action.Builder(R.drawable.icon_edit_grey, "Edit", pendingIntent).build()

        return NotificationCompat.Builder(context)
                .setContentIntent(pendingIntent)
                .setContentTitle("Nearsoft timesheets")
                .setSmallIcon(R.drawable.timesheet_notif_icon)
                .addAction(sendAsIsAction)
                .addAction(editAction)
                .setAutoCancel(true) as NotificationCompat.Builder
    }
}