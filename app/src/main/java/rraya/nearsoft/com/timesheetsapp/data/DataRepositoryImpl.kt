package rraya.nearsoft.com.timesheetsapp.data

import android.util.Log
import io.reactivex.Single
import rraya.nearsoft.com.timesheetsapp.data.local.IUserPrefs
import rraya.nearsoft.com.timesheetsapp.data.models.Day
import rraya.nearsoft.com.timesheetsapp.data.models.TimeSheet
import rraya.nearsoft.com.timesheetsapp.network.TimesheetsApi
import rraya.nearsoft.com.timesheetsapp.network.TokenBody
import rraya.nearsoft.com.timesheetsapp.network.TokenResponse
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit

class DataRepositoryImpl(val api: TimesheetsApi, private val sharedPreferences: IUserPrefs) : IDataRepository {

    override fun getTimeSheetTokenFromSharedPreferences(): String {
        return sharedPreferences.getUserToken()
    }

    override fun saveTimeSheetTokenIntoPreferences(token: String) {
        sharedPreferences.setUserToken(token)
    }

    override fun getTimeSheetUserIdFromSharedPreferences(): Int {
        return sharedPreferences.getUserId()
    }

    override fun saveTimeSheetUserIdIntoPreferences(userId: Int) {
        sharedPreferences.setUserUserId(userId)
    }

    @Throws(Throwable::class)
    override fun getTimesheetsTokenFromGoogleToken(googleToken: String): Single<TokenResponse> {
        return api.getTSTokenFromGoogleToken(TokenBody(googleToken))
    }

    //TODO:Replace for actual implementation
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

    //TODO:Replace for actual implementation
    override fun getClientName(): Single<String> {
        return Single.just("Umbrella corp").delay(5, TimeUnit.SECONDS)
    }

    //TODO:Replace for actual implementation
    override fun isCurrentWeekSubmitted(): Single<Boolean> {
        return Single.just(false).delay(5, TimeUnit.SECONDS)
    }

    //TODO:Replace for actual implementation
    override fun submitTimeSheet(timesheet: TimeSheet?): Single<Boolean> {
        Log.v("MockRepository", "Timesheets sent!!")
        return Single.just(true).delay(5, TimeUnit.SECONDS)
    }
}
