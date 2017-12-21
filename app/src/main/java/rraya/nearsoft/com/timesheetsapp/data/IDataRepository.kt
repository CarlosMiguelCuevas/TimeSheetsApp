package rraya.nearsoft.com.timesheetsapp.data

import io.reactivex.Single

interface IDataRepository {

    fun getTimesheetsTokenFromGoogleToken(googleToken: String): Single<String>

    fun getGoogleTokenFromSharedPreferences(): Single<String>

    fun saveGoogleTokenFromIntoPreferences(token: String)

}