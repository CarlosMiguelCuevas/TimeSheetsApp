package rraya.nearsoft.com.timesheetsapp.dagger

import android.app.Application
import android.content.Context

import javax.inject.Singleton

import dagger.Module
import dagger.Provides

/**
 * Created by oaguilar on 12/5/17.
 */

@Module
class AppModule(private val application: Application) {

    @Provides
    @Singleton
    fun provideContext(): Context {
        return application
    }
}
