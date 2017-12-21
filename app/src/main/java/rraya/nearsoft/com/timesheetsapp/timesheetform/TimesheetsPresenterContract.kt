package rraya.nearsoft.com.timesheetsapp.timesheetform

import rraya.nearsoft.com.timesheetsapp.common.BaseViewPresenterContract
import rraya.nearsoft.com.timesheetsapp.data.models.Day

/**
 * Created by ccuevas on 12/1/17.
 */
//TODO: modify this contract as needed
interface TimesheetsPresenterContract {

    interface View : BaseViewPresenterContract.View {

        fun onErrorSubmit(error: Throwable)

        fun onErrorLoading(error: Throwable)

        fun onSuccessSubmit()

        fun showSendTimesheetButton()

        fun hideSendTimesheetButton()

        fun showDaysOfWeek(days: List<Day>)

    }

    interface Presenter : BaseViewPresenterContract.Presenter {

        fun loadTimeSheet()

        fun submitTimeSheet()

        fun setView(view: View)

        fun getUrlForTimesheetEditing(): String
    }

}