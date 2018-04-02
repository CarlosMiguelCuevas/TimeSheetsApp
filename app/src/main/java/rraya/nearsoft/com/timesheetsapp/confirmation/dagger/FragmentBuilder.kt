package rraya.nearsoft.com.timesheetsapp.confirmation.dagger

import android.support.v4.app.Fragment
import dagger.Binds
import dagger.Module
import dagger.android.AndroidInjector
import dagger.android.support.FragmentKey
import dagger.multibindings.IntoMap
import rraya.nearsoft.com.timesheetsapp.confirmation.ConfirmationView


@Module
abstract class FragmentBuilder {

    @Binds
    @IntoMap
    @FragmentKey(ConfirmationView::class)
    internal abstract fun bindConfirmationFragment(builder: ConfirmationFragmentComponent.Builder): AndroidInjector.Factory<out Fragment>

}
