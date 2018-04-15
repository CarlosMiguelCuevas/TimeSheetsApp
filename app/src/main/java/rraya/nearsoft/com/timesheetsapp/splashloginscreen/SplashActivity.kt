package rraya.nearsoft.com.timesheetsapp.splashloginscreen

import android.os.Bundle
import dagger.android.support.DaggerAppCompatActivity
import rraya.nearsoft.com.timesheetsapp.R


class SplashActivity : DaggerAppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (null == savedInstanceState) {
            val frag = SplashView()
            supportFragmentManager.beginTransaction().replace(R.id.container, frag).commit()
        }

    }

}
