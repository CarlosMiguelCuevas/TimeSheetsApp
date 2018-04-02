package rraya.nearsoft.com.timesheetsapp.dagger.applicationScopeModules

import dagger.Module
import dagger.Provides
import rraya.nearsoft.com.timesheetsapp.data.GifsRepository
import rraya.nearsoft.com.timesheetsapp.data.IGifsRepository
import rraya.nearsoft.com.timesheetsapp.network.GiphyApi
import javax.inject.Singleton

@Module
class GiphyRepositoryModule {

    @Singleton
    @Provides
    internal fun providesRepository(api: GiphyApi): IGifsRepository {
        return GifsRepository(api)
    }

}

