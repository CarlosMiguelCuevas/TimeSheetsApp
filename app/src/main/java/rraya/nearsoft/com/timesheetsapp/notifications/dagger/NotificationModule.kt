package rraya.nearsoft.com.timesheetsapp.notifications.dagger

import dagger.Module
import dagger.Provides
import rraya.nearsoft.com.timesheetsapp.dagger.scopes.BroadcastReceiverScope
import rraya.nearsoft.com.timesheetsapp.notifications.NotificationHelper

@Module
class NotificationModule {

    @BroadcastReceiverScope
    @Provides
    internal fun providesNotificationHelper(): NotificationHelper {
        return NotificationHelper()
    }

}

