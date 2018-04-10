package rraya.nearsoft.com.timesheetsapp.splashloginscreen

import com.firebase.ui.auth.AuthUI
import com.google.firebase.auth.FirebaseAuth
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import rraya.nearsoft.com.timesheetsapp.common.RxBasePresenter
import rraya.nearsoft.com.timesheetsapp.data.IDataRepository
import rraya.nearsoft.com.timesheetsapp.network.TokenResponse
import java.util.*

class SplashPresenter(private var dataRepository: IDataRepository, private var view: SplashViewPresenterContract.View) : RxBasePresenter(), SplashViewPresenterContract.Presenter {

    companion object {
        private val RC_SIGN_IN: Int = 1
    }

    private var splashView: SplashViewPresenterContract.View? = view


    override fun loginInTimesheets(idToken: String) {
        splashView?.showProgressBar()

        val getTokenSubcription = dataRepository.getTimesheetsTokenFromGoogleToken(idToken)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    splashView?.hideProgressBar()
                    saveDataIntoSharedPrefernces(it)
                    continueToNextActivity()
                }, {
                    splashView?.hideProgressBar()
                    splashView?.onLoginError(it)
                })

        subscribe(getTokenSubcription)
    }

    private fun saveDataIntoSharedPrefernces(tokenResponse: TokenResponse){
        dataRepository.saveTimeSheetTokenIntoPreferences(tokenResponse.token)
        dataRepository.saveTimeSheetUserIdIntoPreferences(tokenResponse.userId)
    }

    override fun tryLoginApp() {
        splashView?.hideProgressBar()
        if (isTokenStored() && isUserIdStored()) {
            continueToNextActivity()
        } else {
            launchFirebaseLogin()
        }
    }

    override fun forceLoginWithGoogle() {
        splashView?.hideProgressBar()
        launchFirebaseLogin()
    }

    private fun isTokenStored(): Boolean {
        val token = dataRepository.getTimeSheetTokenFromSharedPreferences()
        return token.isNotEmpty()
    }

    private fun isUserIdStored(): Boolean {
        val userId = dataRepository.getTimeSheetUserIdFromSharedPreferences()
        return userId > 0
    }

    private fun launchFirebaseLogin() {
        val providers = Arrays.asList(
                AuthUI.IdpConfig.Builder(AuthUI.GOOGLE_PROVIDER).build())

        splashView?.startActivityForResult(
                AuthUI.getInstance()
                        .createSignInIntentBuilder()
                        .setAvailableProviders(providers)
                        .build(),
                RC_SIGN_IN)
    }

    private fun continueToNextActivity() {
        splashView?.onLoginSuccess()
    }

    override fun checkLoginResult(requestCode: Int): Boolean {
        return requestCode == RC_SIGN_IN
    }


    override fun firebaseLoginResponse() {
        val user = FirebaseAuth.getInstance().currentUser
        user?.getIdToken(false)?.addOnCompleteListener {
            if (it.isSuccessful && it.result.token?.isNotEmpty() == true) {
                loginInTimesheets(it.result.token!!)
            } else {
                splashView?.onLoginError(it.exception ?: Throwable("something happened"))
            }
        }
    }

}