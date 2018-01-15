package rraya.nearsoft.com.timesheetsapp.data

import android.util.Log
import io.reactivex.Single
import rraya.nearsoft.com.timesheetsapp.data.models.Day
import java.text.SimpleDateFormat
import java.util.*

class MockRepository : IDataRepository {

    private var mockedToken: String = "67v8f9ds6v7fd6svfds" //lets log the user

    override fun getTimeSheetTokenFromSharedPreferences(): String {
        return mockedToken
    }

    override fun saveTimeSheetTokenIntoPreferences(token: String) {
        mockedToken = token
    }

    override fun getTimesheetsTokenFromGoogleToken(googleToken: String): Single<String> {
        return Single.just("6gf7d8s96gfds77896fdsklnjvdfs")
    }

    override fun getWeekDaysForWeekStarting(startingDateString: String): Single<List<Day>> {

        val dayDummies: ArrayList<Day> = ArrayList()
        val simpleDataFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val currentDate = simpleDataFormat.parse(startingDateString)
        val calendar = Calendar.getInstance()
        calendar.time = currentDate
        (0 until 7).forEach {
            val dayDate = calendar.time
            dayDummies.add(Day(dayDate, 8))
            calendar.add(Calendar.DAY_OF_YEAR, 1)
        }
        return Single.just(dayDummies)
    }

    override fun getClientName(): Single<String> {
        Thread.sleep(5000)
        return Single.just("Umbrella corp")
    }

    override fun isCurrentWeekSubmitted(): Single<Boolean> {
        Thread.sleep(5000)
        return Single.just(false)
    }

    override fun submitTimeSheet(days: List<Day>?): Single<Boolean> {
        Log.v("MockRepository", "Timesheets sent!!")
        return Single.just(true)
    }
}