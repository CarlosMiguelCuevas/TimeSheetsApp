package rraya.nearsoft.com.timesheetsapp.services

import android.content.Intent
import dagger.android.DaggerIntentService
import rraya.nearsoft.com.timesheetsapp.common.extensions.yearMonthDayFormat
import rraya.nearsoft.com.timesheetsapp.data.IDataRepository
import rraya.nearsoft.com.timesheetsapp.data.models.TimeSheet
import rraya.nearsoft.com.timesheetsapp.notifications.NotificationHelper
import java.util.*
import javax.inject.Inject

class SubmitTimesheetService : DaggerIntentService("SubmitTimesheetService") {

    @Inject lateinit var repository: IDataRepository
    @Inject lateinit var notificationHelper: NotificationHelper

    override fun onHandleIntent(intent: Intent?) {

        val notification = notificationHelper.buildProgressNotification(this)

        startForeground(NotificationHelper.REPEATED_NOTIFICATION_ID, notification.build())
        submitTimsheet()

    }

    private fun submitTimsheet() {
        try {

            var weekdays = repository.getWeekDaysForWeekStarting(calculateWeekStart().yearMonthDayFormat()).blockingGet()
            var client = repository.getClientName().blockingGet()
            var timesheet = TimeSheet(weekdays, client)
            var isSuccessful = repository.submitTimeSheet(timesheet).blockingGet()

            if (isSuccessful) {
                notificationHelper.notify(this, NotificationHelper.REPEATED_NOTIFICATION_ID, notificationHelper.buildSuccessNotification(this).build())
            } else {
                notificationHelper.notify(this, NotificationHelper.REPEATED_NOTIFICATION_ID, notificationHelper.buildErrorNotification(this).build())
            }
            Thread.sleep(5000)
        } catch (error: Throwable) {
            notificationHelper.notify(this, NotificationHelper.REPEATED_NOTIFICATION_ID, notificationHelper.buildErrorNotification(this).build())
        }

        stopForeground(true)
    }

    //TODO: refactor this duplicated code, it is in the timesheet form presenter too
    private fun calculateWeekStart(): Date {
        val calendar = Calendar.getInstance()
        val weekDay = calendar.get(Calendar.DAY_OF_WEEK)
        return if (weekDay > Calendar.MONDAY) {
            calendar.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY)
            calendar.time
        } else {
            calendar.add(Calendar.DAY_OF_MONTH, -7)
            calendar.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY)
            calendar.time
        }
    }

}