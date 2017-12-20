package rraya.nearsoft.com.timesheetsapp.dagger

import javax.inject.Singleton

import dagger.Module
import dagger.Provides
import rraya.nearsoft.com.timesheetsapp.data.IRepository
import rraya.nearsoft.com.timesheetsapp.data.Repository
import rraya.nearsoft.com.timesheetsapp.network.TimesheetsApi

/**
 * Created by oaguilar on 12/5/17.
 */

@Module
class RepositoryModule {

    @Provides
    @Singleton
    internal fun providesRepository(api: TimesheetsApi): IRepository {
        return Repository(api)
    }
}
