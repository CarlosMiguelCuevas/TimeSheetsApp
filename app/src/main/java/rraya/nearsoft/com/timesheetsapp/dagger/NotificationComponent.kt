package rraya.nearsoft.com.timesheetsapp.dagger

import dagger.Subcomponent
import rraya.nearsoft.com.timesheetsapp.dagger.scopes.NotificationScope
import rraya.nearsoft.com.timesheetsapp.notifications.AlarmReceiver
import rraya.nearsoft.com.timesheetsapp.services.ScheduleTimesheetNotificationService
import rraya.nearsoft.com.timesheetsapp.services.SubmitTimesheetService

@NotificationScope
@Subcomponent(modules = [
    NotificationModule::class
])
interface NotificationComponent {

    fun inject(target: AlarmReceiver)
    fun inject(target: ScheduleTimesheetNotificationService)
    fun inject(target: SubmitTimesheetService)

}
