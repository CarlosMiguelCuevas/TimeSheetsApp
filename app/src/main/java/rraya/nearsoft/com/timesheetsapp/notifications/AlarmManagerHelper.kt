package rraya.nearsoft.com.timesheetsapp.notifications

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import java.util.*

class AlarmManagerHelper {

    companion object {
        val ALARM_PENDING_INTENT_ID = 100
        val EXTRA_CLIENT_NAME = "EXTRACLIENTNAME"
    }

    fun scheduleTimesheetReminder(context: Context, clientName: String) {

        var alarmManager: AlarmManager? = null
        var alarmReceiverPedingIntent: PendingIntent? = null

        val calendar = Calendar.getInstance()
        calendar.setTimeInMillis(System.currentTimeMillis())
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.FRIDAY)
        calendar.set(Calendar.HOUR_OF_DAY, 12)

        val alarmReceiverIntent = Intent(context, AlarmReceiver::class.java)
        alarmReceiverIntent.putExtra(EXTRA_CLIENT_NAME, clientName)
        alarmReceiverPedingIntent = PendingIntent.getBroadcast(context, ALARM_PENDING_INTENT_ID, alarmReceiverIntent, PendingIntent.FLAG_UPDATE_CURRENT)

        alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager

        //I used setRepeateing instead of inexact because according to documentation `With setInexactRepeating(), you can't specify a custom interval the way you can with setRepeating()`
        //https://developer.android.com/training/scheduling/alarms.html
        alarmManager!!.setRepeating(AlarmManager.RTC_WAKEUP,
                calendar.timeInMillis, AlarmManager.INTERVAL_DAY * 7, alarmReceiverPedingIntent)
    }

}