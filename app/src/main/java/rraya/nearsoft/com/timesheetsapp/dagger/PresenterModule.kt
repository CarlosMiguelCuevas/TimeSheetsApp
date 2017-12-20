package rraya.nearsoft.com.timesheetsapp.dagger

import dagger.Module
import dagger.Provides
import rraya.nearsoft.com.timesheetsapp.IUserPrefs
import rraya.nearsoft.com.timesheetsapp.data.IRepository
import rraya.nearsoft.com.timesheetsapp.splashloginscreen.SplashPresenter

/**
 * Created by oaguilar on 12/5/17.
 */

@Module
class PresenterModule {

    @Provides
    internal fun provideSplashPresenter(repo: IRepository, userPrefs: IUserPrefs): SplashPresenter {
        return SplashPresenter(repo, userPrefs)
    }
}
