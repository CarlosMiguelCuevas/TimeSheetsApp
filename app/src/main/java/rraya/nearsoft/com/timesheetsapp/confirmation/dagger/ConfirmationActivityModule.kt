package rraya.nearsoft.com.timesheetsapp.confirmation.dagger

import dagger.Module
import dagger.Provides
import rraya.nearsoft.com.timesheetsapp.confirmation.ConfirmationActivity
import rraya.nearsoft.com.timesheetsapp.dagger.scopes.ActivityScope

@Module
class ConfirmationActivityModule {

    @ActivityScope
    @Provides
    internal fun provideConfirmationActivity(activity: ConfirmationActivity): ConfirmationActivity {
        return activity
    }

}