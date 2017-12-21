package rraya.nearsoft.com.timesheetsapp.splashloginscreen.dagger

import dagger.Module
import dagger.Provides
import rraya.nearsoft.com.timesheetsapp.dagger.scopes.FragmentScope
import rraya.nearsoft.com.timesheetsapp.data.IDataRepository
import rraya.nearsoft.com.timesheetsapp.splashloginscreen.SplashPresenter
import rraya.nearsoft.com.timesheetsapp.splashloginscreen.SplashView
import rraya.nearsoft.com.timesheetsapp.splashloginscreen.SplashViewPresenterContract

@Module
class SplashModule {

    @FragmentScope
    @Provides
    internal fun provideSplashView(view: SplashView): SplashViewPresenterContract.View {
        return view
    }

    @FragmentScope
    @Provides
    internal fun provideSplashPresenter(dataRepository: IDataRepository, view: SplashViewPresenterContract.View): SplashViewPresenterContract.Presenter {
        return SplashPresenter(dataRepository, view)
    }
}