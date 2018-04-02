package rraya.nearsoft.com.timesheetsapp.dagger.applicationScopeModules

import android.app.Activity
import android.app.Service
import android.content.BroadcastReceiver
import dagger.Binds
import dagger.Module
import dagger.android.ActivityKey
import dagger.android.AndroidInjector
import dagger.android.BroadcastReceiverKey
import dagger.android.ServiceKey
import dagger.multibindings.IntoMap
import rraya.nearsoft.com.timesheetsapp.confirmation.ConfirmationActivity
import rraya.nearsoft.com.timesheetsapp.confirmation.dagger.ConfirmationActivityComponent
import rraya.nearsoft.com.timesheetsapp.splashloginscreen.SplashActivity
import rraya.nearsoft.com.timesheetsapp.splashloginscreen.dagger.SplashActivityComponent
import rraya.nearsoft.com.timesheetsapp.timesheetform.TimeSheetActivity
import rraya.nearsoft.com.timesheetsapp.timesheetform.dagger.TimeSheetFormActivityComponent
import rraya.nearsoft.com.timesheetsapp.timesheetnotification.dagger.NotificationComponent
import rraya.nearsoft.com.timesheetsapp.timesheetnotification.dagger.ScheduleServiceComponent
import rraya.nearsoft.com.timesheetsapp.timesheetnotification.dagger.SubmitServiceComponent
import rraya.nearsoft.com.timesheetsapp.timesheetnotification.receivers.AlarmReceiver
import rraya.nearsoft.com.timesheetsapp.timesheetnotification.services.ScheduleTimesheetNotificationService
import rraya.nearsoft.com.timesheetsapp.timesheetnotification.services.SubmitTimesheetService


@Module
abstract class ActivityBuilder {

    //TODO: presentation, es la lista de activityes (services y receivers) que estan en el grafo y su respectivo componetne (subcomponente, el cual esta referencuado en el appp module)
    @Binds
    @IntoMap
    @ActivityKey(SplashActivity::class)//TODO: presentation, notice this is activityKey
    internal abstract fun bindSplashActivity(builder: SplashActivityComponent.Builder): AndroidInjector.Factory<out Activity>

    @Binds
    @IntoMap
    @ActivityKey(TimeSheetActivity::class)
    internal abstract fun bindFormActivity(builder: TimeSheetFormActivityComponent.Builder): AndroidInjector.Factory<out Activity>

    @Binds
    @IntoMap
    @ActivityKey(ConfirmationActivity::class)
    internal abstract fun bindConfirmActivity(builder: ConfirmationActivityComponent.Builder): AndroidInjector.Factory<out Activity>

    @Binds
    @IntoMap
    @BroadcastReceiverKey(AlarmReceiver::class)//TODO: presentation, notice is Broadcast Receiver key
    internal abstract fun bindAlarmReceiver(builder: NotificationComponent.Builder): AndroidInjector.Factory<out BroadcastReceiver>

    @Binds
    @IntoMap
    @ServiceKey(ScheduleTimesheetNotificationService::class)//TODO: presentation, notice is Service Key
    internal abstract fun bindScheduleService(builder: ScheduleServiceComponent.Builder): AndroidInjector.Factory<out Service>

    @Binds
    @IntoMap
    @ServiceKey(SubmitTimesheetService::class)
    internal abstract fun bindSubmitService(builder: SubmitServiceComponent.Builder): AndroidInjector.Factory<out Service>

}
