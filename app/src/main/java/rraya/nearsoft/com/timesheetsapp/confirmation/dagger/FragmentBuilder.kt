package rraya.nearsoft.com.timesheetsapp.confirmation.dagger

import dagger.Module
import dagger.android.ContributesAndroidInjector
import rraya.nearsoft.com.timesheetsapp.confirmation.ConfirmationView
import rraya.nearsoft.com.timesheetsapp.dagger.scopes.FragmentScope


@Module
abstract class FragmentBuilder {

    @FragmentScope
    @ContributesAndroidInjector(modules = [ConfirmationFragmentModule::class])
    internal abstract fun bindConfirmationFragment(): ConfirmationView

}
