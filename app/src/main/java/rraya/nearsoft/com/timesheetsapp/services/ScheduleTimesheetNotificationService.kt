package rraya.nearsoft.com.timesheetsapp.services

import android.app.IntentService
import android.content.Context
import android.content.Intent
import android.os.Build
import rraya.nearsoft.com.timesheetsapp.R
import rraya.nearsoft.com.timesheetsapp.TimeSheetsApp
import rraya.nearsoft.com.timesheetsapp.data.IDataRepository
import rraya.nearsoft.com.timesheetsapp.notifications.AlarmManagerHelper
import rraya.nearsoft.com.timesheetsapp.notifications.NotificationHelper
import javax.inject.Inject

class ScheduleTimesheetNotificationService : IntentService("notificationThread") {

    @Inject
    lateinit var repository: IDataRepository
    @Inject
    lateinit var alarmHelper: AlarmManagerHelper
    @Inject
    lateinit var notificationHelper: NotificationHelper

    companion object {
        fun startService(context: Context?, startForeground: Boolean = false) {
            val scheduleNotificationService = Intent(context, ScheduleTimesheetNotificationService::class.java)

            if (startForeground && Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                context?.startForegroundService(scheduleNotificationService)
            } else {
                context?.startService(scheduleNotificationService)
            }
        }
    }

    override fun onCreate() {
        super.onCreate()
        injectDependencies()
        startForeground(NotificationHelper.REPEATED_NOTIFICATION_ID, notificationHelper.buildDefaultProgressNotification(this, getString(R.string.notification_message_setting_alarm)).build())
    }

    private fun injectDependencies() {
        TimeSheetsApp.component.NotificationSubComponent().inject(this)
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