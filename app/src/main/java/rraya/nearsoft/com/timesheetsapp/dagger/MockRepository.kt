package rraya.nearsoft.com.timesheetsapp.dagger

import io.reactivex.Single
import rraya.nearsoft.com.timesheetsapp.data.models.Day
import rraya.nearsoft.com.timesheetsapp.data.IDataRepository
import java.util.*

class MockRepository : IDataRepository {

    var mockedToken : String = ""

    override fun getTimeSheetTokenFromSharedPreferences(): String {
        return mockedToken
    }

    override fun saveTimeSheetTokenIntoPreferences(token: String) {
        mockedToken = token
    }

    override fun getTimesheetsTokenFromGoogleToken(googleToken: String): Single<String> {
        return Single.just("6gf7d8s96gfds77896fdsklnjvdfs")
    }

    override fun getWeekDaysForWeekStarting(currentDay: String): List<Day> {

        val dayDummies: ArrayList<Day> = ArrayList()
        (0 until 7).forEach{dayDummies.add(Day(Date(), 8))}
        return dayDummies
    }
}