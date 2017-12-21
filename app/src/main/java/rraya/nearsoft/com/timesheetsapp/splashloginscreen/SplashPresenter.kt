package rraya.nearsoft.com.timesheetsapp.splashloginscreen

import com.firebase.ui.auth.AuthUI
import com.google.firebase.auth.FirebaseAuth
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import rraya.nearsoft.com.timesheetsapp.common.RxBasePresenter
import rraya.nearsoft.com.timesheetsapp.data.IDataRepository
import java.util.*

class SplashPresenter(private var dataRepository: IDataRepository, private var splashView: SplashViewPresenterContract.View?) : RxBasePresenter(), SplashViewPresenterContract.Presenter {

    companion object {
        private val RC_SIGN_IN: Int = 1
    }

//    private var splashView: SplashViewPresenterContract.View? = null

//    override fun setView(view: SplashViewPresenterContract.View) {
//        splashView = view
//    }

    override fun loginInTimesheets(idToken: String) {
        splashView?.showProgressBar()

        val getTokenSubcription = dataRepository.getTimesheetsTokenFromGoogleToken(idToken)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    splashView?.hideProgressBar()
                    dataRepository.saveTimeSheetTokenIntoPreferences(it)
                    splashView?.onLoginSuccess()
                }, {
                    splashView?.hideProgressBar()
                    splashView?.onLoginError(it)
                })

        subscribe(getTokenSubcription)
    }

    override fun tryLoginApp() {
        splashView?.hideProgressBar()
        if (isTokenStored()) {
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
        //TODO: here we will call the next activity
    }

    override fun checkLoginResult(requestCode: Int): Boolean {
        return requestCode == RC_SIGN_IN
    }

    override fun dropView() {
        splashView = null
    }

    override fun firebaseLoginResponce() {
        val user = FirebaseAuth.getInstance().currentUser
        user?.getIdToken(false)?.addOnCompleteListener {
            if (it.isSuccessful && it.result.token?.isNotEmpty() ?: false) {
                loginInTimesheets(it.result.token!!)
                continueToNextActivity()
            } else {
                splashView?.onLoginError(it.exception ?: Throwable("something happened"))
            }
        }
    }

}