package rraya.nearsoft.com.timesheetsapp.data

import io.reactivex.Single
import rraya.nearsoft.com.timesheetsapp.data.models.Day
import rraya.nearsoft.com.timesheetsapp.data.models.TimeSheet

interface IDataRepository {

    fun getTimesheetsTokenFromGoogleToken(googleToken: String): Single<String>

    fun getTimeSheetTokenFromSharedPreferences(): String

    fun saveTimeSheetTokenIntoPreferences(token: String)

    fun getWeekDaysForWeekStarting(startingDateString: String): Single<List<Day>>

    fun getClientName(): Single<String>

    fun isCurrentWeekSubmitted(): Single<Boolean>

    fun submitTimeSheet(timesheet: TimeSheet?): Single<Boolean>
}