package rraya.nearsoft.com.timesheetsapp.timesheetform.dagger

import dagger.Module
import dagger.android.ContributesAndroidInjector
import rraya.nearsoft.com.timesheetsapp.dagger.scopes.FragmentScope
import rraya.nearsoft.com.timesheetsapp.splashloginscreen.dagger.TimesheetFormFragmentModule
import rraya.nearsoft.com.timesheetsapp.timesheetform.TimeSheetView

@Module
abstract class FragmentBuilder {

    @FragmentScope
    @ContributesAndroidInjector(modules = [TimesheetFormFragmentModule::class])
    internal abstract fun bindTimeSheetFormFragment(): TimeSheetView

}
