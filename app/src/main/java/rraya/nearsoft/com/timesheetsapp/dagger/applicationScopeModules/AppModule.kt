package rraya.nearsoft.com.timesheetsapp.dagger.applicationScopeModules

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

//TODO:[new] presentation, we deleted submodules from here
@Module
class AppModule() {

    @Provides
    @Singleton
    fun provideContext(application: Application): Context {//TODO: presentation, ya no esta application en el costructor, ahora esta en el grafo gracias al builder del component
        return application
    }
}
