package rraya.nearsoft.com.timesheetsapp

import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication
import rraya.nearsoft.com.timesheetsapp.dagger.AppComponent
import rraya.nearsoft.com.timesheetsapp.dagger.DaggerAppComponent

class TimeSheetsApp : DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        val appComponent: AppComponent = DaggerAppComponent.builder().application(this).build()
        appComponent.inject(this)
        return appComponent
    }
}