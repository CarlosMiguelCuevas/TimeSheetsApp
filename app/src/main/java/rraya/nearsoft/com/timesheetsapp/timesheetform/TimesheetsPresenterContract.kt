package rraya.nearsoft.com.timesheetsapp.timesheetform

import rraya.nearsoft.com.timesheetsapp.common.BaseViewPresenterContract
import rraya.nearsoft.com.timesheetsapp.data.models.TimeSheet

interface TimesheetsPresenterContract {

    interface View : BaseViewPresenterContract.View {

        fun onErrorSubmit(error: Throwable)

        fun onSuccessSubmit()

        fun onErrorLoading(error: Throwable)

        fun showTimeSheetForm(timesheet: TimeSheet)

        fun showSendTimesheetButton()

        fun hideSendTimesheetButton()

    }

    interface Presenter : BaseViewPresenterContract.Presenter {

        fun loadTimeSheet()

        fun submitTimeSheet()

        fun setView(view: View)

        fun dropView()

        fun getUrlForTimesheetEditing(): String

        fun isRightNowOnTime(): Boolean
    }

}