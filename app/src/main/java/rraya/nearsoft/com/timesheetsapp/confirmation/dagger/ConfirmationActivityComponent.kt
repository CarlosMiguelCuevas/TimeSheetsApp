package rraya.nearsoft.com.timesheetsapp.confirmation.dagger

import dagger.Subcomponent
import dagger.android.AndroidInjector
import rraya.nearsoft.com.timesheetsapp.confirmation.ConfirmationActivity
import rraya.nearsoft.com.timesheetsapp.dagger.scopes.ActivityScope

@ActivityScope
@Subcomponent(modules = [
    ConfirmationActivityModule::class,
    FragmentBuilder::class
])
interface ConfirmationActivityComponent : AndroidInjector<ConfirmationActivity> {

    @Subcomponent.Builder
    abstract class Builder : AndroidInjector.Builder<ConfirmationActivity>()

}
