package rraya.nearsoft.com.timesheetsapp.timesheetform

import android.os.Bundle
import dagger.android.support.DaggerAppCompatActivity
import dagger.android.support.HasSupportFragmentInjector
import rraya.nearsoft.com.timesheetsapp.R


class TimeSheetActivity : DaggerAppCompatActivity(), HasSupportFragmentInjector {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_time_sheet)

        if (null == savedInstanceState) {
            supportFragmentManager.beginTransaction().replace(R.id.container, TimeSheetView()).commit()
        }
    }
}
