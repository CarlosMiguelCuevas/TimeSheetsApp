package rraya.nearsoft.com.timesheetsapp.timesheetform

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.BiFunction
import rraya.nearsoft.com.timesheetsapp.common.RxBasePresenter
import rraya.nearsoft.com.timesheetsapp.common.extensions.calculateWeekStart
import rraya.nearsoft.com.timesheetsapp.common.extensions.yearMonthDayFormat
import rraya.nearsoft.com.timesheetsapp.data.IDataRepository
import rraya.nearsoft.com.timesheetsapp.data.models.Day
import rraya.nearsoft.com.timesheetsapp.data.models.TimeSheet
import java.util.*

class TimeSheetPresenter(private val repo: IDataRepository, private val calendar: Calendar) : RxBasePresenter(), TimesheetsPresenterContract.Presenter {

    private var mTimesheetsView: TimesheetsPresenterContract.View? = null
    private var mTimesheet: TimeSheet? = null

    override fun setView(view: TimesheetsPresenterContract.View) {
        mTimesheetsView = view
    }

    override fun loadTimeSheet() {
        mTimesheetsView?.showProgressBar()
        val subscription = repo.getWeekDaysForWeekStarting(calendar.calculateWeekStart().yearMonthDayFormat())
                .zipWith(repo.getClientName(), BiFunction { dayList: List<Day>, clientName: String -> TimeSheet(dayList, clientName) })
                .doOnSuccess { timesheet -> mTimesheet = timesheet }
                .observeOn(AndroidSchedulers.mainThread())
                .doAfterTerminate { mTimesheetsView?.hideProgressBar() }
                .subscribe(
                        { timesheet -> mTimesheetsView?.showTimeSheetForm(timesheet) },
                        { error -> mTimesheetsView?.onErrorLoading(error) })

        subscribe(subscription)

    }

    override fun isRightNowOnTime(): Boolean {
        //TODO correctly set if it was on time
        return true
    }

    override fun submitTimeSheet() {
        mTimesheetsView?.showProgressBar()
        var subscription = repo.submitTimeSheet(mTimesheet)
                .observeOn(AndroidSchedulers.mainThread())
                .doAfterTerminate { mTimesheetsView?.hideProgressBar() }
                .subscribe({ mTimesheetsView?.onSuccessSubmit() },
                        { mTimesheetsView?.onErrorSubmit(it) })
        subscribe(subscription)
    }

    override fun getUrlForTimesheetEditing(): String {
        //TODO Perhaps build it correctly using a builder?
        return "http://google.com"
    }

    override fun dropView() {
        mTimesheetsView = null
    }

}