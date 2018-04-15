package rraya.nearsoft.com.timesheetsapp.dagger.applicationScopeModules

import dagger.Module
import dagger.android.ContributesAndroidInjector
import rraya.nearsoft.com.timesheetsapp.confirmation.ConfirmationActivity
import rraya.nearsoft.com.timesheetsapp.confirmation.dagger.ConfirmationActivityModule
import rraya.nearsoft.com.timesheetsapp.splashloginscreen.SplashActivity
import rraya.nearsoft.com.timesheetsapp.splashloginscreen.dagger.FragmentBuilder
import rraya.nearsoft.com.timesheetsapp.splashloginscreen.dagger.SplashActivityModule
import rraya.nearsoft.com.timesheetsapp.splashloginscreen.dagger.TimesheetFormActivityModule
import rraya.nearsoft.com.timesheetsapp.timesheetform.TimeSheetActivity


@Module
abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules = [SplashActivityModule::class, FragmentBuilder::class])
    internal abstract fun bindSplashActivity(): SplashActivity

    @ContributesAndroidInjector(modules = [TimesheetFormActivityModule::class, rraya.nearsoft.com.timesheetsapp.timesheetform.dagger.FragmentBuilder::class])
    internal abstract fun bindFormActivity(): TimeSheetActivity

    @ContributesAndroidInjector(modules = [ConfirmationActivityModule::class, rraya.nearsoft.com.timesheetsapp.confirmation.dagger.FragmentBuilder::class])
    internal abstract fun bindConfirmActivity(): ConfirmationActivity

}
