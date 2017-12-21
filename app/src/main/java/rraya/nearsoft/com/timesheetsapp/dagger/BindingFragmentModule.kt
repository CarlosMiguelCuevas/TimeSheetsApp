package rraya.nearsoft.com.timesheetsapp.dagger

import dagger.Module
import dagger.android.ContributesAndroidInjector
import rraya.nearsoft.com.timesheetsapp.dagger.scopes.FragmentScope
import rraya.nearsoft.com.timesheetsapp.splashloginscreen.SplashView
import rraya.nearsoft.com.timesheetsapp.splashloginscreen.dagger.SplashModule

@Module
abstract class BindingFragmentModule {

    @FragmentScope
    @ContributesAndroidInjector(modules = arrayOf(SplashModule::class))
    abstract fun bindSplashFragment(): SplashView
}


