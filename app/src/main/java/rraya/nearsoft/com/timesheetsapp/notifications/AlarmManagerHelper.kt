package rraya.nearsoft.com.timesheetsapp.notifications

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import rraya.nearsoft.com.timesheetsapp.common.extensions.setDefaultTimeReminder
import java.util.*

class AlarmManagerHelper {

    fun scheduleTimesheetReminder(context: Context, clientName: String) {

        var alarmManager: AlarmManager? = null
        var alarmReceiverPedingIntent: PendingIntent? = null
        val calendar = Calendar.getInstance()
        calendar.setDefaultTimeReminder()

        alarmReceiverPedingIntent = AlarmReceiver.getPendingIntentWith(context, clientName)
        alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager

        //I used setRepeateing instead of inexact because according to documentation `With setInexactRepeating(), you can't specify a custom interval the way you can with setRepeating()`
        //https://developer.android.com/training/scheduling/alarms.html
        alarmManager!!.setRepeating(AlarmManager.RTC_WAKEUP,
                calendar.timeInMillis, AlarmManager.INTERVAL_DAY * 7, alarmReceiverPedingIntent)
    }

}