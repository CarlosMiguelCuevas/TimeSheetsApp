package rraya.nearsoft.com.timesheetsapp.notifications

import android.content.Context
import android.content.Intent
import dagger.android.DaggerBroadcastReceiver
import javax.inject.Inject


class BootReceiver : DaggerBroadcastReceiver() {

    @Inject
    lateinit var notificationHelper: NotificationHelper

    override fun onReceive(context: Context, intent: Intent) {
        super.onReceive(context, intent)
        if (intent.action.equals("android.intent.action.BOOT_COMPLETED")) {
            notificationHelper.scheduleRepeatingTimesheetNotification(context)
        }
    }

}