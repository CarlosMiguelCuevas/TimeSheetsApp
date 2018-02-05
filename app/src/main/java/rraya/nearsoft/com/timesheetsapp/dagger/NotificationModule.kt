package rraya.nearsoft.com.timesheetsapp.dagger

import dagger.Module
import dagger.Provides
import rraya.nearsoft.com.timesheetsapp.dagger.scopes.ServiceScope
import rraya.nearsoft.com.timesheetsapp.notifications.AlarmManagerHelper
import rraya.nearsoft.com.timesheetsapp.notifications.NotificationHelper

@Module
class NotificationModule {

    @ServiceScope
    @Provides
    internal fun providesNotificationHelper(): NotificationHelper {
        return NotificationHelper()
    }

    @ServiceScope
    @Provides
    internal fun providesAlarmHelper(): AlarmManagerHelper {
        return AlarmManagerHelper()
    }

}

