package rraya.nearsoft.com.timesheetsapp.splashloginscreen

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import rraya.nearsoft.com.timesheetsapp.R

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val frag = SplashView()
        supportFragmentManager.beginTransaction().replace(R.id.container, frag).commit()
    }

}
