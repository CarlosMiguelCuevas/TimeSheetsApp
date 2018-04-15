package rraya.nearsoft.com.timesheetsapp

import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication
import rraya.nearsoft.com.timesheetsapp.dagger.AppComponent
import rraya.nearsoft.com.timesheetsapp.dagger.DaggerAppComponent


class TimeSheetsApp : DaggerApplication() { //TODO: inherit form this and get rid of boilerplate

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        var appComponent: AppComponent = DaggerAppComponent
                .builder()
                .application(this)//TODO: [new] presentation, aqui proveemos application context
                .build()
        appComponent.inject(this)//TODO: [new] presentation, con esto inyectamso esta clase y ya teenmos lso dispatchers
        return appComponent
    }
}