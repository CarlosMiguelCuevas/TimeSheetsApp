package rraya.nearsoft.com.timesheetsapp.timesheetform

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.BiFunction
import io.reactivex.schedulers.Schedulers
import rraya.nearsoft.com.timesheetsapp.common.RxBasePresenter
import rraya.nearsoft.com.timesheetsapp.common.extensions.yearMonthDayFormat
import rraya.nearsoft.com.timesheetsapp.data.IDataRepository
import rraya.nearsoft.com.timesheetsapp.data.models.Day
import rraya.nearsoft.com.timesheetsapp.data.models.TimeSheet
import java.util.*

class TimeSheetPresenter(private val repo: IDataRepository) : RxBasePresenter(), TimesheetsPresenterContract.Presenter {

    private var timesheetsView: TimesheetsPresenterContract.View? = null
    private var days: List<Day>? = null

    override fun setView(view: TimesheetsPresenterContract.View) {
        timesheetsView = view
    }

    override fun loadTimeSheet() {
        val subscription = repo.getWeekDaysForWeekStarting(calculateWeekStart().yearMonthDayFormat())
                .zipWith(repo.getClientName(), BiFunction { dayList: List<Day>, clientName: String -> TimeSheet(dayList, clientName) })
                .subscribe({ timesheet -> timesheetsView?.showTimeSheetForm(timesheet) })

        subscribe(subscription)

    }

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

    override fun isRightNowOnTime(): Boolean {
        //TODO correctly set if it was on time
        return true
    }

    override fun submitTimeSheet() {
        timesheetsView?.showProgressBar()
        var subscription = repo.submitTimeSheet(days)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    timesheetsView?.hideProgressBar()
                    timesheetsView?.onSuccessSubmit()
                }, {
                    timesheetsView?.hideProgressBar()
                    timesheetsView?.onErrorSubmit(it)
                })
        subscribe(subscription)
    }

    override fun getUrlForTimesheetEditing(): String {
        //TODO Perhaps build it correctly using a builder?
        return "http://google.com"
    }

    override fun dropView() {
        timesheetsView = null
    }

}