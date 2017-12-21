package rraya.nearsoft.com.timesheetsapp.splashloginscreen

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AlertDialog
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.firebase.ui.auth.IdpResponse
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.fragment_splash_view.*
import rraya.nearsoft.com.timesheetsapp.IUserPrefs
import rraya.nearsoft.com.timesheetsapp.R
import rraya.nearsoft.com.timesheetsapp.TimeSheetsApp
import javax.inject.Inject


class SplashView : Fragment(), SplashViewPresenterContract.View {


    @Inject lateinit var presenter: SplashViewPresenterContract.Presenter
    @Inject lateinit var userPrefs: IUserPrefs

    companion object {
        val TAG = "SplashView"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (activity?.application as TimeSheetsApp).getAppComponent()?.inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_splash_view, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.setView(this)
        presenter.checkIsLoggedIn()
        signInBtn.setOnClickListener {
            hideErrorLayout()
            showProgressBar()
            presenter.onClickedLogin()
        }
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
        //Check if the result has something to to with firebase login.
        if(presenter.checkLoginResult(requestCode)){
            if (resultCode == Activity.RESULT_OK) {
                getUserAndRequestLogin()
            } else {
                val response = IdpResponse.fromResultIntent(data)
                Log.v(TAG, response.toString())
                onLoginError(Exception(getText(R.string.error_with_firebase).toString()))
            }
        }
    }

    private fun getUserAndRequestLogin() {
        val user = FirebaseAuth.getInstance().currentUser
        user?.getIdToken(false)?.addOnCompleteListener {
            if(it.isSuccessful && !TextUtils.isEmpty(it.result.token)){
                presenter.login(it.result.token!!)
            }else{
                onLoginError(it.exception!!)
            }
        }
    }

    override fun isLoggedIn(): Boolean {
        return !TextUtils.isEmpty(context?.let { userPrefs.getUserToken() })
    }

    override fun showProgressBar() {
        progress_bar?.visibility = View.VISIBLE
        signInBtn?.visibility = View.GONE
    }

    override fun hideProgressBar() {
        progress_bar?.visibility = View.GONE
        signInBtn?.visibility = View.VISIBLE
    }

    override fun onStop() {
        super.onStop()
        presenter.unSubscribe()
    }
}
