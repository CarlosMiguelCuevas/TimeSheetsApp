package rraya.nearsoft.com.timesheetsapp.dagger

import dagger.Component
import rraya.nearsoft.com.timesheetsapp.splashloginscreen.dagger.SplashModule
import rraya.nearsoft.com.timesheetsapp.splashloginscreen.dagger.TimesheetModule
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AppModule::class,
    NetworkModule::class,
    DataModule::class,
    GiphyRepositoryModule::class
])
interface AppComponent {

    //Educativo, se pone asi si el modulo del subcoempoennte requiere parametro USANDO el Builder pattern
//    asi se hacia antes: fun SplashSubComponent(module: SplashModule): SplashComponent
    fun SplashSubComponentBuilder(): SplashComponent.Builder
    //Edicativo, se puede escribir asi o sin el parametro, si es que el modulo tiene constructor default
    fun TimeSheetFormhSubComponent(module: TimesheetModule): TimeSheetFormComponent
    fun ConfirmationSubComponent(): ConfirmationComponent
    fun NotificationSubComponent(): NotificationComponent

//    fun inject(target: TimeSheetsApp)

}
