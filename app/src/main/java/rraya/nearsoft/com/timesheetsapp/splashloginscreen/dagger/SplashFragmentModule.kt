package rraya.nearsoft.com.timesheetsapp.splashloginscreen.dagger

import dagger.Module
import dagger.Provides
import rraya.nearsoft.com.timesheetsapp.dagger.scopes.FragmentScope
import rraya.nearsoft.com.timesheetsapp.data.IDataRepository
import rraya.nearsoft.com.timesheetsapp.splashloginscreen.SplashPresenter
import rraya.nearsoft.com.timesheetsapp.splashloginscreen.SplashViewPresenterContract
import javax.inject.Named

@Module
class SplashFragmentModule() {
    //TODO: [new] presentation, provee lo que usa el fragment

    @FragmentScope
    @Provides
    internal fun provideSplashPresenter(dataRepository: IDataRepository): SplashViewPresenterContract.Presenter {
        return SplashPresenter(dataRepository)
    }

    @FragmentScope
    @Named("SplashMessage")
    @Provides
    internal fun provideMessage(): String {
        return "Hola Enfermera"
    }
}