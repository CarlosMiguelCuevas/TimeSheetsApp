package rraya.nearsoft.com.timesheetsapp.splashloginscreen.dagger

import dagger.Module
import dagger.Provides
import rraya.nearsoft.com.timesheetsapp.dagger.scopes.ActivityViewScope
import rraya.nearsoft.com.timesheetsapp.data.IDataRepository
import rraya.nearsoft.com.timesheetsapp.splashloginscreen.SplashPresenter
import rraya.nearsoft.com.timesheetsapp.splashloginscreen.SplashViewPresenterContract

@Module
class SplashModule {

    @ActivityViewScope
    @Provides
    internal fun provideSplashPresenter(dataRepository: IDataRepository): SplashViewPresenterContract.Presenter {
        return SplashPresenter(dataRepository)
    }
}