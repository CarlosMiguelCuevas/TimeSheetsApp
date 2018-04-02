package rraya.nearsoft.com.timesheetsapp

import android.app.Activity
import android.app.Application
import android.app.Service
import android.content.BroadcastReceiver
import dagger.android.*
import rraya.nearsoft.com.timesheetsapp.dagger.DaggerAppComponent
import javax.inject.Inject


class TimeSheetsApp : Application(), HasActivityInjector, HasServiceInjector, HasBroadcastReceiverInjector {//TODO: [new] presentation, implementamso las intrfaces para cada tipo de dispatcher

    //TODO: [new] presentation, al inyectar application inyectamso estos tres providers que proveeen los dispatchers, que soon los que inyectaran el sactivity el service y el receiver
    @Inject
    lateinit var activityDispatchingAndroidInjector: DispatchingAndroidInjector<Activity>
    @Inject
    lateinit var serviceDispatchingAndroidInjector: DispatchingAndroidInjector<Service>
    @Inject
    lateinit var broadcastReceiverDispatchingAndroidInjector: DispatchingAndroidInjector<BroadcastReceiver>

    override fun onCreate() {
        super.onCreate()
//        component = buildComponent()
        DaggerAppComponent
                .builder()
                .application(this)//TODO: [new] presentation, aqui proveemos application context
                .build()
                .inject(this)//TODO: [new] presentation, con esto inyectamso esta clase y ya teenmos lso dispatchers
    }

    //TODO: [new] presentation, impementamso los methodos de las interfces y proveemos los disptchers
    override fun activityInjector(): AndroidInjector<Activity> {
        return activityDispatchingAndroidInjector
    }

    override fun serviceInjector(): AndroidInjector<Service> {
        return serviceDispatchingAndroidInjector
    }

    override fun broadcastReceiverInjector(): AndroidInjector<BroadcastReceiver> {
        return broadcastReceiverDispatchingAndroidInjector
    }

//    TODO: presentation, we don't need this any more
//    companion object {
//        lateinit var component: AppComponent
//    }
//

//
//    private fun buildComponent(): AppComponent {
//        return DaggerAppComponent.builder()
//                .application(this)
////                .appModule(AppModule(this)) TODO: presentation, asi se ahacie antes, pero con el buileder ya podemos usar el de arriba
////                .dataModule( DataModule())
////                .networkModule(NetworkModule())
////                .giphyRepositoryModule(GiphyRepositoryModule())
//                .build()
//    }

}