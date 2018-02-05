package rraya.nearsoft.com.timesheetsapp.services

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
import rraya.nearsoft.com.timesheetsapp.common.extensions.calculateWeekStart
import rraya.nearsoft.com.timesheetsapp.common.extensions.yearMonthDayFormat
import rraya.nearsoft.com.timesheetsapp.data.IDataRepository
import rraya.nearsoft.com.timesheetsapp.data.models.Client
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

    @Inject
    lateinit var repository: IDataRepository
    @Inject
    lateinit var notificationHelper: NotificationHelper
    private var calendar: Calendar = Calendar.getInstance()

    private var subscriptions = CompositeDisposable()

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        val progressNotification = notificationHelper.buildUploadProgressNotification(this).build()

        startForeground(NotificationHelper.REPEATED_NOTIFICATION_ID, progressNotification)
        submitTimsheet()

        return Service.START_STICKY
    }

    private fun submitTimsheet() {
        var subscription = repository.getWeekDaysForWeekStarting(calendar.calculateWeekStart().yearMonthDayFormat())
                .subscribeOn(Schedulers.io())
                .zipWith(repository.getClientName(), BiFunction { dayList: List<Day>, clientName: String -> TimeSheet(dayList, listOf(Client(clientName))) })
                .flatMap { timesheet -> repository.submitTimeSheet(timesheet) }
                .observeOn(AndroidSchedulers.mainThread())
                .doAfterSuccess { _ -> stopSelf() }
                .subscribe(
                        { isSuccessful -> handleSubmitResponse(isSuccessful) },
                        { error -> handleSubmitResponse(false) }
                )

        subscriptions.add(subscription)

    }

    private fun handleSubmitResponse(isSuccessful: Boolean) {

        var notificationId = NotificationHelper.REPEATED_NOTIFICATION_ID

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            stopForeground(Service.STOP_FOREGROUND_DETACH)
        } else {
            stopForeground(false)
            notificationId += 1
        }

        if (isSuccessful) {
            notificationHelper.notify(this, notificationId, notificationHelper.buildSimpleNotification(this, getString(R.string.simple_notification_success_description)).build())
        } else {
            notificationHelper.notify(this, notificationId, notificationHelper.buildSimpleNotification(this, getString(R.string.simple_notification_error_description)).build())
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