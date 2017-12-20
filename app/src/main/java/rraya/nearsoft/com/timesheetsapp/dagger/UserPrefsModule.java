package rraya.nearsoft.com.timesheetsapp.dagger;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import rraya.nearsoft.com.timesheetsapp.IUserPrefs;
import rraya.nearsoft.com.timesheetsapp.UserPrefs;

/**
 * Created by oaguilar on 12/5/17.
 */

@Module
public class UserPrefsModule {

    @Provides
    @Singleton
    IUserPrefs provideSplashPresenter(Context appContext) {
        return new UserPrefs(appContext);
    }
}
