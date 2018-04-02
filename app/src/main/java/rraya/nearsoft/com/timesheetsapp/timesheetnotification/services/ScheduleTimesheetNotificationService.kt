package rraya.nearsoft.com.timesheetsapp.timesheetnotification.services

import android.app.IntentService
import android.content.Context
import android.content.Intent
import android.os.Build
import dagger.android.AndroidInjection
import rraya.nearsoft.com.timesheetsapp.R
import rraya.nearsoft.com.timesheetsapp.data.IDataRepository
import rraya.nearsoft.com.timesheetsapp.timesheetnotification.helpers.AlarmManagerHelper
import rraya.nearsoft.com.timesheetsapp.timesheetnotification.helpers.NotificationHelper
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
        AndroidInjection.inject(this)//TODO: [new] presentation, con esto inyectamso el servicio, si te fijas no tenemos que implementar intrefaces por que este es lo que se inyecta, no tiene comeponentes inetrnos como el activity, que se inyecten (interfaces), si no tubieramso fragemnts, lso activities se verian asi como este, application ya imepment als inetrfaces necesarias
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