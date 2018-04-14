package rraya.nearsoft.com.timesheetsapp.splashloginscreen.dagger

import dagger.Module
import dagger.android.ContributesAndroidInjector
import rraya.nearsoft.com.timesheetsapp.dagger.scopes.FragmentScope
import rraya.nearsoft.com.timesheetsapp.splashloginscreen.SplashView


@Module
abstract class FragmentBuilder {
    //TODO: presentation, if our activity has fragments we create this archive as we did with the list of cativities
    //we do teh same her as in activity builder and we get rid of fragment compoennt
    @FragmentScope
    @ContributesAndroidInjector(modules = [SplashFragmentModule::class])
    internal abstract fun bindSplashFragment(): SplashView

}
