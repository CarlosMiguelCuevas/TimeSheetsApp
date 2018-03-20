package rraya.nearsoft.com.timesheetsapp.dagger

import dagger.Module
import dagger.Provides
import rraya.nearsoft.com.timesheetsapp.dagger.scopes.NotificationScope
import rraya.nearsoft.com.timesheetsapp.notifications.AlarmManagerHelper
import rraya.nearsoft.com.timesheetsapp.notifications.NotificationHelper

@Module
class NotificationModule {

    @NotificationScope
    @Provides
    internal fun providesNotificationHelper(): NotificationHelper {
        return NotificationHelper()
    }

    @NotificationScope
    @Provides
    internal fun providesAlarmHelper(): AlarmManagerHelper {
        return AlarmManagerHelper()
    }

}

