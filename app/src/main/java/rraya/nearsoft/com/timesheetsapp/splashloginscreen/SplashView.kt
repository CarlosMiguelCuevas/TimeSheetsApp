package rraya.nearsoft.com.timesheetsapp.splashloginscreen

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AlertDialog
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.firebase.ui.auth.IdpResponse
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_splash_view.*
import rraya.nearsoft.com.timesheetsapp.R
import rraya.nearsoft.com.timesheetsapp.timesheetform.TimeSheetActivity
import javax.inject.Inject
import javax.inject.Named

class SplashView : Fragment(), SplashViewPresenterContract.View {

    companion object {
        private val TAG = "SplashView"
    }

    @Inject
    lateinit var presenter: SplashViewPresenterContract.Presenter

    @field:[Inject Named("SplashMessage")]
    lateinit var message: String

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        welcome_to_title.setText(message)
        signIn_button.setOnClickListener {
            hideErrorLayout()
            showProgressBar()
            presenter.forceLoginWithGoogle()
        }
    }

    override fun onResume() {
        super.onResume()
        presenter.setView(this)
        presenter.tryLoginApp()

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_splash_view, container, false)
        return view
    }

    override fun onAttach(context: Context?) {
        AndroidSupportInjection.inject(this);//TODO: [new] presentation, con esto inyectamos en el fragment
        super.onAttach(context)
    }

//    TODO:presentation, we don't need this anymore
//    private fun injectDependencies() {
////        Meramente educativo, esto es inutil, Asi se hacai antes:
////        TimeSheetsApp.component.SplashSubComponent(SplashFragmentModule(WELLCOME_MESSAGE)).inject(this)
//        TimeSheetsApp.component.SplashSubComponentBuilder()
//                .splashModule(SplashFragmentModule(WELLCOME_MESSAGE))
//                .build()
//                .inject(this)
//    }

    override fun onLoginError(error: Throwable) {
        Log.e(TAG, "Error logging in to the server")
        context?.let {
            val builder = AlertDialog.Builder(it)
            builder.setMessage(error.message)
                    .setPositiveButton(R.string.accept, { dialog, _ ->
                        dialog.dismiss()
                    })
            val dialog = builder.create()
            dialog.show()
        }
        showErrorLayout()
        hideProgressBar()
    }

    private fun showErrorLayout() {
        login_error_holder.visibility = View.VISIBLE
        splash_info.visibility = View.GONE
    }

    private fun hideErrorLayout() {
        login_error_holder.visibility = View.GONE
        splash_info.visibility = View.VISIBLE
    }

    override fun onLoginSuccess() {
        Log.v(TAG, "Login success!!")
        startActivity(Intent(activity, TimeSheetActivity::class.java))
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        //Check if the result has something to do with firebase login.
        if (presenter.checkLoginResult(requestCode)) {
            if (resultCode == Activity.RESULT_OK) {
                presenter.firebaseLoginResponse()
            } else {
                val response = IdpResponse.fromResultIntent(data)
                Log.v(TAG, response.toString())
                onLoginError(Exception(getText(R.string.error_with_firebase).toString()))
            }
        }
    }

    override fun showProgressBar() {
        progress_bar?.visibility = View.VISIBLE
        signIn_button?.visibility = View.GONE
    }

    override fun hideProgressBar() {
        progress_bar?.visibility = View.GONE
        signIn_button?.visibility = View.VISIBLE
    }

    override fun onStop() {
        super.onStop()
        presenter.unSubscribe()
        presenter.dropView()
    }
}
