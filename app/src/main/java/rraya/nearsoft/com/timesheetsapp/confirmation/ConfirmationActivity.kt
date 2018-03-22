package rraya.nearsoft.com.timesheetsapp.confirmation

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import rraya.nearsoft.com.timesheetsapp.R


class ConfirmationActivity : AppCompatActivity() {

    companion object {
        const val DID_SUBMIT_ON_TIME = "DID SUBMIT ON TIME"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_confirmation)

        if (null == savedInstanceState) {
            supportFragmentManager.beginTransaction().replace(R.id.container, ConfirmationView()).commit()
        }
    }

}
