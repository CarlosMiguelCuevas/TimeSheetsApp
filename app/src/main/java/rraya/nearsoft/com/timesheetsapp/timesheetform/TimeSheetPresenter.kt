package rraya.nearsoft.com.timesheetsapp.timesheetform

import rraya.nearsoft.com.timesheetsapp.common.RxBasePresenter
import rraya.nearsoft.com.timesheetsapp.data.IDataRepository
import java.text.SimpleDateFormat
import java.util.*

class TimeSheetPresenter(private val repo: IDataRepository) : RxBasePresenter(), TimesheetsPresenterContract.Presenter {


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
        var weekStart: Date
        val calendar = Calendar.getInstance()
        val weekDay = calendar.get(Calendar.DAY_OF_WEEK)
        if(weekDay > Calendar.MONDAY){
            calendar.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY)
            weekStart = calendar.time
        }else{
            calendar.add(Calendar.DAY_OF_MONTH, -7)
            calendar.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY)
            weekStart = calendar.time
        }

        val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        return simpleDateFormat.format(weekStart)
    }

    override fun submitTimeSheet() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getUrlForTimesheetEditing(): String {
        //TODO Perhaps build it correctly using a builder?
        return "http://google.com"
    }

}