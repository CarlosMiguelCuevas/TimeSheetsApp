package rraya.nearsoft.com.timesheetsapp.splashloginscreen.dagger

import dagger.Subcomponent
import dagger.android.AndroidInjector
import rraya.nearsoft.com.timesheetsapp.dagger.scopes.FragmentScope
import rraya.nearsoft.com.timesheetsapp.splashloginscreen.SplashView


@FragmentScope
@Subcomponent(modules = [
    SplashFragmentModule::class//TODO: [new] presentation, see inside
])
interface SplashFragmentComponent : AndroidInjector<SplashView> {//TODO:presentation need to inherit from this to have teh builder

    @Subcomponent.Builder
    abstract class Builder : AndroidInjector.Builder<SplashView>()//TODO: [new] presentation, notice teh splash vie, it is teh fragment

}