package rraya.nearsoft.com.timesheetsapp.dagger

import io.reactivex.Single
import rraya.nearsoft.com.timesheetsapp.data.IDataRepository

class MockRepository : IDataRepository {

    override fun getTimeSheetTokenFromSharedPreferences(): String {
        return "hello"
    }

    override fun saveTimeSheetTokenIntoPreferences(token: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getTimesheetsTokenFromGoogleToken(googleToken: String): Single<String> {
        return Single.just("6gf7d8s96gfds77896fdsklnjvdfs")
    }

}