package rraya.nearsoft.com.timesheetsapp.timesheetnotification.dagger

import dagger.Subcomponent
import dagger.android.AndroidInjector
import rraya.nearsoft.com.timesheetsapp.dagger.scopes.NotificationScope
import rraya.nearsoft.com.timesheetsapp.timesheetnotification.receivers.AlarmReceiver

@NotificationScope
@Subcomponent(modules = [
    NotificationModule::class//TODO: [new] presentation, provee lo que los services y receiver necesitan, no teine lista de fragments ni provee un method para obtener este compoennte, ay esta en el grafo
])
interface NotificationComponent : AndroidInjector<AlarmReceiver> {

    @Subcomponent.Builder
    abstract class Builder : AndroidInjector.Builder<AlarmReceiver>()//TODO: [new] presentation, gracias a esto esta en el grafo

//    fun inject(target: AlarmReceiver)
//    fun inject(target: ScheduleTimesheetNotificationService)
//    fun inject(target: SubmitTimesheetService)

}
