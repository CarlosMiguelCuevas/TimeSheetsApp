package rraya.nearsoft.com.timesheetsapp.dagger.applicationScopeModules

import dagger.Module
import dagger.android.ContributesAndroidInjector
import rraya.nearsoft.com.timesheetsapp.dagger.scopes.NotificationScope
import rraya.nearsoft.com.timesheetsapp.timesheetnotification.dagger.NotificationModule
import rraya.nearsoft.com.timesheetsapp.timesheetnotification.services.ScheduleTimesheetNotificationService
import rraya.nearsoft.com.timesheetsapp.timesheetnotification.services.SubmitTimesheetService


@Module
abstract class ServiceBuilder {

    @NotificationScope
    @ContributesAndroidInjector(modules = [NotificationModule::class])
    internal abstract fun bindScheduleService(): ScheduleTimesheetNotificationService

    @NotificationScope
    @ContributesAndroidInjector(modules = [NotificationModule::class])
    internal abstract fun bindSubmitService(): SubmitTimesheetService

}
