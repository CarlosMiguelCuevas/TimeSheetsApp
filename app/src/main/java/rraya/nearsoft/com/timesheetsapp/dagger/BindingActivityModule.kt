package rraya.nearsoft.com.timesheetsapp.dagger

import dagger.Module
import dagger.android.ContributesAndroidInjector
import rraya.nearsoft.com.timesheetsapp.confirmation.ConfirmationActivity
import rraya.nearsoft.com.timesheetsapp.confirmation.dagger.GiphyRepositoryModule
import rraya.nearsoft.com.timesheetsapp.dagger.scopes.ActivityScope
import rraya.nearsoft.com.timesheetsapp.splashloginscreen.SplashActivity

@Module
abstract class BindingActivityModule {

    @ActivityScope
    @ContributesAndroidInjector(modules = arrayOf(BindingSplashFragmentModule::class))
    abstract fun bindMainActivity(): SplashActivity

    @ActivityScope
    @ContributesAndroidInjector(modules = arrayOf(GiphyRepositoryModule::class))
    abstract fun bindConfrimationActivity(): ConfirmationActivity

}


