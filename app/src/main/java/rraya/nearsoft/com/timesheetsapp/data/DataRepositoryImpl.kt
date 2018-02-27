package rraya.nearsoft.com.timesheetsapp.data

import android.util.Log
import com.google.gson.GsonBuilder
import io.reactivex.Single
import okhttp3.MediaType
import okhttp3.RequestBody
import rraya.nearsoft.com.timesheetsapp.data.local.IUserPrefs
import rraya.nearsoft.com.timesheetsapp.data.models.Day
import rraya.nearsoft.com.timesheetsapp.data.models.TimeSheet
import rraya.nearsoft.com.timesheetsapp.network.TimesheetsApi
import rraya.nearsoft.com.timesheetsapp.network.TokenBody
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

    @Throws(Throwable::class)
    override fun getTimesheetsTokenFromGoogleToken(googleToken: String): Single<String> {
        return api.getTSTokenFromGoogleToken(createRequestBody(googleToken))
                .map({
                    if (it.isSuccessful && it.body() != null) {
                        it.body()!!.token
                    } else {
                        throw RuntimeException("Error del servidor.")
                    }
                }).doOnError({
            throw RuntimeException("Error de conexión.")
        })
    }


    private fun createRequestBody(token: String): RequestBody {
        val json = GsonBuilder().excludeFieldsWithoutExposeAnnotation().create().toJson(TokenBody(token))
        return RequestBody.create(MediaType.parse("application/json; charset=utf-8"), json)
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
