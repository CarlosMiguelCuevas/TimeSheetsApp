package rraya.nearsoft.com.timesheetsapp.notifications

import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import rraya.nearsoft.com.timesheetsapp.timesheetform.TimeSheetActivity


class AlarmReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {

        val intentEditTimesheet = Intent(context, TimeSheetActivity::class.java)
        intentEditTimesheet.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        val pendingIntentEditTimesheet = PendingIntent.getActivity(context, NotificationHelper.EDIT_PENDING_INTENT_ID, intentEditTimesheet, PendingIntent.FLAG_UPDATE_CURRENT)

        val notificationHelper = NotificationHelper()

        val client = "generic client" //we have to get this information usig a job or an async

        //TODO: we might have to move this to a job/service because we would need to retrieve the client name form an endpoint, that and the reciever can't be active mor ethan 5 seconds, the cal might take longer
        val reminderNotification = notificationHelper.buildEditSendTimeSheetNotification(context, pendingIntentEditTimesheet, null, client).build()

        notificationHelper.notify(context, NotificationHelper.REPEATED_NOTIFICATION_ID, reminderNotification)
    }

}