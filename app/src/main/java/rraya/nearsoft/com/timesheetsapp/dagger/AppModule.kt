package rraya.nearsoft.com.timesheetsapp.dagger

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

//TODO: presentation, gracias al builder ya no esta en el constructor
@Module
class AppModule() {

    @Provides
    @Singleton
    fun provideContext(application: Application): Context {
        return application
    }
}
