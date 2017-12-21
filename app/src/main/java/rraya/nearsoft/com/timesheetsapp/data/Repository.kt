package rraya.nearsoft.com.timesheetsapp.data

import com.google.gson.GsonBuilder
import io.reactivex.Single
import okhttp3.MediaType
import okhttp3.RequestBody
import rraya.nearsoft.com.timesheetsapp.data.models.Day
import rraya.nearsoft.com.timesheetsapp.network.TimesheetsApi
import rraya.nearsoft.com.timesheetsapp.network.TokenBody

/**
 * Created by oaguilar on 12/19/17.
 */

class Repository(val api: TimesheetsApi) : IRepository {

    @Throws(Throwable::class)
    override fun getTimesheetsTokenFromGoogleToken(googleToken: String): Single<String> {
        return api.getTSTokenFromGoogleToken(createRequestBody(googleToken))
                .map({
                    if(it.isSuccessful && it.body()!= null){
                        it.body()!!.token
                    }else{
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


    override fun getWeekDaysForWeekStarting(currentDay: String): List<Day> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
