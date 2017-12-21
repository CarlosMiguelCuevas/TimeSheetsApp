package rraya.nearsoft.com.timesheetsapp.splashloginscreen

import com.firebase.ui.auth.AuthUI
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import rraya.nearsoft.com.timesheetsapp.common.RxBasePresenter
import rraya.nearsoft.com.timesheetsapp.data.IDataRepository
import java.util.*

class SplashPresenter(private var repository: IDataRepository, private var splashView: SplashViewPresenterContract.View?) : RxBasePresenter(), SplashViewPresenterContract.Presenter {

    companion object {
        private val RC_SIGN_IN: Int = 1
    }

    override fun login(idToken: String) {
        splashView?.showProgressBar()

        var getTokenSubcription = repository.getTimesheetsTokenFromGoogleToken(idToken)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    splashView?.hideProgressBar()
//                    userPrefs.setUserToken(it)
                    splashView?.onLoginSuccess()
                }, {
                    splashView?.hideProgressBar()
                    splashView?.onLoginError(it)
                })

        subscribe(getTokenSubcription)
    }

    override fun checkIsLoggedIn() {
        if (splashView != null) {
            if (!splashView!!.isLoggedIn()) {
                launchFirebaseLogin()
                splashView?.hideProgressBar()
            } else {
                continueToNextActivity()
            }
        } else {
            throw Exception("SplashView wasn't set")
        }
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

    override fun onClickedLogin() {
        launchFirebaseLogin()
    }

    private fun continueToNextActivity() {
        splashView?.onLoginSuccess()
    }

    override fun checkLoginResult(requestCode: Int): Boolean {
        return requestCode == RC_SIGN_IN
    }

    override fun dropView() {
        splashView = null
    }

}