package rraya.nearsoft.com.timesheetsapp.data

import io.reactivex.Single

interface IGifsRepository {

    fun getWellDoneGif(): Single<ByteArray>

    fun getNotGoodGif(): Single<ByteArray>
}