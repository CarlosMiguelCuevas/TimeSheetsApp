package rraya.nearsoft.com.timesheetsapp.services

import android.annotation.TargetApi
import android.content.Context
import android.content.Intent
import android.os.Build
import dagger.android.DaggerIntentService
import rraya.nearsoft.com.timesheetsapp.R
import rraya.nearsoft.com.timesheetsapp.data.IDataRepository
import rraya.nearsoft.com.timesheetsapp.notifications.AlarmManagerHelper
import rraya.nearsoft.com.timesheetsapp.notifications.NotificationHelper
import javax.inject.Inject

class ScheduleTimesheetNotificationService : DaggerIntentService("notificationThread") {

    @Inject lateinit var repository: IDataRepository
    @Inject lateinit var alarmHelper: AlarmManagerHelper
    @Inject lateinit var notificationHelper: NotificationHelper

    companion object {
        //TODO: fix compatibility
        @TargetApi(Build.VERSION_CODES.O)
        fun startService(context: Context?, startForeground: Boolean = false) {
            val scheduleNotificationService = Intent(context, ScheduleTimesheetNotificationService::class.java)
            if (startForeground) { //TODO: here we should fix this `if` to add the chek of android O for compatibility
                context?.startForegroundService(scheduleNotificationService)
            } else {
                context?.startService(scheduleNotificationService)
            }
        }
    }

    override fun onCreate() {
        super.onCreate()
        startForeground(NotificationHelper.REPEATED_NOTIFICATION_ID, notificationHelper.buildDefaultProgressNotification(this, getString(R.string.notification_message_setting_alarm)).build())
    }

    override fun onHandleIntent(intent: Intent?) {
        val clientName = repository.getClientName().blockingGet()
        alarmHelper.scheduleTimesheetReminder(this, clientName)
    }

    override fun onDestroy() {
        super.onDestroy()
        stopForeground(true)
    }
}