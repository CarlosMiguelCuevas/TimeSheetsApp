package rraya.nearsoft.com.timesheetsapp.dagger

import javax.inject.Singleton

import dagger.Component
import rraya.nearsoft.com.timesheetsapp.confirmation.ConfirmationActivity
import rraya.nearsoft.com.timesheetsapp.data.GifsRepository
import rraya.nearsoft.com.timesheetsapp.data.Repository
import rraya.nearsoft.com.timesheetsapp.splashloginscreen.SplashView
import rraya.nearsoft.com.timesheetsapp.timesheetform.TimeSheetView

/**
 * Created by oaguilar on 12/5/17.
 */

@Singleton
@Component(modules = [AppModule::class, PresenterModule::class, NetworkModule::class, UserPrefsModule::class, RepositoryModule::class, GiphyRepositoryModule::class])
interface AppComponent {

    fun inject(target: SplashView)
    fun inject(target: TimeSheetView)
    fun inject(target: Repository)
    fun inject(target: GifsRepository)
    fun inject(target: ConfirmationActivity)
}
