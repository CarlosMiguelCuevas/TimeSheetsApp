package rraya.nearsoft.com.timesheetsapp.splashloginscreen

import rraya.nearsoft.com.timesheetsapp.common.BaseViewPresenterContract

/**
 * Created by ccuevas on 12/1/17.
 */
//TODO: modify this contract as needed
interface SplashViewPresenterContract {

    interface View : BaseViewPresenterContract.View {

        fun onLoginError(error: Throwable)

        fun onLoginSuccess()

        fun isLoggedIn(): Boolean

    }

    interface Presenter : BaseViewPresenterContract.Presenter {

        fun login(idToken: String)
        fun checkLoginResult(requestCode: Int): Boolean
        fun setView(view: SplashViewPresenterContract.View)
        fun checkIsLoggedIn()

    }

}