package rraya.nearsoft.com.timesheetsapp.dagger

import dagger.Binds
import dagger.Module
import rraya.nearsoft.com.timesheetsapp.data.DataRepositoryImpl
import rraya.nearsoft.com.timesheetsapp.data.IDataRepository
import rraya.nearsoft.com.timesheetsapp.data.local.IUserPrefs
import rraya.nearsoft.com.timesheetsapp.data.local.UserPrefs
import javax.inject.Singleton

@Module
abstract class DataModule {


    //    TODO: shoi in presentation, another way to do it
    //    @Provides
//    @Singleton
//    internal fun providesDataRepository(api: TimesheetsApi, sharedPreferences: IUserPrefs): IDataRepository {
//        return DataRepositoryImpl(api, sharedPreferences)
//    }
//@Provides
//@Singleton
//internal fun provideSplashPresenter(appContext: Context): IUserPrefs {
//    return UserPrefs(appContext)
//}
//
    @Singleton
    @Binds
    internal abstract fun providesDataRepository(dataRepositoryImpl: DataRepositoryImpl): IDataRepository

    @Singleton
    @Binds
    internal abstract fun provideSplashPresenter(userPrefs: UserPrefs): IUserPrefs

}

