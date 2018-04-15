package rraya.nearsoft.com.timesheetsapp.dagger

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import dagger.android.support.DaggerApplication
import rraya.nearsoft.com.timesheetsapp.TimeSheetsApp
import rraya.nearsoft.com.timesheetsapp.dagger.applicationScopeModules.*
import javax.inject.Singleton


@Singleton
@Component(modules = [
    AndroidSupportInjectionModule::class,
    ActivityBuilder::class,
    ServiceBuilder::class,
    ReceiverBuilder::class,
    AppModule::class,
    NetworkModule::class,
    DataModule::class,
    GiphyRepositoryModule::class
])
interface AppComponent : AndroidInjector<DaggerApplication> {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): AppComponent.Builder

        fun build(): AppComponent
    }

    fun inject(target: TimeSheetsApp)

}
