package rraya.nearsoft.com.timesheetsapp.confirmation

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import rraya.nearsoft.com.timesheetsapp.R
import javax.inject.Inject


class ConfirmationActivity : AppCompatActivity(), HasSupportFragmentInjector {

    @Inject
    lateinit var fragmentDispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

    override fun supportFragmentInjector(): AndroidInjector<Fragment> {
        return fragmentDispatchingAndroidInjector
    }

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
