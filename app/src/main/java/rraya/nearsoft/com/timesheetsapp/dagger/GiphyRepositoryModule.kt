package rraya.nearsoft.com.timesheetsapp.dagger

import javax.inject.Singleton

import dagger.Module
import dagger.Provides
import rraya.nearsoft.com.timesheetsapp.BuildConfig
import rraya.nearsoft.com.timesheetsapp.data.GifsRepository
import rraya.nearsoft.com.timesheetsapp.data.IGifsRepository
import rraya.nearsoft.com.timesheetsapp.data.IRepository
import rraya.nearsoft.com.timesheetsapp.data.Repository
import rraya.nearsoft.com.timesheetsapp.network.GiphyApi
import rraya.nearsoft.com.timesheetsapp.network.TimesheetsApi

/**
 * Created by oaguilar on 12/5/17.
 */

@Module
class GiphyRepositoryModule {

    @Provides
    @Singleton
    internal fun providesRepository(api: GiphyApi): IGifsRepository {
        return GifsRepository(api)
    }
}

