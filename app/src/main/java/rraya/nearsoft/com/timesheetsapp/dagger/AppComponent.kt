package rraya.nearsoft.com.timesheetsapp.dagger

import android.app.Application
import dagger.BindsInstance
import dagger.Component
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

    //TODO: presentation, chequen este es otra forma de hacerlo, con ets ebuilder ya no es necesario mandar el obejcto en application, para el modulo, de app module, agregar al garph el application
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): AppComponent.Builder

        fun build(): AppComponent
    }

    //Educativo, se pone asi si el modulo del subcoempoennte requiere parametro USANDO el Builder pattern
//    asi se hacia antes: fun SplashSubComponent(module: SplashModule): SplashComponent
    fun SplashSubComponentBuilder(): SplashComponent.Builder
    //Edicativo, se puede escribir asi o sin el parametro, si es que el modulo tiene constructor default
    fun TimeSheetFormhSubComponent(module: TimesheetModule): TimeSheetFormComponent
    fun ConfirmationSubComponent(): ConfirmationComponent
    fun NotificationSubComponent(): NotificationComponent

//    fun inject(target: TimeSheetsApp)

}
