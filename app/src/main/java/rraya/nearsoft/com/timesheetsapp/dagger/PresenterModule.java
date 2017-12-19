package rraya.nearsoft.com.timesheetsapp.dagger;

import dagger.Module;
import dagger.Provides;
import rraya.nearsoft.com.timesheetsapp.network.TimesheetsApi;
import rraya.nearsoft.com.timesheetsapp.splashloginscreen.SplashPresenter;

/**
 * Created by oaguilar on 12/5/17.
 */

@Module
public class PresenterModule {

    @Provides
    SplashPresenter provideSplashPresenter(TimesheetsApi tsApi) {
        return new SplashPresenter(tsApi);
    }
}
