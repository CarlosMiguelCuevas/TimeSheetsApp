package rraya.nearsoft.com.timesheetsapp.dagger

import dagger.Module
import dagger.Provides
import rraya.nearsoft.com.timesheetsapp.dagger.scopes.ServiceReceiverScope
import rraya.nearsoft.com.timesheetsapp.notifications.AlarmManagerHelper
import rraya.nearsoft.com.timesheetsapp.notifications.NotificationHelper

@Module
class NotificationModule {

    @ServiceReceiverScope
    @Provides
    internal fun providesNotificationHelper(): NotificationHelper {
        return NotificationHelper()
    }

    @ServiceReceiverScope
    @Provides
    internal fun providesAlarmHelper(): AlarmManagerHelper {
        return AlarmManagerHelper()
    }

}

