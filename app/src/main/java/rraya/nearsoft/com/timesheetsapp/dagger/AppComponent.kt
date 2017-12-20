package rraya.nearsoft.com.timesheetsapp.dagger

import javax.inject.Singleton

import dagger.Component
import rraya.nearsoft.com.timesheetsapp.data.Repository
import rraya.nearsoft.com.timesheetsapp.splashloginscreen.SplashView

@Singleton
@Component(modules = [AppModule::class, PresenterModule::class, NetworkModule::class, UserPrefsModule::class, RepositoryModule::class])
interface AppComponent {

    fun inject(target: SplashView)
    fun inject(target: Repository)
}
