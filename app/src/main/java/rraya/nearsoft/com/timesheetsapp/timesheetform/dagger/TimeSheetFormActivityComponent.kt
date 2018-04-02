package rraya.nearsoft.com.timesheetsapp.timesheetform.dagger

import dagger.Subcomponent
import dagger.android.AndroidInjector
import rraya.nearsoft.com.timesheetsapp.dagger.scopes.ActivityScope
import rraya.nearsoft.com.timesheetsapp.splashloginscreen.dagger.TimesheetFormActivityModule
import rraya.nearsoft.com.timesheetsapp.timesheetform.TimeSheetActivity

@ActivityScope
@Subcomponent(modules = [
    TimesheetFormActivityModule::class,
    FragmentBuilder::class
])
interface TimeSheetFormActivityComponent : AndroidInjector<TimeSheetActivity> {

    @Subcomponent.Builder
    abstract class Builder : AndroidInjector.Builder<TimeSheetActivity>()

}
