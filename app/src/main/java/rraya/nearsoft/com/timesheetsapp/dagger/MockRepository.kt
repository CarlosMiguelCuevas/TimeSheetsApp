package rraya.nearsoft.com.timesheetsapp.dagger

import io.reactivex.Single
import rraya.nearsoft.com.timesheetsapp.data.IDataRepository

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

}