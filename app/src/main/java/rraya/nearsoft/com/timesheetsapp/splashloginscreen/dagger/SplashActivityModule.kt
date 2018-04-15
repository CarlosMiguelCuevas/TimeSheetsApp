package rraya.nearsoft.com.timesheetsapp.splashloginscreen.dagger

import dagger.Module
import dagger.Provides
import rraya.nearsoft.com.timesheetsapp.dagger.scopes.ActivityScope
import rraya.nearsoft.com.timesheetsapp.splashloginscreen.SplashActivity

@Module
class SplashActivityModule() {

    @ActivityScope
    @Provides
    internal fun provideSplashActivity(activity: SplashActivity): SplashActivity {
        return activity
    }

}