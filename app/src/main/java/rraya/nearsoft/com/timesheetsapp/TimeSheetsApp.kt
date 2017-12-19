package rraya.nearsoft.com.timesheetsapp

import android.app.Application
import rraya.nearsoft.com.timesheetsapp.dagger.AppComponent
import rraya.nearsoft.com.timesheetsapp.dagger.AppModule
import rraya.nearsoft.com.timesheetsapp.dagger.DaggerAppComponent

/**
 * Created by ccuevas on 12/1/17.
 */
class TimeSheetsApp : Application() {

    private var appComponent: AppComponent? = null

    override fun onCreate() {
        super.onCreate()
        appComponent = initDagger(this)
    }

    fun getAppComponent(): AppComponent? {
        return appComponent
    }

    @Suppress("DEPRECATION")
    private fun initDagger(application: TimeSheetsApp): AppComponent {
        return DaggerAppComponent.builder()
                .appModule(AppModule(application))
                .build()
    }
}