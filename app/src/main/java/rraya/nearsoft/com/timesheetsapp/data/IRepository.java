package rraya.nearsoft.com.timesheetsapp.data;

import io.reactivex.Single;

public interface IRepository {
    Single<String> getTimesheetsTokenFromGoogleToken(String googleToken) throws Throwable;
}
