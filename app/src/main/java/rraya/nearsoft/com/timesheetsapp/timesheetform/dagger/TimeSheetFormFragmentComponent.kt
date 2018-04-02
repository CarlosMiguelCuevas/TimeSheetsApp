package rraya.nearsoft.com.timesheetsapp.timesheetform.dagger

import dagger.Subcomponent
import dagger.android.AndroidInjector
import rraya.nearsoft.com.timesheetsapp.dagger.scopes.FragmentScope
import rraya.nearsoft.com.timesheetsapp.splashloginscreen.dagger.TimesheetFormFragmentModule
import rraya.nearsoft.com.timesheetsapp.timesheetform.TimeSheetView

@FragmentScope
@Subcomponent(modules = [
    TimesheetFormFragmentModule::class
])
interface TimeSheetFormFragmentComponent : AndroidInjector<TimeSheetView> {

    @Subcomponent.Builder
    abstract class Builder : AndroidInjector.Builder<TimeSheetView>()

}
