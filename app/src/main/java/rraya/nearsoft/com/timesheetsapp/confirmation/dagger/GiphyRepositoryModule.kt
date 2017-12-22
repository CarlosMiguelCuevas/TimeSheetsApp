package rraya.nearsoft.com.timesheetsapp.confirmation.dagger

import dagger.Module
import dagger.Provides
import rraya.nearsoft.com.timesheetsapp.dagger.scopes.ActivityScope
import rraya.nearsoft.com.timesheetsapp.data.GifsRepository
import rraya.nearsoft.com.timesheetsapp.data.IGifsRepository
import rraya.nearsoft.com.timesheetsapp.network.GiphyApi

@Module
class GiphyRepositoryModule {

    @ActivityScope
    @Provides
    internal fun providesRepository(api: GiphyApi): IGifsRepository {
        return GifsRepository(api)
    }

}

