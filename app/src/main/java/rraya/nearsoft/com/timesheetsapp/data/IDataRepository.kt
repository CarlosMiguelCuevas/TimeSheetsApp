package rraya.nearsoft.com.timesheetsapp.data

import io.reactivex.Single

interface IDataRepository {

    fun getTimesheetsTokenFromGoogleToken(googleToken: String): Single<String>

    fun getTimeSheetTokenFromSharedPreferences(): String

    fun saveTimeSheetTokenIntoPreferences(token: String)

}