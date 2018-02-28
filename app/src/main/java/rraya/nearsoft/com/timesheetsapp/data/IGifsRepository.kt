package rraya.nearsoft.com.timesheetsapp.data

import io.reactivex.Single

interface IGifsRepository {

    fun getWellDoneGif(): Single<String>

    fun getNotGoodGif(): Single<String>
}