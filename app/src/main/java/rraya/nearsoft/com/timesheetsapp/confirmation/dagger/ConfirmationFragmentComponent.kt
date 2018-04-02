package rraya.nearsoft.com.timesheetsapp.confirmation.dagger

import dagger.Subcomponent
import dagger.android.AndroidInjector
import rraya.nearsoft.com.timesheetsapp.confirmation.ConfirmationView
import rraya.nearsoft.com.timesheetsapp.dagger.scopes.FragmentScope

@FragmentScope
@Subcomponent(modules = [
    ConfirmationFragmentModule::class
])
interface ConfirmationFragmentComponent : AndroidInjector<ConfirmationView> {

    @Subcomponent.Builder
    abstract class Builder : AndroidInjector.Builder<ConfirmationView>()

}
