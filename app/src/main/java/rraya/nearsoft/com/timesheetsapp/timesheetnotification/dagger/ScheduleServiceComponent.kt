package rraya.nearsoft.com.timesheetsapp.timesheetnotification.dagger

import dagger.Subcomponent
import dagger.android.AndroidInjector
import rraya.nearsoft.com.timesheetsapp.dagger.scopes.NotificationScope
import rraya.nearsoft.com.timesheetsapp.timesheetnotification.services.ScheduleTimesheetNotificationService

@NotificationScope
@Subcomponent(modules = [
    NotificationModule::class
])
interface ScheduleServiceComponent : AndroidInjector<ScheduleTimesheetNotificationService> {//TODO: [new] presentation, se cree a un subcomponente por cada servuce y cada receiber

    @Subcomponent.Builder
    abstract class Builder : AndroidInjector.Builder<ScheduleTimesheetNotificationService>()

//    fun inject(target: AlarmReceiver)
//    fun inject(target: ScheduleTimesheetNotificationService)
//    fun inject(target: SubmitTimesheetService)

}
