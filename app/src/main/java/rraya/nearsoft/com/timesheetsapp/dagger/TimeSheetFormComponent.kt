package rraya.nearsoft.com.timesheetsapp.dagger

import dagger.Subcomponent
import rraya.nearsoft.com.timesheetsapp.dagger.scopes.ActivityViewScope
import rraya.nearsoft.com.timesheetsapp.splashloginscreen.dagger.TimesheetModule
import rraya.nearsoft.com.timesheetsapp.timesheetform.TimeSheetView

@ActivityViewScope
@Subcomponent(modules = [
    TimesheetModule::class
])
interface TimeSheetFormComponent {

    fun inject(target: TimeSheetView)

}
