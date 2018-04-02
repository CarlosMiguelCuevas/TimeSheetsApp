package rraya.nearsoft.com.timesheetsapp.timesheetform

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import rraya.nearsoft.com.timesheetsapp.R
import javax.inject.Inject


class TimeSheetActivity : AppCompatActivity(), HasSupportFragmentInjector {

    @Inject
    lateinit var fragmentDispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

    override fun supportFragmentInjector(): AndroidInjector<Fragment> {
        return fragmentDispatchingAndroidInjector
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_time_sheet)

        if (null == savedInstanceState) {
            supportFragmentManager.beginTransaction().replace(R.id.container, TimeSheetView()).commit()
        }
    }
}
