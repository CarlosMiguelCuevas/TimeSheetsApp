package rraya.nearsoft.com.timesheetsapp.confirmation

import android.os.Bundle
import dagger.android.AndroidInjection
import dagger.android.support.DaggerAppCompatActivity
import dagger.android.support.HasSupportFragmentInjector
import rraya.nearsoft.com.timesheetsapp.R


class ConfirmationActivity : DaggerAppCompatActivity(), HasSupportFragmentInjector {

    companion object {
        const val DID_SUBMIT_ON_TIME = "DID SUBMIT ON TIME"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_confirmation)

        if (null == savedInstanceState) {
            supportFragmentManager.beginTransaction().replace(R.id.container, ConfirmationView()).commit()
        }
    }

}
