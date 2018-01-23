package rraya.nearsoft.com.timesheetsapp.dagger

import dagger.Module
import dagger.android.ContributesAndroidInjector
import rraya.nearsoft.com.timesheetsapp.services.ScheduleTimesheetNotificationService
import rraya.nearsoft.com.timesheetsapp.services.SubmitTimesheetService

@Module
abstract class BindingServiceModule {

    @ContributesAndroidInjector()
    abstract fun bindSubmitService(): SubmitTimesheetService

    @ContributesAndroidInjector()
    abstract fun bindScheduleService(): ScheduleTimesheetNotificationService

}


