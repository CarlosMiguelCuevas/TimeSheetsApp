package rraya.nearsoft.com.timesheetsapp.timesheetform

import rraya.nearsoft.com.timesheetsapp.common.BaseViewPresenterContract

/**
 * Created by ccuevas on 12/1/17.
 */
//TODO: modify this contract as needed
interface ViewPresenterContract {

    interface View : BaseViewPresenterContract.View {

        fun onErrorSubmit(error: Throwable)

        fun onErrorLoading(error: Throwable)

        fun onSuccessSubmit()

    }

    interface Presenter : BaseViewPresenterContract.Presenter {

        fun loadTimeSheet()

        fun submitTimeSheet()

    }

}