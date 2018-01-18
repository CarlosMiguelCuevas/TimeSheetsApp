package rraya.nearsoft.com.timesheetsapp.notifications

import android.content.Context
import android.content.Intent
import dagger.android.DaggerBroadcastReceiver
import javax.inject.Inject


class BootReceiver : DaggerBroadcastReceiver() {

    @Inject
    lateinit var alarmHelper: AlarmManagerHelper

    override fun onReceive(context: Context, intent: Intent) {
        super.onReceive(context, intent)
        if (intent.action.equals("android.intent.action.BOOT_COMPLETED")) {
            //TODO: change this, this should be in a job because we need to retrive the client name
            alarmHelper.scheduleTimesheetReminder(context, "name provided for boot receiver")
        }
    }

}