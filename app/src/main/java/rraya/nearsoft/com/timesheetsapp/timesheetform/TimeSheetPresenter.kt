package rraya.nearsoft.com.timesheetsapp.timesheetform

import rraya.nearsoft.com.timesheetsapp.common.RxBasePresenter
import rraya.nearsoft.com.timesheetsapp.data.IRepository
s
class TimeSheetPresenter(private val repo: IRepository) : RxBasePresenter(), TimesheetsPresenterContract.Presenter {


    private var timesheetsView: TimesheetsPresenterContract.View? = null

    override fun setView(view: TimesheetsPresenterContract.View) {
        timesheetsView = view
    }

    override fun loadTimeSheet() {
        var currentDay = calculateWeekStart()
        var days = repo.getWeekDaysForWeekStarting(currentDay)
        timesheetsView?.showDaysOfWeek(days)
    }

    private fun calculateWeekStart(): String {
        return ""
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun submitTimeSheet() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getUrlForTimesheetEditing(): String {
        TODO("Missing where to get if from") //Perhaps build it correctly using a builder?
        return "http://google.com"
    }

}