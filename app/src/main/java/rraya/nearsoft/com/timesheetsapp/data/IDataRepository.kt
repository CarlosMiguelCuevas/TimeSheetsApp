package rraya.nearsoft.com.timesheetsapp.data

import io.reactivex.Single
import rraya.nearsoft.com.timesheetsapp.data.models.Day

interface IDataRepository {

    fun getTimesheetsTokenFromGoogleToken(googleToken: String): Single<String>

    fun getTimeSheetTokenFromSharedPreferences(): String

    fun saveTimeSheetTokenIntoPreferences(token: String)

    fun getWeekDaysForWeekStarting(currentDay: String): List<Day>
}