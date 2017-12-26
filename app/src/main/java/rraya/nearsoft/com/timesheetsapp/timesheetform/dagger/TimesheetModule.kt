package rraya.nearsoft.com.timesheetsapp.splashloginscreen.dagger

import dagger.Module
import dagger.Provides
import rraya.nearsoft.com.timesheetsapp.dagger.scopes.FragmentScope
import rraya.nearsoft.com.timesheetsapp.data.IDataRepository
import rraya.nearsoft.com.timesheetsapp.timesheetform.TimeSheetPresenter
import rraya.nearsoft.com.timesheetsapp.timesheetform.TimesheetsPresenterContract

@Module
class TimesheetModule {

    @FragmentScope
    @Provides
    internal fun prodiveTimesheetPresenter(dataRepository: IDataRepository): TimesheetsPresenterContract.Presenter {
        return TimeSheetPresenter(dataRepository)
    }
}