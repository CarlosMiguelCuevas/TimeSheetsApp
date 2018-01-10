package rraya.nearsoft.com.timesheetsapp.dagger

import dagger.Module
import dagger.android.ContributesAndroidInjector
import rraya.nearsoft.com.timesheetsapp.dagger.scopes.BroadcastReceiverScope
import rraya.nearsoft.com.timesheetsapp.notifications.AlarmReceiver
import rraya.nearsoft.com.timesheetsapp.notifications.BootReceiver
import rraya.nearsoft.com.timesheetsapp.notifications.dagger.NotificationModule

@Module
abstract class BindingReceiverModule {

    @BroadcastReceiverScope
    @ContributesAndroidInjector(modules = arrayOf(NotificationModule::class))
    abstract fun bindAlarmReceiver(): AlarmReceiver

    @BroadcastReceiverScope
    @ContributesAndroidInjector(modules = arrayOf(NotificationModule::class))
    abstract fun bindBootReceiver(): BootReceiver

}


