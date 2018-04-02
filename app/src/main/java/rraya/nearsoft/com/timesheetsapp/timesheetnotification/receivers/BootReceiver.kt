package rraya.nearsoft.com.timesheetsapp.timesheetnotification.receivers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import rraya.nearsoft.com.timesheetsapp.timesheetnotification.services.ScheduleTimesheetNotificationService


class BootReceiver : BroadcastReceiver() {


    override fun onReceive(context: Context, intent: Intent) {
        if (Intent.ACTION_BOOT_COMPLETED == intent.action) {
            //Here the `true` is needed because the app is not in foreground
            ScheduleTimesheetNotificationService.startService(context, true)
        }
    }

}