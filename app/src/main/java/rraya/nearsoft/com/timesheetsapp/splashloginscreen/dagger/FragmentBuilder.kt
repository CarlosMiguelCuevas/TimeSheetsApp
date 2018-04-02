package rraya.nearsoft.com.timesheetsapp.splashloginscreen.dagger

import android.support.v4.app.Fragment
import dagger.Binds
import dagger.Module
import dagger.android.AndroidInjector
import dagger.android.support.FragmentKey
import dagger.multibindings.IntoMap
import rraya.nearsoft.com.timesheetsapp.splashloginscreen.SplashView


@Module
abstract class FragmentBuilder {
    //TODO: presentation, if our activity has fragments we create this archive as we did with the list of cativities

    @Binds
    @IntoMap
    @FragmentKey(SplashView::class)//TODO: presentation, this is the reference for teh fragmemt
    internal abstract fun bindSplashFragment(builder: SplashFragmentComponent.Builder): AndroidInjector.Factory<out Fragment>

}
