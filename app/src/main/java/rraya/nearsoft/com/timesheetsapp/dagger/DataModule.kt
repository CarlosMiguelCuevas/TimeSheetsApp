package rraya.nearsoft.com.timesheetsapp.dagger

import android.content.Context
import dagger.Module
import dagger.Provides
import rraya.nearsoft.com.timesheetsapp.BuildConfig
import rraya.nearsoft.com.timesheetsapp.data.DataRepositoryImpl
import rraya.nearsoft.com.timesheetsapp.data.IDataRepository
import rraya.nearsoft.com.timesheetsapp.data.MockRepository
import rraya.nearsoft.com.timesheetsapp.data.local.IUserPrefs
import rraya.nearsoft.com.timesheetsapp.data.local.UserPrefs
import rraya.nearsoft.com.timesheetsapp.network.TimesheetsApi
import javax.inject.Singleton

@Module
class DataModule {

    @Provides
    @Singleton
    internal fun providesDataRepository(api: TimesheetsApi, sharedPreferences: IUserPrefs): IDataRepository {
        //TODO: Remove this when we get the actual API
        return if (BuildConfig.DEBUG) {
            MockRepository()
        } else {
            DataRepositoryImpl(api, sharedPreferences)
        }
    }

    @Provides
    @Singleton
    internal fun provideSplashPresenter(appContext: Context): IUserPrefs {
        return UserPrefs(appContext)
    }

}

