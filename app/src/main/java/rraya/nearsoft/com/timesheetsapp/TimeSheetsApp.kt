package rraya.nearsoft.com.timesheetsapp

import android.app.Application
import rraya.nearsoft.com.timesheetsapp.dagger.AppComponent
import rraya.nearsoft.com.timesheetsapp.dagger.DaggerAppComponent

class TimeSheetsApp : Application() {

    companion object {
        lateinit var component: AppComponent
    }

    override fun onCreate() {
        super.onCreate()
        component = buildComponent()
    }

    private fun buildComponent(): AppComponent {
        return DaggerAppComponent.builder()
                .application(this)
//                .appModule(AppModule(this)) TODO: presentation, asi se ahacie antes, pero con el buileder ya podemos usar el de arriba
//                .dataModule( DataModule())
//                .networkModule(NetworkModule())
//                .giphyRepositoryModule(GiphyRepositoryModule())
                .build()
    }

}