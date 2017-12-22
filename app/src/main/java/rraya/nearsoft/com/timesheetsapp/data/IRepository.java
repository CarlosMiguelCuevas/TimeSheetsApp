package rraya.nearsoft.com.timesheetsapp.data;

import io.reactivex.Single;

/**
 * Created by oaguilar on 12/19/17.
 */

public interface IRepository {
    Single<String> getTimesheetsTokenFromGoogleToken(String googleToken) throws Throwable;
}
