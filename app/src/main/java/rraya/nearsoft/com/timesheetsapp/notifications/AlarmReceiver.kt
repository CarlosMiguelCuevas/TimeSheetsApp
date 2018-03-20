package rraya.nearsoft.com.timesheetsapp.notifications

import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import rraya.nearsoft.com.timesheetsapp.TimeSheetsApp
import rraya.nearsoft.com.timesheetsapp.services.SubmitTimesheetService
import rraya.nearsoft.com.timesheetsapp.timesheetform.TimeSheetActivity
import javax.inject.Inject


class AlarmReceiver : BroadcastReceiver() {

    @Inject
    lateinit var notificationHelper: NotificationHelper

    companion object {
        val ALARM_PENDING_INTENT_ID = 100
        val EXTRA_CLIENT_NAME = "EXTRACLIENTNAME"

        fun getPendingIntentWith(context: Context, clientName: String): PendingIntent {
            val alarmReceiverIntent = Intent(context, AlarmReceiver::class.java)
            alarmReceiverIntent.putExtra(EXTRA_CLIENT_NAME, clientName)
            return PendingIntent.getBroadcast(context, ALARM_PENDING_INTENT_ID, alarmReceiverIntent, PendingIntent.FLAG_UPDATE_CURRENT)
        }
    }

    private fun injectDependencies() {
        TimeSheetsApp.component.NotificationSubComponent().inject(this)
    }

    override fun onReceive(context: Context, intent: Intent) {

        injectDependencies()

        val pendingIntentEditTimesheet = buildEditPendingIntent(context)
        val pendingIntentSubmitTimsheet = buildSendPendingIntent(context)
        val client = intent.getStringExtra(EXTRA_CLIENT_NAME)
        val reminderNotification = notificationHelper.buildEditSendTimeSheetNotification(context, pendingIntentEditTimesheet, pendingIntentSubmitTimsheet, client).build()

        notificationHelper.notify(context, NotificationHelper.REPEATED_NOTIFICATION_ID, reminderNotification)
    }

    private fun buildEditPendingIntent(context: Context): PendingIntent {
        val intentEditTimesheet = Intent(context, TimeSheetActivity::class.java)
        intentEditTimesheet.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        return PendingIntent.getActivity(context, NotificationHelper.EDIT_PENDING_INTENT_ID, intentEditTimesheet, PendingIntent.FLAG_UPDATE_CURRENT)
    }

    private fun buildSendPendingIntent(context: Context): PendingIntent {
        val intentSubmitTimeSheet40Hours = Intent(context, SubmitTimesheetService::class.java)
        return PendingIntent.getService(context, NotificationHelper.SUBMIT_PENDING_INTENT_ID, intentSubmitTimeSheet40Hours, PendingIntent.FLAG_UPDATE_CURRENT)
    }

}