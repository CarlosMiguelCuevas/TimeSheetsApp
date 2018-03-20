package rraya.nearsoft.com.timesheetsapp.dagger

import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AppModule::class,
    NetworkModule::class,
    DataModule::class,
    GiphyRepositoryModule::class
])
interface AppComponent {

    fun SplashSubComponent(): SplashComponent
    fun TimeSheetFormhSubComponent(): TimeSheetFormComponent
    fun ConfirmationSubComponent(): ConfirmationComponent
    fun NotificationSubComponent(): NotificationComponent

//    fun inject(target: TimeSheetsApp)

}
