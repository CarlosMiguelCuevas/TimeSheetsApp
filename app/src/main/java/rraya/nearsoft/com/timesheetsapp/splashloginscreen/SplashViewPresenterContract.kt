package rraya.nearsoft.com.timesheetsapp.splashloginscreen

import android.content.Intent
import rraya.nearsoft.com.timesheetsapp.common.BaseViewPresenterContract

//TODO: modify this contract as needed
interface SplashViewPresenterContract {

    interface View : BaseViewPresenterContract.View {

        fun onLoginError(error: Throwable)

        fun onLoginSuccess()

        fun startActivityForResult(intent: Intent, requestCode: Int)

    }

    interface Presenter : BaseViewPresenterContract.Presenter {

        fun loginInTimesheets(idToken: String)

        fun checkLoginResult(requestCode: Int): Boolean

        fun tryLoginApp()

        fun forceLoginWithGoogle()

        fun firebaseLoginResponse()

        fun setView(view: SplashViewPresenterContract.View)

        fun dropView()

    }

}