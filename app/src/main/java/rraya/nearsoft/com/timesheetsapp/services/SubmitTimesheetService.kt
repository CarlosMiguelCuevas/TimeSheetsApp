package rraya.nearsoft.com.timesheetsapp.services

import android.annotation.TargetApi
import android.app.Service
import android.content.Intent
import android.os.Build
import android.os.IBinder
import dagger.android.DaggerService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.BiFunction
import io.reactivex.schedulers.Schedulers
import rraya.nearsoft.com.timesheetsapp.R
import rraya.nearsoft.com.timesheetsapp.common.extensions.yearMonthDayFormat
import rraya.nearsoft.com.timesheetsapp.data.IDataRepository
import rraya.nearsoft.com.timesheetsapp.data.models.Day
import rraya.nearsoft.com.timesheetsapp.data.models.TimeSheet
import rraya.nearsoft.com.timesheetsapp.notifications.NotificationHelper
import java.util.*
import javax.inject.Inject

class SubmitTimesheetService : DaggerService() {

    companion object {
        val SERVICE_TYPE = "SERVICETYPE"
        val SHOW_NOTIFICATION = "SHOWNOTIFICATION"
        val SEND_TIMESHEET = "SENDTIMESHEET"
    }

    @Inject lateinit var repository: IDataRepository
    @Inject lateinit var notificationHelper: NotificationHelper
    private var subscriptions = CompositeDisposable()

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        val progressNotification = notificationHelper.buildUploadProgressNotification(this).build()

        startForeground(NotificationHelper.REPEATED_NOTIFICATION_ID, progressNotification)
        submitTimsheet()

        return Service.START_STICKY
    }

    @TargetApi(Build.VERSION_CODES.N)
    private fun submitTimsheet() {
        var subscription = repository.getWeekDaysForWeekStarting(calculateWeekStart().yearMonthDayFormat())
                .subscribeOn(Schedulers.io())
                .zipWith(repository.getClientName(), BiFunction { dayList: List<Day>, clientName: String -> TimeSheet(dayList, clientName) })
                .flatMap { timesheet -> repository.submitTimeSheet(timesheet) }
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSuccess { _ -> stopForeground(Service.STOP_FOREGROUND_DETACH) }
                .doAfterSuccess { _ -> stopSelf() }
                .subscribe(
                        { isSuccessful ->
                            run {
                                if (isSuccessful) {
                                    notificationHelper.notify(this, NotificationHelper.REPEATED_NOTIFICATION_ID, notificationHelper.buildSimpleNotification(this, getString(R.string.simple_notification_success_description)).build())
                                } else {
                                    notificationHelper.notify(this, NotificationHelper.REPEATED_NOTIFICATION_ID, notificationHelper.buildSimpleNotification(this, getString(R.string.simple_notification_error_description)).build())
                                }
                            }
                        },
                        { error -> notificationHelper.notify(this, NotificationHelper.REPEATED_NOTIFICATION_ID, notificationHelper.buildSimpleNotification(this, getString(R.string.simple_notification_error_description)).build()) }
                )

        subscriptions.add(subscription)

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

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onDestroy() {
        subscriptions.clear()
        super.onDestroy()
    }
}