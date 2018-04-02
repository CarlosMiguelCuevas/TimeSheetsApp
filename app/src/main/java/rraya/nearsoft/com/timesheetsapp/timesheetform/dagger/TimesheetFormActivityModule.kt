package rraya.nearsoft.com.timesheetsapp.splashloginscreen.dagger

import dagger.Module
import dagger.Provides
import rraya.nearsoft.com.timesheetsapp.dagger.scopes.ActivityScope
import rraya.nearsoft.com.timesheetsapp.timesheetform.TimeSheetActivity
import rraya.nearsoft.com.timesheetsapp.timesheetform.dagger.TimeSheetFormFragmentComponent

@Module(subcomponents = [TimeSheetFormFragmentComponent::class])
class TimesheetFormActivityModule {

    @ActivityScope
    @Provides
    internal fun provideTimeSheetActivity(activity: TimeSheetActivity): TimeSheetActivity {
        return activity
    }
}