package rraya.nearsoft.com.timesheetsapp.notifications

import android.app.AlarmManager
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import java.util.*

class NotificationHelper {


    private var alarmManagerRTC: AlarmManager? = null
    private var alarmPedingIntentRTC: PendingIntent? = null
    var ALARM_TYPE_RTC = 100


    fun scheduleRepeatingRTCNotification(context: Context) {
        val calendar = Calendar.getInstance()
        calendar.setTimeInMillis(System.currentTimeMillis())
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.FRIDAY)
        calendar.set(Calendar.HOUR_OF_DAY, 12)

        val alarmReceiverIntent = Intent(context, AlarmReceiver::class.java)
        alarmPedingIntentRTC = PendingIntent.getBroadcast(context, ALARM_TYPE_RTC, alarmReceiverIntent, PendingIntent.FLAG_UPDATE_CURRENT)

        alarmManagerRTC = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        alarmManagerRTC!!.setInexactRepeating(AlarmManager.RTC_WAKEUP,
                calendar.timeInMillis, AlarmManager.INTERVAL_DAY, alarmPedingIntentRTC)
    }

    fun cancelAlarmRTC() {
        if (alarmManagerRTC != null) {
            alarmManagerRTC!!.cancel(alarmPedingIntentRTC)
        }
    }

    fun getNotificationManager(context: Context): NotificationManager {
        return context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
    }
}