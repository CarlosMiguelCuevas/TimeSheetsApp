package rraya.nearsoft.com.timesheetsapp.dagger

import dagger.Subcomponent
import rraya.nearsoft.com.timesheetsapp.confirmation.dagger.ConfirmationModule
import rraya.nearsoft.com.timesheetsapp.dagger.scopes.ActivityViewScope
import rraya.nearsoft.com.timesheetsapp.splashloginscreen.ConfirmationView

@ActivityViewScope
@Subcomponent(modules = [
    ConfirmationModule::class
])
interface ConfirmationComponent {

    fun inject(target: ConfirmationView)

}
