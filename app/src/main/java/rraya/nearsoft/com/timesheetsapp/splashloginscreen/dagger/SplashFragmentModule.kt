package rraya.nearsoft.com.timesheetsapp.splashloginscreen.dagger

import dagger.Module
import dagger.Provides
import rraya.nearsoft.com.timesheetsapp.dagger.scopes.FragmentScope
import rraya.nearsoft.com.timesheetsapp.data.IDataRepository
import rraya.nearsoft.com.timesheetsapp.splashloginscreen.SplashPresenter
import rraya.nearsoft.com.timesheetsapp.splashloginscreen.SplashView
import rraya.nearsoft.com.timesheetsapp.splashloginscreen.SplashViewPresenterContract
import javax.inject.Named

@Module
class SplashFragmentModule() {

    @FragmentScope
    @Provides
    internal fun provideSplashPresenter(dataRepository: IDataRepository, view: SplashViewPresenterContract.View): SplashViewPresenterContract.Presenter {
        return SplashPresenter(dataRepository, view)
    }

    @FragmentScope
    @Named("SplashMessage")
    @Provides
    internal fun provideMessage(): String {
        return "Hola Enfermera"
    }

    @FragmentScope
    @Provides
    internal fun providesView(splashView: SplashView): SplashViewPresenterContract.View {
        return splashView
    }

}