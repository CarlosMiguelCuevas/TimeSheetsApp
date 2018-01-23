package rraya.nearsoft.com.timesheetsapp.dagger

import dagger.Module
import dagger.android.ContributesAndroidInjector
import rraya.nearsoft.com.timesheetsapp.notifications.AlarmReceiver

@Module
abstract class BindingReceiverModule {

    @ContributesAndroidInjector()
    abstract fun bindAlarmReceiver(): AlarmReceiver

}


