package rraya.nearsoft.com.timesheetsapp.dagger.applicationScopeModules

import dagger.Module
import dagger.android.ContributesAndroidInjector
import rraya.nearsoft.com.timesheetsapp.dagger.scopes.NotificationScope
import rraya.nearsoft.com.timesheetsapp.timesheetnotification.dagger.NotificationModule
import rraya.nearsoft.com.timesheetsapp.timesheetnotification.receivers.AlarmReceiver


@Module
abstract class ReceiverBuilder {

    @NotificationScope
    @ContributesAndroidInjector(modules = [NotificationModule::class])
    internal abstract fun bindAlarmReceiver(): AlarmReceiver

}
