package rraya.nearsoft.com.timesheetsapp.notifications

import android.app.AlarmManager
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import java.util.*

class NotificationHelper {


    private var alarmManagerRTC: AlarmManager? = null
    private var alarmIntentRTC: PendingIntent? = null
    var ALARM_TYPE_RTC = 100


    fun scheduleRepeatingRTCNotification(context: Context) {
        //get calendar instance to be able to select what time notification should be scheduled
        val calendar = Calendar.getInstance()
        calendar.setTimeInMillis(System.currentTimeMillis())
        //Setting time of the day (8am here) when notification will be sent every day (default)
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.WEDNESDAY)
//        calendar.set(Calendar.HOUR_OF_DAY, 10)
//        calendar.set(Calendar.MINUTE, 38)

        //Setting intent to class where Alarm broadcast message will be handled
        val intent = Intent(context, AlarmReceiver::class.java)
        //Setting alarm pending intent
        alarmIntentRTC = PendingIntent.getBroadcast(context, ALARM_TYPE_RTC, intent, PendingIntent.FLAG_UPDATE_CURRENT)

        //getting instance of AlarmManager service
        alarmManagerRTC = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager

        //Setting alarm to wake up device every day for clock time.
        //AlarmManager.RTC_WAKEUP is responsible to wake up device for sure, which may not be good practice all the time.
        // Use this when you know what you're doing.
        //Use RTC when you don't need to wake up device, but want to deliver the notification whenever device is woke-up
        //We'll be using RTC.WAKEUP for demo purpose only
        alarmManagerRTC!!.setInexactRepeating(AlarmManager.RTC_WAKEUP,
                calendar.timeInMillis, AlarmManager.INTERVAL_DAY, alarmIntentRTC)
    }

    fun cancelAlarmRTC() {
        if (alarmManagerRTC != null) {
            alarmManagerRTC!!.cancel(alarmIntentRTC)
        }
    }

    fun getNotificationManager(context: Context): NotificationManager {
        return context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
    }
}