package rraya.nearsoft.com.timesheetsapp.dagger

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import dagger.android.support.DaggerApplication
import rraya.nearsoft.com.timesheetsapp.TimeSheetsApp
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AndroidSupportInjectionModule::class,
    AppModule::class,
    NetworkModule::class,
    DataModule::class,
    BindingActivityModule::class,
    BindingSplashFragmentModule::class,
    BindingReceiverModule::class
])
interface AppComponent : AndroidInjector<DaggerApplication> {

    fun inject(target: TimeSheetsApp)

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): AppComponent.Builder

        fun build(): AppComponent

    }

}
