package rraya.nearsoft.com.timesheetsapp.splashloginscreen

import rraya.nearsoft.com.timesheetsapp.common.BaseViewPresenterContract

/**
 * Created by ccuevas on 12/1/17.
 */
//TODO: modify this contract as needed
interface ViewPresenterContract {

    interface View : BaseViewPresenterContract.View {

        fun onLoginError(error: Throwable)

        fun onLoginSuccess()

    }

    interface Presenter : BaseViewPresenterContract.Presenter {

        fun Login()

    }

}