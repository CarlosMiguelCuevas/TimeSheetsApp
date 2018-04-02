package rraya.nearsoft.com.timesheetsapp.splashloginscreen.dagger

import dagger.Subcomponent
import dagger.android.AndroidInjector
import rraya.nearsoft.com.timesheetsapp.dagger.scopes.ActivityScope
import rraya.nearsoft.com.timesheetsapp.splashloginscreen.SplashActivity


@ActivityScope
@Subcomponent(modules = [
    SplashActivityModule::class,//TODO: see inside
    FragmentBuilder::class//TODO: [new] presentation, referencia a la lista de fragments
])
interface SplashActivityComponent : AndroidInjector<SplashActivity> {//TODO:presentation need to inherit from this to have teh builder

    @Subcomponent.Builder
    abstract class Builder : AndroidInjector.Builder<SplashActivity>()

}