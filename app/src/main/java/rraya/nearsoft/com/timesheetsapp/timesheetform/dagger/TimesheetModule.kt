package rraya.nearsoft.com.timesheetsapp.splashloginscreen.dagger

import dagger.Module
import dagger.Provides
import rraya.nearsoft.com.timesheetsapp.dagger.scopes.FragmentScope
import rraya.nearsoft.com.timesheetsapp.data.IDataRepository
import rraya.nearsoft.com.timesheetsapp.timesheetform.TimeSheetPresenter
import rraya.nearsoft.com.timesheetsapp.timesheetform.TimesheetsPresenterContract
import java.util.*

@Module
class TimesheetModule {

    @FragmentScope
    @Provides
    internal fun prodiveTimesheetPresenter(dataRepository: IDataRepository, calendar: Calendar): TimesheetsPresenterContract.Presenter {
        return TimeSheetPresenter(dataRepository, calendar)
    }
}