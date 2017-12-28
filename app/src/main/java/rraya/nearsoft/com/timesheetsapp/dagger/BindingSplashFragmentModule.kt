package rraya.nearsoft.com.timesheetsapp.dagger

import dagger.Module
import dagger.android.ContributesAndroidInjector
import rraya.nearsoft.com.timesheetsapp.dagger.scopes.FragmentScope
import rraya.nearsoft.com.timesheetsapp.splashloginscreen.SplashView
import rraya.nearsoft.com.timesheetsapp.splashloginscreen.dagger.SplashModule
import rraya.nearsoft.com.timesheetsapp.splashloginscreen.dagger.TimesheetModule
import rraya.nearsoft.com.timesheetsapp.timesheetform.TimeSheetView

@Module
abstract class BindingSplashFragmentModule {

    @FragmentScope
    @ContributesAndroidInjector(modules = [(SplashModule::class)])
    abstract fun bindSplashFragment(): SplashView

    @FragmentScope
    @ContributesAndroidInjector(modules = [(TimesheetModule::class)])
    abstract fun bindTimesheetFragment(): TimeSheetView
}


