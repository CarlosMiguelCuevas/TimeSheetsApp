package rraya.nearsoft.com.timesheetsapp.splashloginscreen

import com.firebase.ui.auth.AuthUI
import com.google.gson.GsonBuilder
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import okhttp3.MediaType
import okhttp3.RequestBody
import rraya.nearsoft.com.timesheetsapp.common.RxBasePresenter
import rraya.nearsoft.com.timesheetsapp.network.TimesheetsApi
import rraya.nearsoft.com.timesheetsapp.network.TokenBody
import java.util.*

/**
 * Created by ccuevas on 12/1/17.
 */
class SplashPresenter(val timesheetsApi: TimesheetsApi) : RxBasePresenter(), SplashViewPresenterContract.Presenter {

    private var splashView : SplashView? = null

    companion object {
        private  val RC_SIGN_IN: Int = 1
    }

    override fun setView(view: SplashView) {
        splashView = view
    }

    override fun login(idToken: String) {
        splashView?.showProgressBar()
        subscriptions.add(
            timesheetsApi.getTSTokenFromGoogleToken(createRequestBody(idToken))
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                splashView?.hideProgressBar()
                if(it.isSuccessful){
                    splashView?.onLoginSuccess()
                }else{
                    //TODO: Improve this behavior?
                    splashView?.onLoginError(RuntimeException("Error del servidor."))
                }
            },{
                splashView?.hideProgressBar()
                splashView?.onLoginError(it)
            })
        )
    }

    private fun createRequestBody(token: String): RequestBody {
        val json = GsonBuilder().excludeFieldsWithoutExposeAnnotation().create().toJson(TokenBody(token))
        return RequestBody.create(MediaType.parse("application/json; charset=utf-8"), json)
    }

    override fun checkIsLoggedIn() {
        if(splashView!=null){
            if(!splashView!!.isLoggedIn()){
                launchFirebaseLogin()
            }else{
                continueToNextActivity()
            }
        }else{
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

    private fun continueToNextActivity() {
        splashView?.onLoginSuccess()
    }
    override fun checkLoginResult(requestCode: Int): Boolean {
        return requestCode == RC_SIGN_IN
    }

}