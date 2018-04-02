package rraya.nearsoft.com.timesheetsapp.dagger

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import rraya.nearsoft.com.timesheetsapp.TimeSheetsApp
import rraya.nearsoft.com.timesheetsapp.dagger.applicationScopeModules.*
import javax.inject.Singleton


@Singleton
@Component(modules = [
    AndroidInjectionModule::class, //TODO:[new] presentation add this module to inject teh activities and components
    ActivityBuilder::class, //TODO:[new] presentation add this module to mapp the activities (also services and broadcast receivers)
    AppModule::class, //TODO:[new] see inside
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

//TODO: presentation, we moved the subcmepnetns reference to the App module and add the builder to every subcomponet

    fun inject(target: TimeSheetsApp)//TODO: presentation, agregamos metodo para inyectar aplication

}
