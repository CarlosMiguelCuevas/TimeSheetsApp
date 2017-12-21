package rraya.nearsoft.com.timesheetsapp.dagger

import dagger.Module
import dagger.android.ContributesAndroidInjector
import rraya.nearsoft.com.timesheetsapp.dagger.scopes.ActivityScope
import rraya.nearsoft.com.timesheetsapp.splashloginscreen.SplashActivity

@Module
abstract class BindingActivityModule {

    @ActivityScope
    @ContributesAndroidInjector(modules = arrayOf(BindingFragmentModule::class))
    abstract fun bindMainActivity(): SplashActivity
}


