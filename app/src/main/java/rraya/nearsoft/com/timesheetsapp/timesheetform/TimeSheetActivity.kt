package rraya.nearsoft.com.timesheetsapp.timesheetform

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import rraya.nearsoft.com.timesheetsapp.R

class TimeSheetActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_time_sheet)

        if (null == savedInstanceState) {
            supportFragmentManager.beginTransaction().replace(R.id.container, TimeSheetView()).commit()
        }
    }
}
