package rraya.nearsoft.com.timesheetsapp.splashloginscreen.dagger

import dagger.Module
import dagger.Provides
import rraya.nearsoft.com.timesheetsapp.dagger.scopes.ActivityScope
import rraya.nearsoft.com.timesheetsapp.splashloginscreen.SplashActivity

@Module //TODO: presentation, erese the subcompoent form here too
class SplashActivityModule() {

    //TODO: [new] presentation, this is use to provide the activity if needed, we ar not using this in thsi version
    @ActivityScope
    @Provides
    internal fun provideSplashActivity(activity: SplashActivity): SplashActivity {
        return activity
    }

}