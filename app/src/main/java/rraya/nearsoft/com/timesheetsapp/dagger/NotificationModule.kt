package rraya.nearsoft.com.timesheetsapp.dagger

import dagger.Module
import dagger.Provides
import rraya.nearsoft.com.timesheetsapp.notifications.NotificationHelper
import javax.inject.Singleton

@Module
class NotificationModule {

    @Singleton
    @Provides
    internal fun providesNotificationHelper(): NotificationHelper {
        return NotificationHelper()
    }

}

