package rraya.nearsoft.com.timesheetsapp.notifications

import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import dagger.android.DaggerBroadcastReceiver
import rraya.nearsoft.com.timesheetsapp.services.SubmitTimesheetService
import rraya.nearsoft.com.timesheetsapp.timesheetform.TimeSheetActivity
import javax.inject.Inject


class AlarmReceiver : DaggerBroadcastReceiver() {

    @Inject
    lateinit var notificationHelper: NotificationHelper

    override fun onReceive(context: Context, intent: Intent) {
        super.onReceive(context, intent)

        val intentEditTimesheet = Intent(context, TimeSheetActivity::class.java)
        intentEditTimesheet.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        val pendingIntentEditTimesheet = PendingIntent.getActivity(context, NotificationHelper.EDIT_PENDING_INTENT_ID, intentEditTimesheet, PendingIntent.FLAG_UPDATE_CURRENT)

        val intentSubmitTimeSheet40Hours = Intent(context, SubmitTimesheetService::class.java)
        val pendingIntentSubmitTimsheet = PendingIntent.getService(context, NotificationHelper.SUBMIT_PENDING_INTENT_ID, intentSubmitTimeSheet40Hours, PendingIntent.FLAG_UPDATE_CURRENT)

        val client = intent.getStringExtra(NotificationHelper.EXTRA_CLIENT_NAME)

        val reminderNotification = notificationHelper.buildEditSendTimeSheetNotification(context, pendingIntentEditTimesheet, pendingIntentSubmitTimsheet, client).build()

        notificationHelper.notify(context, NotificationHelper.REPEATED_NOTIFICATION_ID, reminderNotification)
    }

}