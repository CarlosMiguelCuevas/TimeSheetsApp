package rraya.nearsoft.com.timesheetsapp.data;

import io.reactivex.Single;

/**
 * Created by oaguilar on 12/19/17.
 */

public interface IGifsRepository {
    Single<byte[]> getWellDoneGif() throws Throwable;
    Single<byte[]> getNotGoodGif() throws Throwable;
}
