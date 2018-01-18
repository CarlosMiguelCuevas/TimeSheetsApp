package rraya.nearsoft.com.timesheetsapp.dagger

import dagger.Module
import dagger.Provides
import rraya.nearsoft.com.timesheetsapp.notifications.AlarmManagerHelper
import rraya.nearsoft.com.timesheetsapp.notifications.NotificationHelper
import javax.inject.Singleton

@Module
class NotificationModule {

    @Singleton
    @Provides
    internal fun providesNotificationHelper(): NotificationHelper {
        return NotificationHelper()
    }

    @Singleton
    @Provides
    internal fun providesAlarmHelper(): AlarmManagerHelper {
        return AlarmManagerHelper()
    }

}

