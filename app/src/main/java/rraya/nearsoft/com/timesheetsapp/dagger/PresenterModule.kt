package rraya.nearsoft.com.timesheetsapp.dagger

import dagger.Module
import dagger.Provides
import rraya.nearsoft.com.timesheetsapp.IUserPrefs
import rraya.nearsoft.com.timesheetsapp.data.IRepository
import rraya.nearsoft.com.timesheetsapp.splashloginscreen.SplashPresenter
import rraya.nearsoft.com.timesheetsapp.splashloginscreen.SplashViewPresenterContract

@Module
class PresenterModule {

    @Provides
    internal fun provideSplashPresenter(repo: IRepository, userPrefs: IUserPrefs): SplashViewPresenterContract.Presenter {
        return SplashPresenter(repo, userPrefs)
    }
}
