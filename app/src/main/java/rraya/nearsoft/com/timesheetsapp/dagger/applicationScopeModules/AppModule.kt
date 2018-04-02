package rraya.nearsoft.com.timesheetsapp.dagger.applicationScopeModules

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import rraya.nearsoft.com.timesheetsapp.confirmation.dagger.ConfirmationActivityComponent
import rraya.nearsoft.com.timesheetsapp.splashloginscreen.dagger.SplashActivityComponent
import rraya.nearsoft.com.timesheetsapp.timesheetform.dagger.TimeSheetFormActivityComponent
import rraya.nearsoft.com.timesheetsapp.timesheetnotification.dagger.NotificationComponent
import rraya.nearsoft.com.timesheetsapp.timesheetnotification.dagger.ScheduleServiceComponent
import rraya.nearsoft.com.timesheetsapp.timesheetnotification.dagger.SubmitServiceComponent
import javax.inject.Singleton

//TODO:[new] presentation, add subcomponents here
@Module(subcomponents = [
    SplashActivityComponent::class,
    TimeSheetFormActivityComponent::class,
    ConfirmationActivityComponent::class,
    NotificationComponent::class,
    ScheduleServiceComponent::class,
    SubmitServiceComponent::class
])
class AppModule() {

    @Provides
    @Singleton
    fun provideContext(application: Application): Context {//TODO: presentation, ya no esta application en el costructor, ahora esta en el grafo gracias al builder del component
        return application
    }
}
