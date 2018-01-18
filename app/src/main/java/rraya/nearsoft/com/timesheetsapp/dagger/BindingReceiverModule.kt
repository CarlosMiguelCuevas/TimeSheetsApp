package rraya.nearsoft.com.timesheetsapp.dagger

import dagger.Module
import dagger.android.ContributesAndroidInjector
import rraya.nearsoft.com.timesheetsapp.notifications.AlarmReceiver
import rraya.nearsoft.com.timesheetsapp.notifications.BootReceiver

@Module
abstract class BindingReceiverModule {

    @ContributesAndroidInjector()
    abstract fun bindAlarmReceiver(): AlarmReceiver

    @ContributesAndroidInjector()
    abstract fun bindBootReceiver(): BootReceiver

}


