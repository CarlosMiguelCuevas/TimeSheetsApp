package rraya.nearsoft.com.timesheetsapp.dagger

import dagger.Module
import dagger.android.ContributesAndroidInjector
import rraya.nearsoft.com.timesheetsapp.dagger.scopes.ServiceReceiverScope
import rraya.nearsoft.com.timesheetsapp.notifications.AlarmReceiver
import rraya.nearsoft.com.timesheetsapp.services.ScheduleTimesheetNotificationService
import rraya.nearsoft.com.timesheetsapp.services.SubmitTimesheetService

@Module
abstract class BindingServiceReceiverModule {

    @ServiceReceiverScope
    @ContributesAndroidInjector(modules = arrayOf(NotificationModule::class))
    abstract fun bindSubmitService(): SubmitTimesheetService

    @ServiceReceiverScope
    @ContributesAndroidInjector(modules = arrayOf(NotificationModule::class))
    abstract fun bindScheduleService(): ScheduleTimesheetNotificationService

    @ServiceReceiverScope
    @ContributesAndroidInjector(modules = arrayOf(NotificationModule::class))
    abstract fun bindAlarmReceiver(): AlarmReceiver

}


