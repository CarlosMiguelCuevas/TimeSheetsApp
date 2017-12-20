package rraya.nearsoft.com.timesheetsapp.dagger

import io.reactivex.Single
import rraya.nearsoft.com.timesheetsapp.data.IRepository

class MockRepository : IRepository {
    override fun getTimesheetsTokenFromGoogleToken(googleToken: String?): Single<String> {
        return Single.just("6gf7d8s96gfds77896fdsklnjvdfs")
    }
}