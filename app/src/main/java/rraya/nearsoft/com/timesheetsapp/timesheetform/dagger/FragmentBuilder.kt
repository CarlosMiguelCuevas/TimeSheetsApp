package rraya.nearsoft.com.timesheetsapp.timesheetform.dagger

import android.support.v4.app.Fragment
import dagger.Binds
import dagger.Module
import dagger.android.AndroidInjector
import dagger.android.support.FragmentKey
import dagger.multibindings.IntoMap
import rraya.nearsoft.com.timesheetsapp.timesheetform.TimeSheetView


@Module
abstract class FragmentBuilder {

    @Binds
    @IntoMap
    @FragmentKey(TimeSheetView::class)
    internal abstract fun bindTimeSheetFormFragment(builder: TimeSheetFormFragmentComponent.Builder): AndroidInjector.Factory<out Fragment>

}
