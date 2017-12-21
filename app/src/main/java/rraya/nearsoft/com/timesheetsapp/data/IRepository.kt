package rraya.nearsoft.com.timesheetsapp.data

import io.reactivex.Single
import rraya.nearsoft.com.timesheetsapp.data.models.Day

/**
 * Created by oaguilar on 12/19/17.
 */

interface IRepository {
    @Throws(Throwable::class)
    fun getTimesheetsTokenFromGoogleToken(googleToken: String): Single<String>

    @Throws(Throwable::class)
    fun getWeekDaysForWeekStarting(currentDay: String): List<Day>
}
