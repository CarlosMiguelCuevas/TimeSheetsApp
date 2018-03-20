package rraya.nearsoft.com.timesheetsapp.dagger

import dagger.Subcomponent
import rraya.nearsoft.com.timesheetsapp.dagger.scopes.ActivityViewScope
import rraya.nearsoft.com.timesheetsapp.splashloginscreen.SplashView
import rraya.nearsoft.com.timesheetsapp.splashloginscreen.dagger.SplashModule

@ActivityViewScope
@Subcomponent(modules = [
    SplashModule::class
])
interface SplashComponent {

    fun inject(target: SplashView)

}
