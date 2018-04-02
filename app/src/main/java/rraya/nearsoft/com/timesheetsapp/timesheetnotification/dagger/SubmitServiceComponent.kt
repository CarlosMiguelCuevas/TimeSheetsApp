package rraya.nearsoft.com.timesheetsapp.timesheetnotification.dagger

import dagger.Subcomponent
import dagger.android.AndroidInjector
import rraya.nearsoft.com.timesheetsapp.dagger.scopes.NotificationScope
import rraya.nearsoft.com.timesheetsapp.timesheetnotification.services.SubmitTimesheetService

@NotificationScope
@Subcomponent(modules = [
    NotificationModule::class
])
interface SubmitServiceComponent : AndroidInjector<SubmitTimesheetService> {

    @Subcomponent.Builder
    abstract class Builder : AndroidInjector.Builder<SubmitTimesheetService>()

//    fun inject(target: AlarmReceiver)
//    fun inject(target: ScheduleTimesheetNotificationService)
//    fun inject(target: SubmitTimesheetService)

}
