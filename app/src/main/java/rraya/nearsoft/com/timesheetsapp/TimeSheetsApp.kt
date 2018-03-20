package rraya.nearsoft.com.timesheetsapp

import android.app.Application
import rraya.nearsoft.com.timesheetsapp.dagger.AppComponent
import rraya.nearsoft.com.timesheetsapp.dagger.AppModule
import rraya.nearsoft.com.timesheetsapp.dagger.DaggerAppComponent

class TimeSheetsApp : Application() {

    companion object {
        lateinit public var component: AppComponent
    }

    override fun onCreate() {
        super.onCreate()
        component = buildComponent()
    }

    private fun buildComponent(): AppComponent {
        return DaggerAppComponent.builder()
                .appModule(AppModule(this))
//                .dataModule( DataModule())
//                .networkModule(NetworkModule())
//                .giphyRepositoryModule(GiphyRepositoryModule())
                .build()
    }

}