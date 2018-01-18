package rraya.nearsoft.com.timesheetsapp.services

import android.content.Intent
import dagger.android.DaggerIntentService
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.BiFunction
import rraya.nearsoft.com.timesheetsapp.common.extensions.yearMonthDayFormat
import rraya.nearsoft.com.timesheetsapp.data.IDataRepository
import rraya.nearsoft.com.timesheetsapp.data.models.Day
import rraya.nearsoft.com.timesheetsapp.data.models.TimeSheet
import rraya.nearsoft.com.timesheetsapp.notifications.NotificationHelper
import java.util.*
import javax.inject.Inject

class SubmitTimesheetService : DaggerIntentService("SubmitTimesheetService") {

    @Inject lateinit var repository: IDataRepository
    @Inject lateinit var notificationHelper: NotificationHelper
    private var subscriptions = CompositeDisposable()

    override fun onHandleIntent(intent: Intent?) {

        var subscription = repository.getWeekDaysForWeekStarting(calculateWeekStart().yearMonthDayFormat())
                .zipWith(repository.getClientName(), BiFunction { dayList: List<Day>, clientName: String -> TimeSheet(dayList, clientName) })
                .flatMap { timesheet -> repository.submitTimeSheet(timesheet) }
                .subscribe({ isSuccessful ->
                    run {
                        if (isSuccessful) {
                            notificationHelper.buildSuccesNotification()
                        } else {
                            notificationHelper.buildErrorNotification()
                        }
                    }
                },
                        { error -> notificationHelper.buildErrorNotification() })

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

    override fun onDestroy() {
        subscriptions.clear()
        super.onDestroy()
    }
}