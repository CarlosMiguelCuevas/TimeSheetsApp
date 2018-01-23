package rraya.nearsoft.com.timesheetsapp.notifications

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import rraya.nearsoft.com.timesheetsapp.services.ScheduleTimesheetNotificationService


class BootReceiver : BroadcastReceiver() {


    override fun onReceive(context: Context, intent: Intent) {
        if (intent.action.equals(Intent.ACTION_BOOT_COMPLETED)) {
            //Here the `true` is needed because the app is not in foreground
            ScheduleTimesheetNotificationService.startService(context, true)
        }
    }

}