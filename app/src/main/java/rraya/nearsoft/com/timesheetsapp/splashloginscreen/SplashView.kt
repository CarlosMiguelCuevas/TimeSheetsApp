package rraya.nearsoft.com.timesheetsapp.splashloginscreen

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.firebase.ui.auth.IdpResponse
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_splash_view.*
import rraya.nearsoft.com.timesheetsapp.R
import javax.inject.Inject

class SplashView : DaggerFragment(), SplashViewPresenterContract.View {

    @Inject lateinit var presenter: SplashViewPresenterContract.Presenter

    companion object {
        private val TAG = "SplashView"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter.tryLoginApp()
    }

    override fun onResume() {
        super.onResume()
//        presenter.setView(this)
        signIn_button.setOnClickListener {
            hideErrorLayout()
            showProgressBar()
            presenter.forceLoginWithGoogle()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_splash_view, container, false)
    }

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
        Toast.makeText(context, "Login success!!", Toast.LENGTH_LONG).show()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        //Check if the result has something to do with firebase login.
        if (presenter.checkLoginResult(requestCode)) {
            if (resultCode == Activity.RESULT_OK) {
                presenter.firebaseLoginResponce()
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
